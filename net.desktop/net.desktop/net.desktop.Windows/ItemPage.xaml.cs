using net.desktop.Data;
using net.desktop.Common;

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Windows.Input;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using System.Diagnostics;
using net.desktop.Services;
using net.desktop.Utilities;
using System.Threading.Tasks;
using net.desktop.Entities;

// The Universal Hub Application project template is documented at http://go.microsoft.com/fwlink/?LinkID=391955

namespace net.desktop
{
    /// <summary>
    /// A page that displays details for a single item within a group.
    /// </summary>
    public sealed partial class ItemPage : Page
    {
        private NavigationHelper navigationHelper;
        private ObservableDictionary defaultViewModel = new ObservableDictionary();
        private CentroService CentroService = new CentroService();
        private ParticipacionService ParticipacionService = new ParticipacionService();

        public ItemPage()
        {
            this.InitializeComponent();
            this.navigationHelper = new NavigationHelper(this);
            this.navigationHelper.LoadState += this.NavigationHelper_LoadState;
        }

        /// <summary>
        /// Gets the NavigationHelper used to aid in navigation and process lifetime management.
        /// </summary>
        public NavigationHelper NavigationHelper
        {
            get { return this.navigationHelper; }
        }

        /// <summary>
        /// Gets the DefaultViewModel. This can be changed to a strongly typed view model.
        /// </summary>
        public ObservableDictionary DefaultViewModel
        {
            get { return this.defaultViewModel; }
        }

        /// <summary>
        /// Populates the page with content passed during navigation.  Any saved state is also
        /// provided when recreating a page from a prior session.
        /// </summary>
        /// <param name="sender">
        /// The source of the event; typically <see cref="NavigationHelper"/>
        /// </param>
        /// <param name="e">Event data that provides both the navigation parameter passed to
        /// <see cref="Frame.Navigate(Type, object)"/> when this page was initially requested and
        /// a dictionary of state preserved by this page during an earlier
        /// session.  The state will be null the first time a page is visited.</param>
        private async void NavigationHelper_LoadState(object sender, LoadStateEventArgs e)
        {
            CentroEntity item = await this.Find_Centro((int)e.NavigationParameter);

            if (item != null)
            {
                item.Participaciones = await this.Find_Participaciones(item.Id_Centro.ToString());
            }
            else
            {
                Alert.CreateAlert("Ocurrió un error al obtener la información del Centro, intente más tarde.");
            }

            ProgressRing Loading = (ProgressRing)FindChildControl<ProgressRing>(this, "Progress");
            Loading.Visibility = Visibility.Collapsed;

            this.DefaultViewModel["Item"] = item;
        }

        private async Task<CentroEntity> Find_Centro(int id)
        {
            CentroEntity response = null;

            try
            {
                response = await CentroService.Find(id);

            }
            catch (Exception)
            {
                Alert.CreateAlert("Ocurrió un error al intentar conectar. Compruebe su conexión e intente más tarde.", "Sistema CEM - Error de conexión");
            }

            return response;

        }

        private async Task<List<ParticipacionEntity>> Find_Participaciones(String id_centro)
        {
            List<ParticipacionEntity> response = null;

            try
            {
                response = await this.ParticipacionService.All("id_centro", id_centro);

            }
            catch (Exception)
            {
                Alert.CreateAlert("No se encontraron datos u ocurrió un error al intentar conectar. Compruebe su conexión e intente más tarde.", "Sistema CEM - Error de conexión");
            }

            return response;

        }

        private DependencyObject FindChildControl<T>(DependencyObject control, string ctrlName)
        {
            int childNumber = VisualTreeHelper.GetChildrenCount(control);
            for (int i = 0; i < childNumber; i++)
            {
                DependencyObject child = VisualTreeHelper.GetChild(control, i);
                FrameworkElement fe = child as FrameworkElement;
                // Not a framework element or is null
                if (fe == null) return null;

                if (child is T && fe.Name == ctrlName)
                {
                    // Found the control so return
                    return child;
                }
                else
                {
                    // Not found it - search children
                    DependencyObject nextLevel = FindChildControl<T>(child, ctrlName);
                    if (nextLevel != null)
                        return nextLevel;
                }
            }
            return null;
        }

        #region NavigationHelper registration

        /// <summary>
        /// The methods provided in this section are simply used to allow
        /// NavigationHelper to respond to the page's navigation methods.
        /// Page specific logic should be placed in event handlers for the  
        /// <see cref="Common.NavigationHelper.LoadState"/>
        /// and <see cref="Common.NavigationHelper.SaveState"/>.
        /// The navigation parameter is available in the LoadState method 
        /// in addition to page state preserved during an earlier session.
        /// </summary>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            this.navigationHelper.OnNavigatedTo(e);
        }

        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {
            this.navigationHelper.OnNavigatedFrom(e);
        }

        #endregion

    }
}
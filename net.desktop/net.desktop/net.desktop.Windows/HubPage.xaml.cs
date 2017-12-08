using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using net.desktop.Data;
using net.desktop.Common;
using System.Threading.Tasks;
using net.desktop.Utilities;
using net.desktop.SessionManagerTools;
using System.Diagnostics;
using net.desktop.Services;
using net.desktop.Entities;

// The Universal Hub Application project template is documented at http://go.microsoft.com/fwlink/?LinkID=391955

namespace net.desktop
{
    /// <summary>
    /// A page that displays a grouped collection of items.
    /// </summary>
    public sealed partial class HubPage : Page
    {
        private NavigationHelper navigationHelper;
        private ObservableDictionary defaultViewModel = new ObservableDictionary();
        private SessionManager SessionManager = new SessionManager();
        private AuthenticationService AuthService = new AuthenticationService();
        private CentroService CentroService = new CentroService();

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

        public HubPage()
        {
            this.InitializeComponent();
            this.navigationHelper = new NavigationHelper(this);
            this.navigationHelper.LoadState += this.NavigationHelper_LoadState;
            this.SessionManager.Init();
            this.CheckSession();
        }

        private async void CheckSession()
        {
            this.ShowSections(await this.SessionManager.Exist("usuario"));
        }

        private async void ShowSections(bool show)
        {
            Sections.Visibility = show ? Visibility.Visible : Visibility.Collapsed;
            bottomAppBar.Visibility = show ? Visibility.Visible : Visibility.Collapsed;
            Login.Visibility = show ? Visibility.Collapsed : Visibility.Visible;
            
        }

        private void ClearInput_Click(object sender, RoutedEventArgs e)
        {
            ((TextBox)FindChildControl<TextBox>(LoginForm, "Usuario")).Text = "";
            ((PasswordBox)FindChildControl<PasswordBox>(LoginForm, "Password")).Password = "";
        }

        private async void Authenticate_Click(object sender, RoutedEventArgs e)
        {
            this.Loading_Show(true);
            string usuario = ((TextBox)FindChildControl<TextBox>(LoginForm, "Usuario")).Text;
            
            string password = HashPwd.GetHash(((PasswordBox)FindChildControl<PasswordBox>(LoginForm, "Password")).Password);

            bool remember = (bool)((AppBarToggleButton)FindChildControl<AppBarToggleButton>(LoginForm, "Remember")).IsChecked;
            
            if (usuario.Equals("") || password.Equals(""))
            {
                Alert.CreateAlert("Ingrese los datos correctos.", "Sistema CEM - Error en credenciales");
            }
            else
            {
                Object response = false;

                try
                {
                    response = await this.AuthService.aunthenticate(usuario, password);

                    if (response.Equals(false))
                        Alert.CreateAlert("Ingrese los datos correctos.", "Sistema CEM - Error en credenciales");
                    else
                    {
                        this.SessionManager.Add("usuario", response);
                        this.SessionManager.Add("remember", remember.ToString());
                        this.ShowSections(true);
                    }

                }
                catch (Exception)
                {
                    Alert.CreateAlert("Ocurrió un error al intentar conectar. Compruebe su conexión e intente más tarde.", "Sistema CEM - Error de conexión");
                }
            }

            this.Loading_Show(false);
        }

        private async void Logout_Click(object sender, RoutedEventArgs e)
        {
            if (await Alert.CreateConfirm("¿Está Seguro? Perderá los datos que no haya guardado.", "Cerrar sessión"))
            {
                this.SessionManager.Delete();
                this.ShowSections(false);
            }
        }

        private void Loading_Show(Boolean loading)
        {
            Button ClearInput = (Button)FindChildControl<Button>(LoginForm, "ClearInput");
            Button Authenticate = (Button)FindChildControl<Button>(LoginForm, "Authenticate");
            TextBlock ProcessTextLogin = (TextBlock)FindChildControl<TextBlock>(LoginForm, "ProcessTextLogin");
            ProgressRing ProgressLogin = (ProgressRing)FindChildControl<ProgressRing>(LoginForm, "ProgressLogin");

            if (loading)
            {
                ClearInput.Visibility = Visibility.Collapsed;
                Authenticate.Visibility = Visibility.Collapsed;
                ProcessTextLogin.Visibility = Visibility.Visible;
                ProgressLogin.IsActive = true;
                Window.Current.CoreWindow.PointerCursor = new Windows.UI.Core.CoreCursor(Windows.UI.Core.CoreCursorType.Wait, 10);
            }
            else
            {
                ClearInput.Visibility = Visibility.Visible;
                Authenticate.Visibility = Visibility.Visible;
                ProcessTextLogin.Visibility = Visibility.Collapsed;
                ProgressLogin.IsActive = false;
                Window.Current.CoreWindow.PointerCursor = new Windows.UI.Core.CoreCursor(Windows.UI.Core.CoreCursorType.Arrow, 0);
            }
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
            try
            {
                //this.ShowSections(false);
                if (await this.SessionManager.Exist("usuario"))
                {
                    var centros = await this.Find_Centros();

                    this.DefaultViewModel["Section2Items"] = centros;

                    ProgressRing LoadingSection = (ProgressRing)FindChildControl<ProgressRing>(Sections, "ProgressCentros");
                    LoadingSection.Visibility = Visibility.Collapsed;
                }

            } catch (Exception)
            {

            }
        }

        /// <summary>
        /// Invoked when a HubSection header is clicked.
        /// </summary>
        /// <param name="sender">The Hub that contains the HubSection whose header was clicked.</param>
        /// <param name="e">Event data that describes how the click was initiated.</param>
        void Hub_SectionHeaderClick(object sender, HubSectionHeaderClickEventArgs e)
        {
            HubSection section = e.Section;
            var group = section.DataContext;
            this.Frame.Navigate(typeof(SectionPage), ((SampleDataGroup)group).UniqueId);
        }

        /// <summary>
        /// Invoked when an item within a section is clicked.
        /// </summary>
        /// <param name="sender">The GridView or ListView
        /// displaying the item clicked.</param>
        /// <param name="e">Event data that describes the item clicked.</param>
        void ItemView_ItemClick(object sender, ItemClickEventArgs e)
        {
            // Navigate to the appropriate destination page, configuring the new page
            // by passing required information as a navigation parameter
            var itemId = ((CentroEntity)e.ClickedItem).Id_Centro;
            this.Frame.Navigate(typeof(ItemPage), itemId);
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

        private void Home_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(HubPage));
        }

        private async void Reload_Centros(object sender, RoutedEventArgs e)
        {
            
            ProgressRing LoadingSection = (ProgressRing)FindChildControl<ProgressRing>(Sections, "ProgressCentros");
            HubSection Section2Header = (HubSection)FindChildControl<HubSection>(Sections, "Section2Header");
            Section2Header.Visibility = Visibility.Collapsed;
            LoadingSection.Visibility = Visibility.Visible;
            var centros = await this.Find_Centros();

            this.DefaultViewModel["Section2Items"] = centros;
            Section2Header.Visibility = Visibility.Visible;
        }

        private async Task<Object> Find_Centros()
        {
            Object response = null;
            try
            {
                response = await this.CentroService.All();
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
                Alert.CreateAlert("Ocurrió un error al intentar conectar. Compruebe su conexión e intente más tarde.", "Sistema CEM - Error de conexión");
            }
            ProgressRing LoadingSection = (ProgressRing)FindChildControl<ProgressRing>(Sections, "ProgressCentros");
            LoadingSection.Visibility = Visibility.Collapsed;

            return response;
        }
        
    }
}

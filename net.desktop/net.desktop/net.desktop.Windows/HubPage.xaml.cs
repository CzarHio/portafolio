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

        private void ShowSections(bool show)
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
            string password = ((PasswordBox)FindChildControl<PasswordBox>(LoginForm, "Password")).Password;
            bool remember = (bool)((AppBarToggleButton)FindChildControl<AppBarToggleButton>(LoginForm, "Remember")).IsChecked;
            
            if (usuario.Equals("") || password.Equals(""))
            {
                Alert.CreateAlert("Ingrese los datos correctos.", "Sistema CEM - Error en credenciales");
            }
            else
            {
                this.SessionManager.Add("usuario", usuario);
                this.SessionManager.Add("remember", remember.ToString());
                await Task.Delay(2000);
                this.ShowSections(true);
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
            this.ShowSections(false);
            // TODO: Create an appropriate data model for your problem domain to replace the sample data
            var sampleDataGroup = await SampleDataSource.GetGroupAsync("Group-4");
            this.DefaultViewModel["Section3Items"] = sampleDataGroup;
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
            var itemId = ((SampleDataItem)e.ClickedItem).UniqueId;
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
        
    }
}

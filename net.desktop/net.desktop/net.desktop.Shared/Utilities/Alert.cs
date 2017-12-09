using System;
using System.Collections.Generic;
using System.Text;
using Windows.UI.Popups;
using System.Threading.Tasks;
using Windows.UI.Xaml.Controls;

namespace net.desktop.Utilities
{
    class Alert
    {
        
        public static async void CreateAlert(string content, string title = "Sistema CEM - Alerta")
        {
            var dialog = new MessageDialog(content, title);
            await dialog.ShowAsync();

        }

        public static async Task<Boolean> CreateConfirm(string content, string title = "Sistema CEM - Alerta")
        {
            var dialog = new MessageDialog(content, title);
            dialog.Commands.Add(new UICommand("Aceptar") { Id = 0 });
            dialog.Commands.Add(new UICommand("Cancelar") { Id = 1 });
            var result = await dialog.ShowAsync();

            return result.Id.ToString().Equals("0");
        }

    }

}

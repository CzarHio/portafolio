using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json.Linq;
using Windows.Storage;
using Windows.Globalization.DateTimeFormatting;
using Newtonsoft.Json;

namespace net.desktop.SessionManagerTools
{
    class SessionManager
    {
        private JObject Session;
        private StorageFolder localFolder = ApplicationData.Current.LocalFolder;
        private String SessionFile = "SessionFileCEM.data";
        private JObject SessionDefault = JObject.Parse(@"{}");

        public SessionManager()
        {
        }

        public async void Init()
        {
            try
            {
                await this.ReadDataSession();

                if (!(bool)this.Session["remember"])
                    this.Delete();
            }
            catch (JsonReaderException)
            {
            }

            if (this.Session == null)
            {
                this.Session = this.SessionDefault;
                this.WriteDataSession();
            }
        }

        public void Add(string key, string value)
        {
            this.CheckSesssion();
            this.Session.Add(key, value);
            this.WriteDataSession();
        }

        public string Get(string key)
        {
            this.CheckSesssion();

            return (string)this.Session[key];
        }

        public void Delete(string key = "")
        {
            if (key.Length > 0)
                this.Session.Remove(key);
            else
                this.Session.RemoveAll();

            this.WriteDataSession();
        }

        public async Task<bool> Exist(string key)
        {

            try
            {
                await this.ReadDataSession();
                return !this.Session[key].Equals("");
            }
            catch (Exception)
            {
            }

            return false;
        }

        private async void CheckSesssion()
        {
            await this.ReadDataSession();
        }
        
        // Write data to a file
        private async void WriteDataSession()
        {
            DateTimeFormatter formatter = new DateTimeFormatter("longtime");

            StorageFile sessionFile = await this.localFolder.CreateFileAsync(this.SessionFile,
                CreationCollisionOption.ReplaceExisting);

            try
            {
                await FileIO.WriteTextAsync(sessionFile, this.Session.ToString());
            }
            catch (Exception)
            {
                Debug.WriteLine("algo pasó");
            }
        }

        // Read data from a file
        private async Task ReadDataSession()
        {
            try
            {
                StorageFile sessionFile = await this.localFolder.GetFileAsync(this.SessionFile);
                String dataSession = await FileIO.ReadTextAsync(sessionFile);
                this.Session = JObject.Parse(@dataSession);
                // Data is contained in timestamp
            }
            catch (FileNotFoundException e)
            {
                // Cannot find file
            }
            catch (JsonReaderException e)
            {
            }
            catch (IOException e)
            {
                // Get information from the exception, then throw
                // the info to the parent method.
                if (e.Source != null)
                {
                    Debug.WriteLine("IOException source: {0}", e.Source);
                }
                throw;
            }
        }

    }
}

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

        /// <summary>
        /// Método que setea la configuración inicial de la sesión.
        /// </summary>
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

        /// <summary>
        /// Método que agrega un atributo a la sesión actual.
        /// </summary>
        /// <param name="key"></param>
        /// <param name="value"></param>
        public void Add(string key, string value)
        {
            this.CheckSesssion();
            this.Session.Add(key, value);
            this.WriteDataSession();
        }

        /// <summary>
        /// Método que retorna un atributo en específico de la sesión actual;
        /// </summary>
        /// <param name="key"></param>
        /// <returns>Devuelve el valor en formato string</returns>
        public string Get(string key)
        {
            this.CheckSesssion();

            return (string)this.Session[key];
        }

        /// <summary>
        /// Método que elimina un atributo de la variable de sesión actual,
        /// o en caso de no indicarlo, la sesión completa.
        /// </summary>
        /// <param name="key"></param>
        public void Delete(string key = "")
        {
            if (key.Length > 0)
                this.Session.Remove(key);
            else
                this.Session.RemoveAll();

            this.WriteDataSession();
        }

        /// <summary>
        /// Método que valida la existencia de un parámetro en particular,
        /// dentro de la variable de sesión actual.
        /// </summary>
        /// <param name="key"></param>
        /// <returns>Si existe o no</returns>
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

        /// <summary>
        /// Método que valida los datos de la sesión guardada en sistema de archivos
        /// </summary>
        private async void CheckSesssion()
        {
            await this.ReadDataSession();
        }
        
        /// <summary>
        /// Método que guarda los datos de la sesión actual en sistema de archivos
        /// </summary>
        private async void WriteDataSession()
        {
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
        
        /// <summary>
        /// Método que lee la información de la sesión en sistema de archivos
        /// </summary>
        /// <returns></returns>
        private async Task ReadDataSession()
        {
            try
            {
                StorageFile sessionFile = await this.localFolder.GetFileAsync(this.SessionFile);
                String dataSession = await FileIO.ReadTextAsync(sessionFile);
                this.Session = JObject.Parse(@dataSession);
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

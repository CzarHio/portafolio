using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceCiudad;

namespace net.desktop.Services
{
    class CiudadService
    {

        private CiudadWSClient Service = new CiudadWSClient();
        private RegionService RegionService = new RegionService();

        public async Task<Object> All(string key = null, string value = null)
        {
            CiudadEntity Ciudad;
            List<CiudadEntity> Ciudades = new List<CiudadEntity>();

            if (String.IsNullOrEmpty(key) && String.IsNullOrEmpty(value))
            {
                findAllCiudadResponse Response = await this.Service.findAllCiudadAsync();
                foreach (ciudad c in Response.@return)
                {
                    Ciudad = new CiudadEntity();
                    Ciudad.Id_Ciudad = (int)c.idCiudad;
                    Ciudad.Nombre_Ciudad = c.nombreCiudad;
                    Ciudad.Region = await this.RegionService.Find(c.idRegion);
                    Ciudades.Add(Ciudad);
                }

                return Ciudades;
                //return centros.FirstOrDefault(c => c.Id_Centro == 1);
            }
            else
            {
                findCiudadPorResponse Response = await this.Service.findCiudadPorAsync(key, value);

                Debug.WriteLine(Response.@return);
                return Response.@return;
            }
        }

        public async Task<CiudadEntity> Find(int id)
        {
            CiudadEntity Ciudad = new CiudadEntity();
            findCiudadResponse Response = await this.Service.findCiudadAsync(id);
            Ciudad.Id_Ciudad = (int)Response.@return.idCiudad;
            Ciudad.Nombre_Ciudad = Response.@return.nombreCiudad;
            Ciudad.Region = await this.RegionService.Find(Response.@return.idRegion);

            return Ciudad;
        }
    }
}

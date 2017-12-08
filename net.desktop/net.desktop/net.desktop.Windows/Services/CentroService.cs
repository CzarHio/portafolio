using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.WebServiceCentro;
using net.desktop.Entities;
using System.Diagnostics;

namespace net.desktop.Services
{
    class CentroService
    {
        private CentroWSClient Service = new CentroWSClient();
        private UsuarioService UsuarioService = new UsuarioService();
        private CiudadService CiudadService = new CiudadService();
        private FotoService FotoService = new FotoService();

        public async Task<Object> All(string key = null, string value = null)
        {
            CentroEntity Centro;
            List<CentroEntity> Centros = new List<CentroEntity>();

            if (String.IsNullOrEmpty(key) && String.IsNullOrEmpty(value))
            {
                findAllCentroResponse Response = await this.Service.findAllCentroAsync();
                foreach (centro c in Response.@return)
                {
                    Centro = new CentroEntity();
                    Centro.Id_Centro = (int)c.idCentro;
                    Centro.Nombre_Centro = c.nombreCentro;
                    Centro.Ciudad = await this.CiudadService.Find(c.idCiudad); ;
                    Centro.Usuario = await this.UsuarioService.Find(c.idUsuario);
                    Centro.Foto = await this.FotoService.Find("2", (int) c.idCentro);
                    Centros.Add(Centro); 
                }

                return Centros;
                //return Centros.FirstOrDefault(c => c.Id_Centro == 1);
            }
            else
            {
                findCentroPorResponse Response = await this.Service.findCentroPorAsync(key, value);

                Debug.WriteLine(Response.@return);
                return Response.@return;
            }
        }

        public async Task<CentroEntity> Find(int id)
        {
            CentroEntity Centro = new CentroEntity();
            findCentroResponse Response = await this.Service.findCentroAsync(id);
            Centro.Id_Centro = (int)Response.@return.idCentro;
            Centro.Nombre_Centro = Response.@return.nombreCentro;
            Centro.Ciudad = await this.CiudadService.Find(Response.@return.idCiudad); ;
            Centro.Usuario = await this.UsuarioService.Find(Response.@return.idUsuario);
            Centro.Foto = await this.FotoService.Find("2", (int)Response.@return.idCentro);

            return Centro;
        }
    }
}

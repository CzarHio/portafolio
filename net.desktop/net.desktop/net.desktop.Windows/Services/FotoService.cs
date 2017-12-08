using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceFoto;

namespace net.desktop.Services
{
    class FotoService
    {

        private FotoWSClient Service = new FotoWSClient();

        public async Task<FotoEntity> Find(string tipo, int id)
        {
            FotoEntity Foto = new FotoEntity();
            findInstanciaFotoResponse Response = await this.Service.findInstanciaFotoAsync(tipo, id);

            if (Response.@return != null)
            {
                foto f = Response.@return.First();

                Foto.Id_Foto = (int)Response.@return.First().idFoto;
                Foto.Nombre_Archivo = "http://localhost:8080" + Response.@return.First().nombreArchivo;
                Foto.Orden = Int32.Parse(Response.@return.First().orden);
                Foto.Titulo = Response.@return.First().titulo;
                //Foto.Representante = await this.UsuarioService.Find((int)Response.@return.idUsuario);
                //Foto.Centro = await this.CentroService.Find((int)Response.@return.idCentro);

            }
            return Foto;
        }
    }
}

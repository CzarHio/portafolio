using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceUsuario;

namespace net.desktop.Services
{
    class UsuarioService
    {
        private UsuarioWSClient Service = new UsuarioWSClient();

        public async Task<UsuarioEntity> Find(int id)
        {
            UsuarioEntity Usuario = new UsuarioEntity();
            findUsuarioResponse Response = await this.Service.findUsuarioAsync(id);
            Usuario.Id_Usuario = (int)Response.@return.idUsuario;
            Usuario.Nombre = Response.@return.nombre;
            Usuario.Apellido_Pat = Response.@return.apellidoPat;
            Usuario.Apellido_Mat = Response.@return.apellidoMat;
            Usuario.Nombre_Completo = Usuario.Nombre + " " + Usuario.Apellido_Pat + " " + Usuario.Apellido_Mat;

            return Usuario;
        }
    }
}

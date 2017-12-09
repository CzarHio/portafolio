using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServicePerfil;

namespace net.desktop.Services
{
    class PerfilService
    {
        private PerfilUsuarioWSClient Service = new PerfilUsuarioWSClient();

        
        public async Task<PerfilEntity> Find(int id)
        {
            PerfilEntity Perfil = new PerfilEntity();
            findPerfilUsuarioResponse Response = await this.Service.findPerfilUsuarioAsync(id);

            Perfil.Id_Perfil = (int)Response.@return.idPerfilUsuario;
            Perfil.Nombre_Perfil = Response.@return.nombrePerfil;

            return Perfil;
        }
    }
}

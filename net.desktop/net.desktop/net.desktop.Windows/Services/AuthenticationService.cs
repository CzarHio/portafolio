using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceUsuario;

namespace net.desktop.Services
{
    class AuthenticationService
    {
        private UsuarioWSClient service = new UsuarioWSClient();
        private FotoService FotoService = new FotoService();
        private PerfilService PerfilService = new PerfilService();
        private autenticarResponse response;
        private UsuarioEntity usuario;

        public async Task<Object> aunthenticate(string usuario, string clave)
        {
            
            this.response = await this.service.autenticarAsync(usuario, clave);
            
            if (this.response.@return == null)
                return false;
            else
            {
                this.usuario = new UsuarioEntity();
                this.usuario.Id_Usuario = (int)this.response.@return.idUsuario;
                this.usuario.Nombre = this.response.@return.nombre;
                this.usuario.Nombre_Completo = this.response.@return.nombre +  " " + this.response.@return.apellidoPat + " " + this.response.@return.apellidoMat;
                this.usuario.Usuario = this.response.@return.usuario1;
                this.usuario.Email = this.response.@return.email;
                this.usuario.Apellido_Pat = this.response.@return.apellidoPat;
                this.usuario.Apellido_Mat = this.response.@return.apellidoMat;
                this.usuario.Foto = await this.FotoService.Find("1", (int)this.response.@return.idUsuario);
                this.usuario.Perfil = await this.PerfilService.Find((int)this.response.@return.idPerfilUsuario);

                return this.usuario;
            }
        }
    }
}

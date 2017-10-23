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
                this.usuario.Usuario = this.response.@return.usuario1;
                this.usuario.Email = this.response.@return.email;
                this.usuario.Apellido_Pat = this.response.@return.apellidoPat;
                this.usuario.Apellido_Mat = this.response.@return.apellidoMat;
                //this.usuario.Clave = this.response.@return.clave;
                //this.usuario.Perfil_Usuario = this.response.@return.idPerfilUsuario.idPerfilUsuario;
                //this.usuario.Token = this.response.@return.token;
                //this.usuario.Creado = this.response.@return.creado;

                return this.usuario;
            }
        }
    }
}

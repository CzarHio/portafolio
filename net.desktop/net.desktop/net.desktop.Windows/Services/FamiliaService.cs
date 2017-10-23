using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceFamilia;

namespace net.desktop.Services
{
    class FamiliaService
    {

        private FamiliaWSClient Service = new FamiliaWSClient();
        private UsuarioService UsuarioService = new UsuarioService();
        private CentroService CentroService = new CentroService();

        public async Task<FamiliaEntity> Find(int id)
        {
            FamiliaEntity Familia = new FamiliaEntity();
            findFamiliaResponse Response = await this.Service.findFamiliaAsync(id);
            Familia.Id_Familia = (int)Response.@return.idFamilia;
            Familia.Representante = await this.UsuarioService.Find((int)Response.@return.idUsuario);
            Familia.Centro = await this.CentroService.Find((int)Response.@return.idCentro);

            return Familia;
        }
    }
}

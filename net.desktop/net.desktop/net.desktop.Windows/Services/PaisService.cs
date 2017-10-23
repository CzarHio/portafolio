using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServicePais;

namespace net.desktop.Services
{
    class PaisService
    {
        private PaisWSClient Service = new PaisWSClient();

        public async Task<PaisEntity> Find(int id)
        {
            PaisEntity Pais = new PaisEntity();
            findPaisResponse Response = await this.Service.findPaisAsync(id);
            Pais.Id_Pais = (int)Response.@return.idPais;
            Pais.Nombre_Pais = Response.@return.nombrePais;

            return Pais;
        }
    }
}

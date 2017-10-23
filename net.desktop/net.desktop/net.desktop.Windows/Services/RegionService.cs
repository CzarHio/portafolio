using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceRegion;

namespace net.desktop.Services
{
    class RegionService
    {
        private RegionWSClient Service = new RegionWSClient();
        private PaisService PaisService = new PaisService();

        public async Task<RegionEntity> Find(int id)
        {
            RegionEntity Region = new RegionEntity();
            findRegionResponse Response = await this.Service.findRegionAsync(id);
            Region.Id_Region = (int)Response.@return.idRegion;
            Region.Nombre_Region = Response.@return.nombreRegion;
            Region.Pais = await this.PaisService.Find(Response.@return.idPais);

            return Region;
        }
    }
}

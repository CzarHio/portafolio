using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServicePrograma;

namespace net.desktop.Services
{
    class ProgramaService
    {
        private ProgramaWSClient Service = new ProgramaWSClient();
        private PaisService PaisService = new PaisService();

        public async Task<ProgramaEntity> Find(int id)
        {
            ProgramaEntity Programa = new ProgramaEntity();
            findProgramaResponse Response = await this.Service.findProgramaAsync(id);
            Programa.Id_Programa = (int)Response.@return.idPrograma;
            Programa.Nombre_Programa = Response.@return.nombrePrograma;
            Programa.Fecha_Creacion = Response.@return.fechaCreacion.ToString();
            Programa.Pais = await this.PaisService.Find(Response.@return.idPais);

            return Programa;
        }
    }
}

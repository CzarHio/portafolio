using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceCurso;

namespace net.desktop.Services
{
    class CursoService
    {

        private CursoWSClient Service = new CursoWSClient();

        public async Task<CursoEntity> Find(int id)
        {
            CursoEntity Curso = new CursoEntity();
            findCursoResponse Response = await this.Service.findCursoAsync(id);
            Curso.Id_Curso = (int)Response.@return.idCurso;
            Curso.Nombre_Curso = Response.@return.nombreCurso;

            return Curso;
        }
    }
}

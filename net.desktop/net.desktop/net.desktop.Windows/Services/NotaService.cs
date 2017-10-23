using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceNota;

namespace net.desktop.Services
{
    class NotaService
    {

        private NotaWSClient Service = new NotaWSClient();
        private CursoService CursoService = new CursoService();

        public async Task<List<NotaEntity>> All(string key = null, string value = null)
        {
            NotaEntity Nota;
            List<NotaEntity> Notas = new List<NotaEntity>();

            if (String.IsNullOrEmpty(key) && String.IsNullOrEmpty(value))
            {
                findAllNotaResponse Response = await this.Service.findAllNotaAsync();

                foreach (nota p in Response.@return)
                {
                    Nota = new NotaEntity();
                    Nota.Id_Nota = (int)p.idNota;
                    Nota.Nota = p.nota1;
                    Nota.Curso = await this.CursoService.Find((int)p.idCurso);
                    Notas.Add(Nota);
                }

                return Notas;
            }
            else
            {
                findNotaPorResponse Response = await this.Service.findNotaPorAsync(key, value);

                foreach (nota p in Response.@return)
                {
                    Nota = new NotaEntity();
                    Nota.Id_Nota = (int)p.idNota;
                    Nota.Nota = p.nota1;
                    Nota.Curso = await this.CursoService.Find((int)p.idCurso);
                    Notas.Add(Nota);
                }

                return Notas;
            }
        }
    }
}

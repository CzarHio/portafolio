using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServiceParticipacion;

namespace net.desktop.Services
{
    class ParticipacionService
    {

        private ParticipacionWSClient Service = new ParticipacionWSClient();
        private ProgramaService ProgramaService = new ProgramaService();
        private PostulacionService PostulacionService = new PostulacionService();

        public async Task<List<ParticipacionEntity>> All(string key = null, string value = null)
        {
            ParticipacionEntity Participacion;
            List<ParticipacionEntity> Participaciones = new List<ParticipacionEntity>();

            if (String.IsNullOrEmpty(key) && String.IsNullOrEmpty(value))
            {
                findAllParticipacionResponse Response = await this.Service.findAllParticipacionAsync();

                foreach (participacion p in Response.@return)
                {
                    Participacion = new ParticipacionEntity();
                    Participacion.Id_Participacion = (int)p.idParticipacion;
                    Participacion.Programa = await this.ProgramaService.Find((int)p.idPrograma);

                    try
                    {
                        Participacion.Postulaciones = await this.PostulacionService.All("id_participacion", ((int)p.idParticipacion).ToString());
                    } catch (Exception e)
                    {
                        Debug.WriteLine(e);
                    }

                    Participaciones.Add(Participacion);
                }

                return Participaciones;
            }
            else
            {
                findParticipacionPorResponse Response = await this.Service.findParticipacionPorAsync(key, value);
                
                foreach (participacion p in Response.@return)
                {
                    
                    Participacion = new ParticipacionEntity();
                    Participacion.Id_Participacion = (int)p.idParticipacion;
                    Participacion.Programa = await this.ProgramaService.Find((int)p.idPrograma); ;

                    try
                    {
                        Participacion.Postulaciones = await this.PostulacionService.All("id_participacion", ((int)p.idParticipacion).ToString());
                    }
                    catch (Exception e) {
                        Debug.WriteLine(e);
                    }

                    Participaciones.Add(Participacion);
                }

                return Participaciones;
            }
        }

    }
}

using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using net.desktop.Entities;
using net.desktop.WebServicePostulacion;

namespace net.desktop.Services
{
    class PostulacionService
    {

        private PostulacionWSClient Service = new PostulacionWSClient();
        private UsuarioService UsuarioService = new UsuarioService();
        private NotaService NotaService = new NotaService();
        private FamiliaService FamiliaService = new FamiliaService();

        public async Task<List<PostulacionEntity>> All(string key = null, string value = null)
        {
            PostulacionEntity Postulacion;
            List<PostulacionEntity> Postulaciones = new List<PostulacionEntity>();

            if (String.IsNullOrEmpty(key) && String.IsNullOrEmpty(value))
            {
                findAllPostulacionResponse Response = await this.Service.findAllPostulacionAsync();

                foreach (postulacion p in Response.@return)
                {
                    Postulacion = new PostulacionEntity();
                    Postulacion.Id_Postulacion = (int)p.idPostulacion;
                    Postulacion.Alumno = await this.UsuarioService.Find((int)p.idUsuario);
                    Postulacion.Familia = await this.FamiliaService.Find((int)p.idFamilia);
                    Postulacion.Fecha_Creacion = p.fechaCreacion.ToString();
                    try
                    {
                        Postulacion.Notas = await this.NotaService.All("id_postulacion", ((int)p.idPostulacion).ToString());
                    }
                    catch (Exception) { }
                    Postulaciones.Add(Postulacion);
                }

                return Postulaciones;
            }
            else
            {
                findPostulacionFullPorResponse Response = await this.Service.findPostulacionFullPorAsync(key, value);

                foreach (postulacion p in Response.@return)
                {
                    Postulacion = new PostulacionEntity();
                    Postulacion.Id_Postulacion = (int)p.idPostulacion;
                    //Postulacion.Alumno = await this.UsuarioService.Find((int)p.idUsuario);
                    //Postulacion.Familia = await this.FamiliaService.Find((int)p.idFamilia);
                    //Postulacion.Fecha_Creacion = p.fechaCreacion.ToString();
                    Postulacion.Nombre_Alumno = "ALUMNO: " + p.nombreAlumno;
                    Postulacion.Nombre_Familia = "FAMILIA: " + p.nombreFamilia;
                    try
                    {
                        Postulacion.Notas = await this.NotaService.All("id_postulacion", ((int)p.idPostulacion).ToString());
                    }
                    catch (Exception) { }
                    Postulaciones.Add(Postulacion);
                }

                return Postulaciones;
            }
        }
    }
}

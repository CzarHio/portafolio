using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class PostulacionEntity
    {
        public int Id_Postulacion
        {
            set;
            get;
        }

        public UsuarioEntity Alumno
        {
            set;
            get;
        }

        public FamiliaEntity Familia
        {
            set;
            get;
        }

        public List<NotaEntity> Notas
        {
            set;
            get;
        }

        public string Fecha_Creacion
        {
            set;
            get;
        }

        public string Nombre_Alumno
        {
            set;
            get;
        }

        public string Nombre_Familia
        {
            set;
            get;
        }
    }
}

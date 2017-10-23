using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class CursoEntity
    {

        public int Id_Curso
        {
            get;
            set;
        }

        public string Nombre_Curso
        {
            get;
            set;
        }

        public ProgramaEntity Programa
        {
            get;
            set;
        }
    }
}

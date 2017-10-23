using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class ProgramaEntity
    {

        public int Id_Programa
        {
            set;
            get;
        }

        public string Nombre_Programa
        {
            set;
            get;
        }

        public string Fecha_Creacion
        {
            set;
            get;
        }

        public PaisEntity Pais
        {
            set;
            get;
        }
    }
}

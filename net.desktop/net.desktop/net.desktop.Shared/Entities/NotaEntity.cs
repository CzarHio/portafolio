using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class NotaEntity
    {

        public int Id_Nota
        {
            get;
            set;
        }

        public CursoEntity Curso
        {
            get;
            set;
        }
        public string Nota
        {
            get;
            set;
        }
    }
}

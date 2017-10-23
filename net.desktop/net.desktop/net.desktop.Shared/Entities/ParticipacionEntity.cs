using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class ParticipacionEntity
    {

        public int Id_Participacion
        {
            set;
            get;
        }

        public ProgramaEntity Programa
        {
            set;
            get;
        }

        public List<PostulacionEntity> Postulaciones
        {
            set;
            get;
        }
    }
}

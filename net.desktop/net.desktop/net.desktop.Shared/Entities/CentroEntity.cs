using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class CentroEntity
    {

        public int Id_Centro
        {
            set;
            get;
        }

        public string Nombre_Centro
        {
            set;
            get;
        }

        public string Nombre_Ciudad
        {
            set;
            get;
        }

        public string Nombre_Pais
        {
            set;
            get;
        }

        public string Nombre_Region
        {
            set;
            get;
        }

        public string Nombre_Usuario
        {
            set;
            get;
        }

        public FotoEntity Foto
        {
            set;
            get;
        }

        public List<ParticipacionEntity> Participaciones
        {
            set;
            get;
        }
    }
}

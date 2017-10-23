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

        public CiudadEntity Ciudad
        {
            set;
            get;
        }

        public UsuarioEntity Usuario
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

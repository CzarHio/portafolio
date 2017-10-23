using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class CiudadEntity
    {

        public int Id_Ciudad
        {
            set;
            get;
        }

        public string Nombre_Ciudad
        {
            set;
            get;
        }

        public RegionEntity Region
        {
            set;
            get;
        }
    }
}

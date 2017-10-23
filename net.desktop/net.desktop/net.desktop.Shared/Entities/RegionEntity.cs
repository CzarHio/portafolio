using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class RegionEntity
    {

        public int Id_Region
        {
            set;
            get;
        }

        public string Nombre_Region
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

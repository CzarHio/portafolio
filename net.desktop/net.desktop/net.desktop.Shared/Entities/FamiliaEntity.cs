using System;
using System.Collections.Generic;
using System.Text;

namespace net.desktop.Entities
{
    class FamiliaEntity
    {

        public int Id_Familia
        {
            set;
            get;
        }

        public UsuarioEntity Representante
        {
            set;
            get;
        }
        
        public CentroEntity Centro
        {
            set;
            get;
        }
    }
}

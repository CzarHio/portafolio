using System;
using System.Collections.Generic;
using System.Text;
using Windows.Security.Cryptography;
using Windows.Security.Cryptography.Core;
using Windows.Storage.Streams;

namespace net.desktop.Utilities
{
    class HashPwd
    {

        private static uint SALT_LEN = 7;

        /**
         * Genera un string plano con dos secciones, una random y la contraseña MD5
         * complementada con una APP_KEY obtenida desde las propiedades de la aplicación.
         * Contraseñas vacías no son permitidas.
         * 
         * @param password
         * @return texto plano con hash
         * @throws java.lang.Exception */
        public static string GetHash(string password)
        {
            
            IBuffer salt = CryptographicBuffer.GenerateRandom(SALT_LEN);
            string APP_KEY = "srgw4g347weh578ber67nw6cw45rgEtq35QGW";

            return CryptographicBuffer.EncodeToBase64String(salt) + "$" + Hash(password, APP_KEY);
        }
    
        /**
         * Genera el hashe MD5 de la contraseña.
         * 
         * @param password
         * @param key
         * @return string plano con hash */
        private static string Hash(string password, string key)
        {
            if (password == null || password.Length == 0)
                throw new Exception("Empty passwords are not supported.");

            string AlgName = HashAlgorithmNames.Sha512;
            IBuffer buffUtf8Msg = CryptographicBuffer.ConvertStringToBinary(password + key, BinaryStringEncoding.Utf8);
            HashAlgorithmProvider objAlgProv = HashAlgorithmProvider.OpenAlgorithm(AlgName);
            IBuffer buffHash = objAlgProv.HashData(buffUtf8Msg);

            if (buffHash.Length != objAlgProv.HashLength)
            {
                throw new Exception("There was an error creating the hash");
            }

            string strHash = CryptographicBuffer.EncodeToHexString(buffHash);

            // Return the encoded string
            return strHash;
            
        }
    }
}

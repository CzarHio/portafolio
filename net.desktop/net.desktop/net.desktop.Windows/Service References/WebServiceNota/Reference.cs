﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// This code was auto-generated by Microsoft.VisualStudio.ServiceReference.Platforms, version 14.0.23107.0
// 
namespace net.desktop.WebServiceNota {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", ConfigurationName="WebServiceNota.NotaWS")]
    public interface NotaWS {
        
        [System.ServiceModel.OperationContractAttribute(IsOneWay=true, Action="http://ws.cem.pft8461.duoc.cl/NotaWS/createNota")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task createNotaAsync(net.desktop.WebServiceNota.createNota request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://ws.cem.pft8461.duoc.cl/NotaWS/findAllNotaRequest", ReplyAction="http://ws.cem.pft8461.duoc.cl/NotaWS/findAllNotaResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<net.desktop.WebServiceNota.findAllNotaResponse> findAllNotaAsync(net.desktop.WebServiceNota.findAllNotaRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://ws.cem.pft8461.duoc.cl/NotaWS/findNotaPorRequest", ReplyAction="http://ws.cem.pft8461.duoc.cl/NotaWS/findNotaPorResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<net.desktop.WebServiceNota.findNotaPorResponse> findNotaPorAsync(net.desktop.WebServiceNota.findNotaPorRequest request);
        
        [System.ServiceModel.OperationContractAttribute(IsOneWay=true, Action="http://ws.cem.pft8461.duoc.cl/NotaWS/removeNota")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task removeNotaAsync(net.desktop.WebServiceNota.removeNota request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://ws.cem.pft8461.duoc.cl/NotaWS/findNotaRequest", ReplyAction="http://ws.cem.pft8461.duoc.cl/NotaWS/findNotaResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task<net.desktop.WebServiceNota.findNotaResponse> findNotaAsync(net.desktop.WebServiceNota.findNotaRequest request);
        
        [System.ServiceModel.OperationContractAttribute(IsOneWay=true, Action="http://ws.cem.pft8461.duoc.cl/NotaWS/editNota")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        System.Threading.Tasks.Task editNotaAsync(net.desktop.WebServiceNota.editNota request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="createNota", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class createNota {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int id_postulacion;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=1)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int id_curso;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=2)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int nota;
        
        public createNota() {
        }
        
        public createNota(int id_postulacion, int id_curso, int nota) {
            this.id_postulacion = id_postulacion;
            this.id_curso = id_curso;
            this.nota = nota;
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.7.2102.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/")]
    public partial class nota : object, System.ComponentModel.INotifyPropertyChanged {
        
        private int idCursoField;
        
        private decimal idNotaField;
        
        private bool idNotaFieldSpecified;
        
        private int idPostulacionField;
        
        private string nota1Field;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public int idCurso {
            get {
                return this.idCursoField;
            }
            set {
                this.idCursoField = value;
                this.RaisePropertyChanged("idCurso");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public decimal idNota {
            get {
                return this.idNotaField;
            }
            set {
                this.idNotaField = value;
                this.RaisePropertyChanged("idNota");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idNotaSpecified {
            get {
                return this.idNotaFieldSpecified;
            }
            set {
                this.idNotaFieldSpecified = value;
                this.RaisePropertyChanged("idNotaSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public int idPostulacion {
            get {
                return this.idPostulacionField;
            }
            set {
                this.idPostulacionField = value;
                this.RaisePropertyChanged("idPostulacion");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("nota", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, DataType="integer", Order=3)]
        public string nota1 {
            get {
                return this.nota1Field;
            }
            set {
                this.nota1Field = value;
                this.RaisePropertyChanged("nota1");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findAllNota", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class findAllNotaRequest {
        
        public findAllNotaRequest() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findAllNotaResponse", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class findAllNotaResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public net.desktop.WebServiceNota.nota[] @return;
        
        public findAllNotaResponse() {
        }
        
        public findAllNotaResponse(net.desktop.WebServiceNota.nota[] @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findNotaPor", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class findNotaPorRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string campo;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=1)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string valor;
        
        public findNotaPorRequest() {
        }
        
        public findNotaPorRequest(string campo, string valor) {
            this.campo = campo;
            this.valor = valor;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findNotaPorResponse", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class findNotaPorResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public net.desktop.WebServiceNota.nota[] @return;
        
        public findNotaPorResponse() {
        }
        
        public findNotaPorResponse(net.desktop.WebServiceNota.nota[] @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="removeNota", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class removeNota {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int entity;
        
        public removeNota() {
        }
        
        public removeNota(int entity) {
            this.entity = entity;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findNota", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class findNotaRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int id;
        
        public findNotaRequest() {
        }
        
        public findNotaRequest(int id) {
            this.id = id;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findNotaResponse", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class findNotaResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public net.desktop.WebServiceNota.nota @return;
        
        public findNotaResponse() {
        }
        
        public findNotaResponse(net.desktop.WebServiceNota.nota @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="editNota", WrapperNamespace="http://ws.cem.pft8461.duoc.cl/", IsWrapped=true)]
    public partial class editNota {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int id_nota;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=1)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int id_postulacion;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=2)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int id_curso;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://ws.cem.pft8461.duoc.cl/", Order=3)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public int nota;
        
        public editNota() {
        }
        
        public editNota(int id_nota, int id_postulacion, int id_curso, int nota) {
            this.id_nota = id_nota;
            this.id_postulacion = id_postulacion;
            this.id_curso = id_curso;
            this.nota = nota;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface NotaWSChannel : net.desktop.WebServiceNota.NotaWS, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class NotaWSClient : System.ServiceModel.ClientBase<net.desktop.WebServiceNota.NotaWS>, net.desktop.WebServiceNota.NotaWS {
        
        /// <summary>
        /// Implement this partial method to configure the service endpoint.
        /// </summary>
        /// <param name="serviceEndpoint">The endpoint to configure</param>
        /// <param name="clientCredentials">The client credentials</param>
        static partial void ConfigureEndpoint(System.ServiceModel.Description.ServiceEndpoint serviceEndpoint, System.ServiceModel.Description.ClientCredentials clientCredentials);
        
        public NotaWSClient() : 
                base(NotaWSClient.GetDefaultBinding(), NotaWSClient.GetDefaultEndpointAddress()) {
            this.Endpoint.Name = EndpointConfiguration.NotaWSPort.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public NotaWSClient(EndpointConfiguration endpointConfiguration) : 
                base(NotaWSClient.GetBindingForEndpoint(endpointConfiguration), NotaWSClient.GetEndpointAddress(endpointConfiguration)) {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public NotaWSClient(EndpointConfiguration endpointConfiguration, string remoteAddress) : 
                base(NotaWSClient.GetBindingForEndpoint(endpointConfiguration), new System.ServiceModel.EndpointAddress(remoteAddress)) {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public NotaWSClient(EndpointConfiguration endpointConfiguration, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(NotaWSClient.GetBindingForEndpoint(endpointConfiguration), remoteAddress) {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public NotaWSClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task net.desktop.WebServiceNota.NotaWS.createNotaAsync(net.desktop.WebServiceNota.createNota request) {
            return base.Channel.createNotaAsync(request);
        }
        
        public System.Threading.Tasks.Task createNotaAsync(int id_postulacion, int id_curso, int nota) {
            net.desktop.WebServiceNota.createNota inValue = new net.desktop.WebServiceNota.createNota();
            inValue.id_postulacion = id_postulacion;
            inValue.id_curso = id_curso;
            inValue.nota = nota;
            return ((net.desktop.WebServiceNota.NotaWS)(this)).createNotaAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<net.desktop.WebServiceNota.findAllNotaResponse> net.desktop.WebServiceNota.NotaWS.findAllNotaAsync(net.desktop.WebServiceNota.findAllNotaRequest request) {
            return base.Channel.findAllNotaAsync(request);
        }
        
        public System.Threading.Tasks.Task<net.desktop.WebServiceNota.findAllNotaResponse> findAllNotaAsync() {
            net.desktop.WebServiceNota.findAllNotaRequest inValue = new net.desktop.WebServiceNota.findAllNotaRequest();
            return ((net.desktop.WebServiceNota.NotaWS)(this)).findAllNotaAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<net.desktop.WebServiceNota.findNotaPorResponse> net.desktop.WebServiceNota.NotaWS.findNotaPorAsync(net.desktop.WebServiceNota.findNotaPorRequest request) {
            return base.Channel.findNotaPorAsync(request);
        }
        
        public System.Threading.Tasks.Task<net.desktop.WebServiceNota.findNotaPorResponse> findNotaPorAsync(string campo, string valor) {
            net.desktop.WebServiceNota.findNotaPorRequest inValue = new net.desktop.WebServiceNota.findNotaPorRequest();
            inValue.campo = campo;
            inValue.valor = valor;
            return ((net.desktop.WebServiceNota.NotaWS)(this)).findNotaPorAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task net.desktop.WebServiceNota.NotaWS.removeNotaAsync(net.desktop.WebServiceNota.removeNota request) {
            return base.Channel.removeNotaAsync(request);
        }
        
        public System.Threading.Tasks.Task removeNotaAsync(int entity) {
            net.desktop.WebServiceNota.removeNota inValue = new net.desktop.WebServiceNota.removeNota();
            inValue.entity = entity;
            return ((net.desktop.WebServiceNota.NotaWS)(this)).removeNotaAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<net.desktop.WebServiceNota.findNotaResponse> net.desktop.WebServiceNota.NotaWS.findNotaAsync(net.desktop.WebServiceNota.findNotaRequest request) {
            return base.Channel.findNotaAsync(request);
        }
        
        public System.Threading.Tasks.Task<net.desktop.WebServiceNota.findNotaResponse> findNotaAsync(int id) {
            net.desktop.WebServiceNota.findNotaRequest inValue = new net.desktop.WebServiceNota.findNotaRequest();
            inValue.id = id;
            return ((net.desktop.WebServiceNota.NotaWS)(this)).findNotaAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task net.desktop.WebServiceNota.NotaWS.editNotaAsync(net.desktop.WebServiceNota.editNota request) {
            return base.Channel.editNotaAsync(request);
        }
        
        public System.Threading.Tasks.Task editNotaAsync(int id_nota, int id_postulacion, int id_curso, int nota) {
            net.desktop.WebServiceNota.editNota inValue = new net.desktop.WebServiceNota.editNota();
            inValue.id_nota = id_nota;
            inValue.id_postulacion = id_postulacion;
            inValue.id_curso = id_curso;
            inValue.nota = nota;
            return ((net.desktop.WebServiceNota.NotaWS)(this)).editNotaAsync(inValue);
        }
        
        public virtual System.Threading.Tasks.Task OpenAsync() {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginOpen(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndOpen));
        }
        
        public virtual System.Threading.Tasks.Task CloseAsync() {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginClose(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndClose));
        }
        
        private static System.ServiceModel.Channels.Binding GetBindingForEndpoint(EndpointConfiguration endpointConfiguration) {
            if ((endpointConfiguration == EndpointConfiguration.NotaWSPort)) {
                System.ServiceModel.BasicHttpBinding result = new System.ServiceModel.BasicHttpBinding();
                result.MaxBufferSize = int.MaxValue;
                result.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
                result.MaxReceivedMessageSize = int.MaxValue;
                result.AllowCookies = true;
                return result;
            }
            throw new System.InvalidOperationException(string.Format("Could not find endpoint with name \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.EndpointAddress GetEndpointAddress(EndpointConfiguration endpointConfiguration) {
            if ((endpointConfiguration == EndpointConfiguration.NotaWSPort)) {
                return new System.ServiceModel.EndpointAddress("http://ws.huevoscopita.cl/webservice/NotaWS");
            }
            throw new System.InvalidOperationException(string.Format("Could not find endpoint with name \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.Channels.Binding GetDefaultBinding() {
            return NotaWSClient.GetBindingForEndpoint(EndpointConfiguration.NotaWSPort);
        }
        
        private static System.ServiceModel.EndpointAddress GetDefaultEndpointAddress() {
            return NotaWSClient.GetEndpointAddress(EndpointConfiguration.NotaWSPort);
        }
        
        public enum EndpointConfiguration {
            
            NotaWSPort,
        }
    }
}
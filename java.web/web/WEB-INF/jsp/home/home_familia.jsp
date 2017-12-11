<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="well"><h1>Bienvenido Familia ${sessionScope.id_familia}</h1></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Postulaciones a Programas</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Centro</th>
                                    <th>Programa</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaPostulaciones}" var="participacion">
                                    <tr>
                                        <td>${participacion.getNombreCentro()} </td>
                                        <td>${participacion.getNombrePrograma()}</td>
                                        <td>${participacion.getFecha()}</td>
                                        <td>${participacion.getEstado()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>  
        </div>

        <div class="row">
            <div class="col-md-12">
                <a class="btn btn-warning btnFiles" data-url="/java.web/familia/archivos.htm" data-toggle="tooltip" data-original-title="Antecedentes" data-id="${sessionScope.id_familia}">
                    <i class="fa fa-files-o"></i> Subir Documentos
                </a>
            </div>  
        </div>
            
        <div class="modal fade" id="archivos" style="display: none;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="form-horizontal cursoForm" id="addArchivo">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Documentos</h4>
                        </div>
                        <form class="form-horizontal" action="/java.web/familia/upload.htm" method="post" enctype="multipart/form-data">
                            <div class="modal-body">
                                <table class="table table-bordered table-hover" id="table-files">
                                    <thead>
                                        <tr>
                                            <th>Tipo</th>
                                            <th>Título</th>
                                            <th>Descripción</th>
                                            <th>Revisión</th>
                                            <th>Estado</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                                <div class="form-group">
                                    <label for="titulo" class="col-sm-4 control-label">Nombre Documento</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Nombre..." required="required">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="descripcion" class="col-sm-4 control-label">Descripción</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción..." required="required">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="idTipoDocumento" class="col-sm-4 control-label">Tipo</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="idTipoDocumento" name="idTipoDocumento" style="width: 100%" required="required">
                                            <c:forEach items="${listaTipoDoc}" var="tipo">
                                                <option value="${tipo.getIdTipoDocumento()}">${tipo.getTipoDocumento()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="idEstado" class="col-sm-4 control-label">Estado</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="idEstado" name="idEstado" style="width: 100%" required="required">
                                            <c:forEach items="${listaEstadoDoc}" var="estado">
                                                <option value="${estado.getIdEstado()}">${estado.getEstado()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="file" class="col-sm-4 control-label">Archivo</label>
                                    <div class="col-sm-8">
                                        <input type="file" id="file" name="file" placeholder="Seleccione archivo" required="required">
                                    </div>
                                </div>
                                <input class="form-control" type="hidden" id="idDocumento" name="idDocumento">
                                <input class="form-control" type="hidden" id="idFamiliaDoc" name="idFamiliaDoc">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>

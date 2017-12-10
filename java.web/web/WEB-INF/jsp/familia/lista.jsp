<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-xs-12 col-lg-12">
                <div class="box">
                    <div class="box-header with-border">
                        <button type="button" class="btn btn-success pull-right" data-toggle="tooltip" data-original-title="Nuevo Registro" id="newItem">
                            <i class="fa-plus fa"></i> Nueva Familia
                        </button>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" id="mantenedor">
                                <thead>
                                    <tr>
                                        <th>Nonbre</th>
                                        <th>Dirección</th>
                                        <th>Centro</th>
                                        <th>Usuario</th>
                                        <th>Descripción</th>
                                        <th>Estado</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listado}" var="familia">
                                        <tr>
                                            <td>${familia.getNombreFamilia()}</td>
                                            <td>${familia.getDireccion()}</td>
                                            <td>${centros.get(familia.getIdCentro()).getNombreCentro()}</td>
                                            <td>${usuarios.get(familia.getIdUsuario()).getNombre()} ${usuarios.get(familia.getIdUsuario()).getApellidoPat()}</td>
                                            <td>${familia.getDescripcion()}</td>
                                            <td>${estadosFamilia.get(familia.getIdEstado()).getEstado()}</td>
                                            <td>
                                                <a class="btn btn-warning btnFiles" data-url="archivos.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${familia.getIdFamilia()}">
                                                    <i class="fa fa-files-o"></i>
                                                </a>
                                                <a class="btn btn-primary btnEditar" data-url="editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${familia.getIdFamilia()}">
                                                    <i class="fa fa-pencil-square-o"></i>
                                                </a>
                                                <a class="btn btn-danger btnEliminar" data-url="borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="${familia.getIdFamilia()}">
                                                    <i class="fa fa-times-circle"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="new" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="form-horizontal form" id="addForm">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Campos Familia</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nombreFamilia" class="col-sm-2 control-label">Nombre Familia</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nombreFamilia" name="nombreFamilia" placeholder="Nombre..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="direccion" class="col-sm-2 control-label">Dirección Familia</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Dirección..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="descripcion" class="col-sm-2 control-label">Descripción</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idCentro" class="col-sm-2 control-label">Centro</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idCentro" name="idCentro" style="width: 100%" required="required">
                                        <c:forEach items="${centros}" var="centro">
                                            <option value="${centro.key}">${centro.value.getNombreCentro()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idUsuario" class="col-sm-2 control-label">Usuario</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idUsuario" name="idUsuario" style="width: 100%" required="required">
                                        <c:forEach items="${usuarios}" var="usuario">
                                            <option value="${usuario.key}">${usuario.value.getNombre()} ${usuario.value.getApellidoPat()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idEstado" class="col-sm-2 control-label">Estado</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idEstado" name="idEstado" style="width: 100%" required="required">
                                        <c:forEach items="${estadosFamilia}" var="estado">
                                            <option value="${estado.key}">${estado.value.getEstado()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input class="form-control" type="hidden" id="idFamilia" name="idFamilia">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="guardar.htm" id="addNew">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </jsp:body>
</t:Master>
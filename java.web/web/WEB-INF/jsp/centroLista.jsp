<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header with-border">
                        <button type="button" class="btn btn-success pull-right" data-toggle="tooltip" data-original-title="Nuevo Registro" id="newItem">
                            <i class="fa fa-plus"></i> Nuevo Centro
                        </button>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" id="mantenedor">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Ciudad</th>
                                        <th>Usuario</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listado}" var="centro">
                                        <tr>
                                            <td>${centro.getIdCentro()}</td>
                                            <td>${centro.getNombreCentro()}</td>
                                            <td>${centro.getIdCiudad()}</td>
                                            <td>${centro.getIdUsuario()}</td>
                                            <td>
                                                <a class="btn btn-primary btnEditar" data-url="editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${centro.getIdCentro()}">
                                                    <i class="fa fa-pencil-square-o"></i>
                                                </a>
                                                <a class="btn btn-danger btnEliminar" data-url="borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="${centro.getIdCentro()}">
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
                            <h4 class="modal-title">Campos Centro</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nombreCentro" class="col-sm-2 control-label">Nombre Centro</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nombreCentro" name="nombreCentro" placeholder="Nombre...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idCiudad" class="col-sm-2 control-label">Ciudad</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idCiudad" name="idCiudad" style="width: 100%">
                                        <c:forEach items="${ciudades}" var="ciudad">
                                            <option value="${ciudad.getIdCiudad()}">${ciudad.getNombreCiudad()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="idUsuario" class="col-sm-2 control-label">Usuario</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idUsuario" name="idUsuario" style="width: 100%">
                                        <c:forEach items="${celUsuario}" var="usuario">
                                            <option value="${usuario.getIdUsuario()}">${usuario.getNombre()} ${usuario.getApellidoPat()} (${usuario.getUsuario()})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="idCentro" name="idCentro">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="guardar.htm" id="addNew">Guardar</button>
                        </div>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </jsp:body>
</t:Master>
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
                            <i class="fa-plus fa"></i> Nuevo Usuario
                        </button>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" id="mantenedor">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Usuario</th>
                                        <th>Nombre</th>
                                        <th>Apellido Pat</th>
                                        <th>Apellido Mat</th>
                                        <th>Email</th>
                                        <th>Perfil Usuario</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listado}" var="usuario">
                                        <tr>
                                            <td>${usuario.getIdUsuario()}</td>
                                            <td>${usuario.getUsuario()}</td>
                                            <td>${usuario.getNombre()}</td>
                                            <td>${usuario.getApellidoPat()}</td>
                                            <td>${usuario.getApellidoMat()}</td>
                                            <td>${usuario.getEmail()}</td>
                                            <td>${perfilesUsuario.get(usuario.getIdPerfilUsuario()-1).getNombrePerfil()}</td>
                                            <td>
                                                <a class="btn btn-primary btnEditar" data-url="editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${usuario.getIdUsuario()}">
                                                    <i class="fa fa-pencil-square-o"></i>
                                                </a>
                                                <a class="btn btn-danger btnEliminar" data-url="borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="${usuario.getIdUsuario()}">
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
                            <h4 class="modal-title">Editar Usuario</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="usuario" class="col-sm-2 control-label">Usuario</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="usuario" name="usuario" placeholder="usuario" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nombre" class="col-sm-2 control-label">Nombre</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Juan" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="apellidoPat" class="col-sm-2 control-label">Apellido Paterno</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="apellidoPat" name="apellidoPat" placeholder="Perez" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="apellidoMat" class="col-sm-2 control-label">Apellido Materno</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="apellidoMat" name="apellidoMat" placeholder="González" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="email" name="email" placeholder="jperez@email.cl" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="clave" class="col-sm-2 control-label">Clave</label>

                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="clave" name="clave" placeholder="Clave">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idPerfilUsuario" class="col-sm-2 control-label">Perfil Usuario</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idPerfilUsuario" name="idPerfilUsuario" style="width: 100%;" required="required">
                                        <c:forEach items="${perfilesUsuario}" var="perfil">
                                            <option value="${perfil.getIdPerfilUsuario()}" >${perfil.getNombrePerfil()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                                <input type="hidden" id="idUsuario" name="idUsuario">
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

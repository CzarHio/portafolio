<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="include/top.jsp" />
<div class="row">
    <div class="col-xs-12 col-lg-12">
        <div class="box">
            <div class="box-header pull-right">

                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-default">
                    <i class="fa-plus fa"></i>
                </button>
            </div>
            <div class="box-body">
                <table class="table table-bordered table-striped table-hover table-responsive no-padding">
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
                                <td>${usuario.getIdPerfilUsuario()}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="modal-default" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Agregar Usuario</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="inputUsuario" class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputUsuario" placeholder="usuario">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputClave" class="col-sm-2 control-label">Clave</label>

                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputClave" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputNombre" class="col-sm-2 control-label">Nombre</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNombre" placeholder="Juan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidoPat" class="col-sm-2 control-label">Apellido Paterno</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputApellidoPat" placeholder="Perez">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidoMat" class="col-sm-2 control-label">Apellido Materno</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputApellidoMat" placeholder="González">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputEmail" placeholder="jperez@email.cl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputClave" class="col-sm-2 control-label">Perfil Usuario</label>

                        <div class="col-sm-10">
                            <select class="form-control">
                                <option></option>
                                <c:forEach items="${perfilesUsuario}" var="perfil">
                                    <option value="${perfil.getIdPerfilUsuario()}">${perfil.getNombrePerfil()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<jsp:include page="include/bottom.jsp" />

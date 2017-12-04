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
                                            <td>${ciudades.get(centro.getIdCiudad()).getNombreCiudad()}</td>
                                            <td>${usuarios.get(centro.getIdUsuario()).getNombre()} ${usuarios.get(centro.getIdUsuario()).getApellidoPat()}</td>
                                      
                                            <td>
                                                <a class="btn btn-primary btnEditar" data-url="editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${centro.getIdCentro()}">
                                                    <i class="fa fa-pencil-square-o"></i>
                                                </a>
                                                <a class="btn btn-warning btnImagen" data-url="findImagen.htm" data-toggle="tooltip" data-original-title="Seleccionar Imagen" data-id="${centro.getIdCentro()}">
                                                    <i class="fa fa-picture-o"></i>
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
                                    <input type="text" class="form-control" id="nombreCentro" name="nombreCentro" placeholder="Nombre..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idCiudad" class="col-sm-2 control-label">Ciudad</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idCiudad" name="idCiudad" style="width: 100%" required="required">
                                        <c:forEach items="${ciudades}" var="ciudad">
                                            <option value="${ciudad.key}">${ciudad.value.getNombreCiudad()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="idUsuario" class="col-sm-2 control-label">Usuario</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idUsuario" name="idUsuario" style="width: 100%" required="required">
                                        <c:forEach items="${usuarios}" var="usuario">
                                            <option value="${usuario.key}">${usuario.value.getNombre()} ${usuario.value.getApellidoPat()} (${usuario.value.getUsuario()})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input class="form-control" type="hidden" id="idCentro" name="idCentro">
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
        
        <div class="modal fade" id="image" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form class="form-horizontal" action="imagen.htm" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Imagen Centro</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12 image"></div>
                            </div>
                            <div class="row" style="margin-top: 30px;">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <label for="img" class="col-sm-2 control-label">Imagen</label>
                                        <div class="col-sm-10">
                                            <input type="file" id="file" name="file" placeholder="Seleccione archivo" required="required">
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" id="idInstancia" name="id">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="imagen.htm" id="saveImage">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
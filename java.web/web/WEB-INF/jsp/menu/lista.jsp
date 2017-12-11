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
                            <i class="fa-plus fa"></i> Nuevo Menú
                        </button>
                    </div>
                    <div class="box-body">
                        <div class="box-group" id="accordion">
                            <c:forEach items="${perfiles}" var="perfil">
                                <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                                <div class="panel box box-primary">
                                    <div class="box-header with-border">
                                        <h4 class="box-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#perfil${perfil.getIdPerfilUsuario()}" aria-expanded="false" class="collapsed">
                                                ${perfil.getNombrePerfil()}
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="perfil${perfil.getIdPerfilUsuario()}" class="panel-collapse collapse" aria-expanded="false">
                                        <div class="box-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Título</th>
                                                            <th>Orden</th>
                                                            <th>Acciones</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${listado}" var="menu">
                                                            <c:if test="${menu.getPerfilUsuario() == perfil.getIdPerfilUsuario()}">
                                                                <tr>
                                                                    <td>${menu.getTitulo()}</td>
                                                                    <td>${menu.getOrden()}</td>
                                                                    <td>
                                                                        <a class="btn btn-success menuItem" data-toggle="tooltip" data-original-title="Items Menú" data-id="items${menu.getIdMenu()}">
                                                                            <i class="fa fa-plus"></i>
                                                                        </a>
                                                                        <a class="btn btn-primary btnEditar" data-url="/java.web/menu/editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${menu.getIdMenu()}">
                                                                            <i class="fa fa-pencil-square-o"></i>
                                                                        </a>
                                                                        <a class="btn btn-danger btnEliminar" data-url="/java.web/menu/borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="${menu.getIdMenu()}">
                                                                            <i class="fa fa-times-circle"></i>
                                                                        </a>
                                                                    </td>
                                                                </tr>
                                                                <tr id="items${menu.getIdMenu()}" style="display: none;">
                                                                    <td colspan="4">
                                                                        <button type="button" class="btn btn-success pull-right newMenuItem" data-menu="${menu.getIdMenu()}" data-toggle="tooltip" data-original-title="Nuevo Registro">
                                                                            <i class="fa-plus fa"></i> Nuevo Menú Ítem
                                                                        </button>
                                                                        <table class="table table-bordered table-hover">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Id</th>
                                                                                    <th>Título</th>
                                                                                    <th>Orden</th>
                                                                                    <th>Padre</th>
                                                                                    <th>Ícono</th>
                                                                                    <th>Url</th>
                                                                                    <th>Acciones</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <c:forEach items="${menuItem}" var="item">
                                                                                    <c:if test="${item.getIdMenu() == menu.getIdMenu()}">
                                                                                        <tr>
                                                                                            <td>${item.getIdMenuItem()}</td>
                                                                                            <td>${item.getTitulo()}</td>
                                                                                            <td>${item.getOrden()}</td>
                                                                                            <td>${item.getPadre()}</td>
                                                                                            <td><i class="fa ${item.getIcono()}"></i></td>
                                                                                            <td>${item.getUrl()}</td>
                                                                                            <td>
                                                                                                <a class="btn btn-primary btnEditarItem" data-url="/java.web/menuItem/editar.htm" data-toggle="tooltip" data-original-title="Editar" data-menu="${menu.getIdMenu()}" data-id="${item.getIdMenuItem()}">
                                                                                                    <i class="fa fa-pencil-square-o"></i>
                                                                                                </a>
                                                                                                <a class="btn btn-danger btnEliminar" data-url="/java.web/menuItem/borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="${item.getIdMenuItem()}">
                                                                                                    <i class="fa fa-times-circle"></i>
                                                                                                </a>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
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
                            <h4 class="modal-title">Campos Menu</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="titulo" class="col-sm-2 control-label">Título</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Título..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="orden" class="col-sm-2 control-label">Orden</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="orden" name="orden" placeholder="Orden..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="perfilUsuario" class="col-sm-2 control-label">Perfil Usuario</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="perfilUsuario" name="perfilUsuario" style="width: 100%;" required="required">
                                        <c:forEach items="${perfiles}" var="perfil">
                                            <option value="${perfil.getIdPerfilUsuario()}" >${perfil.getNombrePerfil()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input class="form-control" type="hidden" id="idMenu" name="idMenu">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="/java.web/menu/guardar.htm" id="addNew">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="newItemMenu" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="form-horizontal addMenuItem" id="addMenuItem">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Campos Menú Ítem</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Título</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="titulo" placeholder="Título..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Orden</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" name="orden" placeholder="Orden..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Url</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="url" placeholder="Url..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Ícono</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="icono" placeholder="Ícono...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Padre</label>
                                <div class="col-sm-10">
                                    <select class="form-control" name="padre" style="width: 100%;">
                                        <option value="0" >Ninguno</option>
                                        <c:forEach items="${menuItem}" var="menu">
                                            <option value="${menu.getIdMenuItem()}" >${menu.getTitulo()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="estado" class="col-sm-2 control-label">Estado</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="estado" name="estado" style="width: 100%" required="required">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                    </select>
                                </div>
                            </div>
                            <input class="form-control" type="hidden" id="_menu" name="idMenu">
                            <input class="form-control" type="hidden" name="idMenuItem">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="/java.web/menuItem/guardar.htm" id="saveMenuItem">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
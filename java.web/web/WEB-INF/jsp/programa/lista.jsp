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
                            <i class="fa-plus fa"></i> Nuevo Programa
                        </button>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" id="mantenedor">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>País</th>
                                        <th>Estado</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listado}" var="programa">
                                        <tr>
                                            <td>${programa.getIdPrograma()}</td>
                                            <td>${programa.getNombrePrograma()}</td>
                                            <td>${paises.get(programa.getIdPais()).getNombrePais()}</td>
                                            <td>${programa.getIdEstado()}</td>
                                            <td>
                                                <a class="btn btn-warning btnCursos" data-url="/java.web/cursos/lista.htm" data-toggle="tooltip" data-original-title="Cursos" data-id="${programa.getIdPrograma()}">
                                                    <i class="fa fa-cubes"></i>
                                                </a>
                                                <a class="btn btn-primary btnEditar" data-url="editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="${programa.getIdPrograma()}">
                                                    <i class="fa fa-pencil-square-o"></i>
                                                </a>
                                                <a class="btn btn-danger btnEliminar" data-url="borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="${programa.getIdPrograma()}">
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
                            <h4 class="modal-title">Campos Programa</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nombrePrograma" class="col-sm-2 control-label">Nombre Programa</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nombrePrograma" name="nombrePrograma" placeholder="Nombre..." required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="idPais" class="col-sm-2 control-label">País</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idPais" name="idPais" style="width: 100%" required="required">
                                        <c:forEach items="${paises}" var="pais">
                                            <option value="${pais.key}">${pais.value.getNombrePais()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="idEstado" class="col-sm-2 control-label">Estado</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="idEstado" name="idEstado" style="width: 100%" required="required">
                                        <c:forEach items="${estados}" var="estado">
                                            <option value="${estado.getIdEstado()}">${estado.getEstado()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="idPrograma" name="idPrograma">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="guardar.htm" id="addNew">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="cursos" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="form-horizontal cursoForm" id="addCurso">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Cursos</h4>
                        </div>
                        <div class="modal-body">
                            <table class="table table-bordered table-hover" id="table-cursos">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div class="form-group">
                                <label for="nombreCurso" class="col-sm-4 control-label">Nombre Curso</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="nombreCurso" name="nombreCurso" placeholder="Nombre..." required="required">
                                </div>
                            </div>
                            <input type="hidden" id="idCurso" name="idCurso">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="/java.web/cursos/guardar.htm" data-programa="" id="saveCurso">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
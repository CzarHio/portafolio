<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-xs-12 col-lg-12">
                <div class="box">
                    <div class="box-header with-border">
                        <c:if test="${!sessionScope.perfil.equals('4')}">
                            <form action="" method="GET">
                                <div class="form-group col-md-5">
                                    <label for="alumno" class="col-sm-2 control-label">Alumno</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" id="idRegion" name="alumno" style="width: 100%" required="required">
                                            <c:forEach items="${alumnos}" var="alumno">
                                                <option value="${alumno.getIdAlumno()}" ${alumno.getIdAlumno()==a?'selected':''}>${alumno.getNombreUsuario()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-success pull-right" data-toggle="tooltip" data-original-title="Filtrar">
                                    Filtrar
                                </button>
                            </form>
                        </c:if>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover" id="mantenedor">
                                <thead>
                                    <tr>
                                        <th>Programa</th>
                                        <th>Nombre Curso</th>
                                        <th>Nota</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${cursos}" var="curso">
                                        <tr>
                                            <td>${curso.getNombrePrograma()}</td>
                                            <td>${curso.getNombreCurso()}</td>
                                            <td>
                                                <c:if test="${!sessionScope.perfil.equals('4')}">
                                                    <c:forEach items="${postulaciones}" var="postulacion">
                                                        <c:if test="${postulacion.getIdPrograma() == curso.getIdPrograma()}">
                                                            <c:set var="id_postulacion" value="${postulacion.getIdPostulacion().intValue()}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    ${notas.containsKey(curso.getIdCurso().intValue())?
                                                      notas.get(curso.getIdCurso().intValue()).getNota():
                                                      ('<a href="#" class="btn btn-success subirNota" data-curso="' += curso.getIdCurso().intValue() += '" data-alumno="' += a += '" data-postulacion="' += id_postulacion += '"><i class="fa fa-plus"></i></a>')}
                                                </c:if>
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
        
        <div class="modal fade" id="notas" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="form-horizontal notaForm" id="addNota">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Notas</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="nota" class="col-sm-4 control-label">Nota</label>
                                <div class="col-sm-8">
                                    <input type="number" class="form-control" id="nota" name="nota" placeholder="Nota..." required="required">
                                </div>
                            </div>
                            <input class="form-control" type="hidden" id="idNota" name="idNota">
                            <input class="form-control" type="hidden" id="idPostulacion" name="idPostulacion">
                            <input class="form-control" type="hidden" id="idCurso" name="idCurso">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" data-url="/java.web/notas/guardar.htm" id="saveNota">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
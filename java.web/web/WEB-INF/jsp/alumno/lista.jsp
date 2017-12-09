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
                                                <option value="${alumno.getIdAlumno()}">${alumno.getNombreUsuario()}</option>
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
                                        <th>Nonbre</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${cursos}" var="curso">
                                        <tr>
                                            <td>${curso.getNombreCurso()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
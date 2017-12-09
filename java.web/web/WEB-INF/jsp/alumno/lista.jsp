<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-xs-12 col-lg-12">
                <div class="box">
                    <div class="box-header with-border">
                        <div class="form-group col-md-5">
                            <label for="alumno" class="col-sm-2 control-label">Alumnos</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="idRegion" name="alumno" style="width: 100%" required="required">
                                    <c:forEach items="${alumnos}" var="alumno">
                                        <option value="${alumno.getIdAlumno()}">${alumno.getIdAlumno()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <button type="button" class="btn btn-success pull-right" data-toggle="tooltip" data-original-title="Nuevo Registro" id="newItem">
                            <i class="fa-plus fa"></i> Nueva Ciudad
                        </button>
                    </div>
                    <div class="box-body">
                        
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
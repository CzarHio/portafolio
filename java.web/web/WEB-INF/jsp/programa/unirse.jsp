<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-xs-12 col-lg-12">
                <c:forEach items="${listado}" var="programa">
                    <div class="col-md-4">
                        <div class="box box-widget widget-user">
                            <div class="widget-user-header bg-black" style="background: url('/java.web/resources/dist/img/photo2.png') center center;">
                                <h3 class="widget-user-username">${programa.getNombrePrograma()}</h3>
                                <h5 class="widget-user-desc">${paises.get(programa.getIdPais()).getNombrePais()}</h5>
                            </div>
                            <div class="box-footer">
                                <div class="row">
                                    <div class="col-sm-4 border-right">
                                        <div class="description-block">
                                            <h5 class="description-header">3,200</h5>
                                            <span class="description-text">Alumnos</span>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 border-right">
                                        <div class="description-block">
                                            <h5 class="description-header">13</h5>
                                            <span class="description-text"><a href="#">Cursos</a></span>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="description-block">
                                            <button class="btn btn-success">UNIRSE</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:Master>

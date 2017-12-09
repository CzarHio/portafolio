<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="well"><h1>Bienvenido ${sessionScope.userSession}</h1></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-primary">
                    <div class="panel-heading ">
                        Mis Postulaciones
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Centro</th>
                                    <th>Programa</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaPostulacion}" var="postulacion">
                                    <tr>
                                        <td>${postulacion.getNombreCentro()} </td>
                                        <td>${postulacion.getNombrePrograma()}</td>
                                        <td>${postulacion.getFecha()}</td>
                                        <td>${postulacion.getEstado()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>  
        </div>
        <h2 class="page-header">
            Programas Disponibles
        </h2>
        <div class="row">
            <c:forEach items="${listaParticipacion}" var="participacion">
                <div class="col-md-4">

                    <!-- Widget: user widget style 1 -->
                    <div class="box box-widget widget-user">
                        <!-- Add the bg color to the header using any of the bg-* classes -->
                        <a href="">
                        <div class="widget-user-header bg-aqua-active">
                            <h3 class="widget-user-username">${participacion.getNombrePrograma()}</h3>
                            <h5 class="widget-user-desc">${participacion.getNombreCentro()}</h5>
                        </div>
                        </a>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">${participacion.getMaxcupos()-participacion.getPostulacionesSeleccionadas()}</h5>
                                        <span class="description-text">Cupos Disponible</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">${participacion.getMaxcupos()}</h5>
                                        <span class="description-text">Cupos Maximo</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4">
                                    <div class="description-block">
                                        <h5 class="description-header">${participacion.getTipoPrograma()}</h5>
                                        <span class="description-text">Tipo Programa</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                    </div>
                    <!-- /.widget-user -->
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:Master>

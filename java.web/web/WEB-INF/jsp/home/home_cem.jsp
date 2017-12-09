<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/top.jsp" />
<div class="row">
    <div class="well"><h1>Bienvenido CEM</h1></div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default ">
            <div class="panel-heading ">
                Participacion a Programa Pendientes
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Centro</th>
                                <th>Programa</th>
                                <th>Fecha</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaParticipacion}" var="participacion">
                                <tr>
                                    <td>${participacion.getNombreCentro()}</td>
                                    <td>${participacion.getNombrePrograma()}</td>
                                    <td>${participacion.getFecha()}</td>
                                    <td>${participacion.getEstado()}</td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                <i class="fa fa-gears"></i>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="#"><i class="fa fa-adjust"></i>Cambiar Estado</a></li>
                                                <li><a href="#"><i class="fa fa-eye"></i>Detalle</a></li>
                                                <li><a href="#"><i class="fa fa-times"></i>Borrar</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </div>
    </div>  
</div>
<jsp:include page="../include/bottom.jsp" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/top.jsp" />
<div class="row">
    <div class="well"><h1>Bienvenido ${sessionScope.userSession}</h1></div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default ">
            <div class="panel-heading ">
                Mis Postulaciones
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Programa</th>
                                <th>Centro</th>
                                <th>Fecha</th>
                                <th>Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaPostulacion}" var="postulacion">
                                <tr>
                                    <td>${postulacion.getNombrePrograma()}</td>
                                    <td>${postulacion.getNombreCentro()} </td>
                                    <td>${postulacion.getFechaCreacion()}</td>
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
                <a class="btnVerPrograma" data-url="programa/ver.htm" data-toggle="tooltip" data-id-participacion="${participacion.getIdParticipacion()}" href="#">
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
<div class="modal fade" id="verPrograma" style="display: none;">
    <div class="modal-dialog">
        <div id="modalTarget" class="modal-content">
            
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<jsp:include page="../include/bottom.jsp" />

<script>
    idParticipacion = 0;
    $('body').on('click', '.btnVerPrograma', function () {
        $("#modalTarget").html("");
        idParticipacion = $(this).data("id-participacion");
        $.post("programa/ver.htm", {idParticipacion: $(this).data("id-participacion") }, function(data){
            $("#modalTarget").html(data);
        });
    $('#verPrograma').modal('show');
    });
    $('body').on('click', '#btnPostular', function () {
       $("#modalTarget").html("");
       $.post("familia/selfam.htm", {idCentro: $(this).data("id-centro") }, function(data){
            $("#modalTarget").html(data);
        });
    });
    $('body').on('click', '.btnSelFamilia', function () {
       $("#modalTarget").html("");
       $.post("programa/resumen.htm", {idFamilia: $(this).data("id-familia"), idParticipacion: idParticipacion}, function(data){
            $("#modalTarget").html(data);
       });
    });
    
</script>

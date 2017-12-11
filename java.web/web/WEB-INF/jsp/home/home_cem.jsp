<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="well"><h1>Bienvenido CEM</h1></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Participación a Programa Pendientes</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <table class="table table-striped table-bordered table-hover">
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
                                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <i class="fa fa-gears"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="btnCambiarEstado" href="#" data-toggle="tooltip" data-id-participacion="${participacion.getIdParticipacion()}"><i class="fa fa-adjust"></i>Cambiar Estado</a></li>
                                                    <li><a class="btnVer" href="#" href="#" data-toggle="tooltip" data-id-participacion="${participacion.getIdParticipacion()}"><i class="fa fa-eye"></i>Detalle</a></li>
                                                    <li><a class="btnBorrar" href="#" href="#" data-toggle="tooltip" data-id-participacion="${participacion.getIdParticipacion()}"><i class="fa fa-times"></i>Borrar</a></li>
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
        <div class="modal fade" id="modalParticipacion" style="display: none;">
            <div class="modal-dialog">
                <div id="modalTarget" class="modal-content">
                    sdadsad
                </div>
            </div>
        </div>
    </jsp:body>
</t:Master>
<script>
    $('body').on('click', '.btnCambiarEstado', function () {
       $("#modalTarget").html("");
       $.post("participacion/estado.htm", {idParticipacion: $(this).data("id-participacion") }, function(data){
            $("#modalTarget").html(data);
        });
        $('#modalParticipacion').modal('show');
    });
    $('body').on('click', '.btnVer', function () {
       $("#modalTarget").html("");
       $.post("participacion/ver.htm", {idParticipacion: $(this).data("id-participacion") }, function(data){
            $("#modalTarget").html(data);
       });
       $('#modalParticipacion').modal('show');
    });
    $('body').on('click', '.btnBorrar', function () {
       $("#modalTarget").html("");
       $.post("familia/selfam.htm", {idParticipacion: $(this).data("id-participacion") }, function(data){
            $("#modalTarget").html(data);
       });
       $('#modalParticipacion').modal('show');
    });
</script>
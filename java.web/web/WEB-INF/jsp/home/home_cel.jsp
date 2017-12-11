<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="well"><h1>Bienvenido CEL</h1></div>
            </div>
        </div>
          <div class="row">
            <div class="col-md-12">
                <div class="box box-solid box-primary ">
                    <div class="box-header with-border">
                        <h3 class="box-title">Mis Postulacioness</h3>
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
                                    <th>Programa</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaPrograma}" var="programa">
                                    <tr>
                                        <td>${programa.getNombrePrograma()}</td>
                                        <td>${programa.getFechaCreacion()}</td>
                                        <td>${programa.getEstado()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>  
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Postulación a Programa Pendientes</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Alumno</th>
                                        <th>Programa</th>
                                        <th>Familia</th>
                                        <th>Fecha</th>
                                        <th>Estado</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listaPostulacion}" var="postulacion">
                                        <tr>
                                            <td>${postulacion.getNombreAlumno()}</td>
                                            <td>${postulacion.getNombrePrograma()}</td>
                                            <td>${postulacion.getNombreFamilia()}</td>
                                            <td>${postulacion.getFechaCreacion()}</td>
                                            <td>${postulacion.getEstado()}</td>
                                            <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <i class="fa fa-gears"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="btnCambiarEstadoPostulacion" href="#" data-toggle="tooltip" data-id-postulacion="${postulacion.getIdPostulacion()}"><i class="fa fa-adjust"></i>Cambiar Estado</a></li>
                                                    <li><a class="btnVerPostulacion" href="#" href="#" data-toggle="tooltip" data-id-postulacion="${postulacion.getIdPostulacion()}"><i class="fa fa-eye"></i>Detalle</a></li>
                                                    <li><a class="btnBorrarPostulacion" href="#" href="#" data-toggle="tooltip" data-id-postulacion="${postulacion.getIdPostulacion()}"><i class="fa fa-times"></i>Borrar</a></li>
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
        </div>
         <h2 class="page-header">
            Programas Disponibles
        </h2>
        <div class="row">
            <c:forEach items="${listaPrograma}" var="participacion">
                <div class="col-md-4">
                    <!-- Widget: user widget style 1 -->
                    <div class="box box-widget widget-user">
                        <!-- Add the bg color to the header using any of the bg-* classes -->
                        <a class="btnVerPrograma" data-url="programa/ver.htm" data-toggle="tooltip" data-id-programa="${participacion.getIdPrograma()}" href="#">
                            <div class="widget-user-header bg-aqua-active">
                                <h3 class="widget-user-username">${participacion.getNombrePrograma()}</h3>
                                <h5 class="widget-user-desc">${participacion.getNombrePais()}</h5>
                            </div>
                        </a>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">${participacion.getMincupos()}</h5>
                                        <span class="description-text">Cupos Minimo</span>
                                    </div>
                                </div>
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">${participacion.getMaxcupos()}</h5>
                                        <span class="description-text">Cupos Maximo</span>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="description-block">
                                        <h5 class="description-header">${participacion.getTipoPrograma()}</h5>
                                        <span class="description-text">Tipo Programa</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="modal fade" id="verPrograma" style="display: none;">
            <div class="modal-dialog">
                <div id="modalTarget" class="modal-content">

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
    $('body').on('click', '.btnVerPrograma', function () {
        $("#modalTarget").html("");
        idParticipacion = $(this).data("id-participacion");
        $.post("programa/participar.htm", {idPrograma: $(this).data("id-programa") }, function(data){
            $("#modalTarget").html(data);
        });
        $('#verPrograma').modal('show');
    });
      //// Postulacion  ///
    $('body').on('click', '.btnCambiarEstadoPostulacion', function () {
       $("#modalTarget").html("");
       $.post("postulacion/estado.htm", {idPostulacion: $(this).data("id-postulacion") }, function(data){
            $("#modalTarget").html(data);
        });
        $('#modalParticipacion').modal('show');
    });
    $('body').on('click', '.btnVerPostulacion', function () {
       $("#modalTarget").html("");
       $.post("postulacion/ver.htm", {idPostulacion: $(this).data("id-postulacion") }, function(data){
            $("#modalTarget").html(data);
       });
       $('#modalParticipacion').modal('show');
    });
    $('body').on('click', '.btnBorrarPostulacion', function () {
       $("#modalTarget").html("");
       $.post("postulacion/borrar.htm", {idPostulacion: $(this).data("id-postulacion") }, function(data){
            $("#modalTarget").html(data);
       });
       $('#modalParticipacion').modal('show');
    });
</script>
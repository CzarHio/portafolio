<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="include/top.jsp" />
<div class="row">
    <div class="col-xs-12 col-lg-12">
        <div class="box">
            <div class="box-header pull-right">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalNuevo">
                    <i class="fa-plus fa"></i>
                </button>
            </div>
            <div class="box-body">
                <table class="table table-bordered table-striped table-hover table-responsive no-padding">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Estado</th>
                            <th>Pais</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listado}" var="programa">
                            <tr>
                                <td>${programa.getIdPrograma()}</td>
                                <td>${programa.getNombrePrograma()}</td>
                                <td>${programa.getIdEstado()}</td>
                                <td>${programa.getIdPais()}</td>
                                <td>
                                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalEditar" data-id="${programa.getIdPrograma()}">
                                        <i class="fa-pencil fa"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalBorrar" data-id="${programa.getIdPrograma()}">
                                        <i class="fa-eraser fa"></i>
                                    </button>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalBorrar" data-id="${programa.getIdPrograma()}">
                                        Publicar
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
<div class="modal fade" id="modalEditar" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modalNuevo" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modalBorrar" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<jsp:include page="include/bottom.jsp" />
<script>
    $("#modalEditar").on("show.bs.modal", function (e) {
        id = $(e.relatedTarget).data('id');
        $(this).find(".modal-content").load("editar.htm?id=" + id);
    });
    $("#modalNuevo").on("show.bs.modal", function (e) {
        $(this).find(".modal-content").load("nuevo.htm");
    });
    $("#modalBorrar").on("show.bs.modal", function (e) {
        id = $(e.relatedTarget).data('id');
        $(this).find(".modal-content").load("borrar.htm?id="+id);
    });
</script>
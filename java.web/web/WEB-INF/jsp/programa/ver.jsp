<%-- 
    Document   : ver
    Created on : 09-dic-2017, 6:14:37
    Author     : Czar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="form-horizontal form" id="addForm">
    <div class="modal-header bg-aqua-active">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span></button>

        <h3 class="widget-user-username">${participacion.getNombrePrograma()}</h3>
        <h5 class="widget-user-desc">${participacion.getNombreCentro()}</h5>

    </div>
    <div class="modal-body">

        <dl class="dl-horizontal">
            <dt>Tipo Programa</dt><dd>${participacion.getTipoPrograma()}</dd>
            <dt>Pais</dt><dd>${programa.getNombrePais()}</dd>
            <dt>Cupos Maximo</dt><dd>${participacion.getMaxcupos()}</dd>
            <dt>Cupos Minimo</dt><dd>${participacion.getMincupos()}</dd>
            <dt>Cupos Disponibles:</dt><dd>${participacion.getMaxcupos()-participacion.getPostulacionesSeleccionadas()}</dd>
            <dt></dt><dd></dd>
            <dt></dt><dd></dd>
        </dl>

        <div class="modal-footer">
            <button id="btnPostular" type="button" class="btn btn-default" data-id-centro="${participacion.getIdCentro()}">Postular</button>   
        </div>
    </div>
</div>


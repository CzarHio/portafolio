<%-- 
    Document   : ver
    Created on : 09-dic-2017, 6:14:37
    Author     : Czar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">×</span></button>
    <h4 class="modal-title">Ver Participacion</h4>
</div>
<div class="modal-body">
    <dl class="dl-horizontal">
        <dt>Nombre Programa</dt><dd>${participacion.getNombrePrograma()}</dd>
        <dt>Centro:</dt><dd>${participacion.getNombreCentro()}</dd>
        <dt>Cupos Maximo</dt><dd>${participacion.getMaxcupos()}</dd>
        <dt>Cupos Minimos</dt><dd>${participacion.getMincupos()}</dd>
        <dt>Fecha</dt><dd>${participacion.getFecha()}</dd>
        <dt>Tipo Programa</dt><dd>${participacion.getTipoPrograma()}</dd>
    </dl>
</div>
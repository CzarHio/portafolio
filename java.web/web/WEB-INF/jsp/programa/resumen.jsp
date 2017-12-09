<%-- 
    Document   : ver
    Created on : 09-dic-2017, 6:14:37
    Author     : Czar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" action="postulacion/guardar.htm">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
    <h4 class="modal-title">Resumen de la Postulacion</h4>
</div>
<div class="modal-body">
    <dl class="dl-horizontal">
        <dt>Prgrograma:</dt><dd>${participacion.getNombrePrograma()}</dd>
        <dt>Centro:</dt><dd>${participacion.getNombreCentro()}</dd>
        <dt>Familia:</dt><dd>${familia.getNombreFamilia()}</dd>
    </dl>
</div>
    <input type="hidden" name="idFamilia" id="idFamilia" value="${familia.getIdFamilia()}">
    <input type="hidden" name="idParticipacion" id="idParticipacion" value="${participacion.getIdParticipacion()}">
<div class="modal-footer">
    <button id="btnConfirmarPostulacion" type="submit" class="btn btn-default">Confirmar</button>   
</div>
</form>

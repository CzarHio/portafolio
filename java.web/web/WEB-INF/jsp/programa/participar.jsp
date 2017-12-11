<%-- 
    Document   : ver
    Created on : 09-dic-2017, 6:14:37
    Author     : Czar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" action="programa/guardarParticipacion.htm">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title">Confirmar Participacion</h4>
    </div>
    <div class="modal-body">
        <h4>¿Desea participar del programa: ${programa.getNombrePrograma()}?</h4>

    </div>
    <input type="hidden" name="idPrograma" id="idPrograma" value="${programa.getIdPrograma()}">
    <div class="modal-footer">
        <button id="btnConfirmarPostulacion" type="submit" class="btn btn-default">Confirmar</button>   
    </div>
</form>

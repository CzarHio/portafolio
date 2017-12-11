<%-- 
    Document   : ver
    Created on : 09-dic-2017, 6:14:37
    Author     : Czar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" action="participacion/cambiarEstado.htm">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span></button>
        <h4 class="modal-title">Cambiar Estado Participacion</h4>
    </div>
    <div class="modal-body">
        <dl class="dl-horizontal">
            <dt>Nombre Programa</dt><dd>${participacion.getNombrePrograma()}</dd>
            <dt>Centro:</dt><dd>${participacion.getNombreCentro()}</dd>
        </dl>
        <div class="form-group">
            <label for="idEstado" class="col-sm-3 control-label">Estado:</label>
            <div class="col-sm-9">
                <select class="form-control" id="idEstado" name="idEstado" style="width: 100%;" required="required">
                    <c:forEach items="${listaEstados}" var="estado">
                        <option value="${estado.getIdEstado()}">${estado.getEstado()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br />
        <div class="form-group">
            <label for="revision" class="col-sm-3 control-label">Revision:</label>
            <div class="col-sm-9">
                <textarea type="text" class="form-control" id="revision" name="revision"></textarea>
            </div>
        </div>
        <br />
        <input name="idParticipacion" value="${participacion.getIdParticipacion()}" type="hidden">
        <input name="idCentro" value="${participacion.getIdCentro()}" type="hidden">
        <div class="modal-footer">
            <button id="btnPostular" type="submit" class="btn btn-default" data-id-centro="${participacion.getIdCentro()}">Guardar</button>   
        </div>
    </div>
        
</form>
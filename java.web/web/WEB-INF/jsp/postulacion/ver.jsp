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
        <dt>Nombre Alumno</dt><dd>${postulacion.getNombreAlumno()}</dd>
        <dt>Nombre Programa</dt><dd>${postulacion.getNombrePrograma()}</dd>
        <dt>Centro:</dt><dd>${postulacion.getNombreCentro()}</dd>
        <dt>Nombre Familia</dt><dd>${postulacion.getNombreFamilia()}</dd>
        <dt>Fecha</dt><dd>${postulacion.getFechaCreacion()}</dd>
    </dl>
</div>
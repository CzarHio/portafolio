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
    <h4 class="modal-title">Seleccione Familia</h4>
</div>
<div class="modal-body">
    <c:forEach items="${ListadoFamilia}" var="familia">
        <div class="small-box bg-aqua">
            <div class="inner">
                <h4>${familia.getNombreFamilia()}</h4>
                <p>${familia.getDireccion()}</p>
            </div>
            <a href="#" class="small-box-footer btnSelFamilia" data-id-familia="${familia.getIdFamilia()}" data-id-participacion="${participacion.getIdParticipacion()}">
                Seleccionar <i class="fa fa-arrow-circle-right"></i>
            </a>
        </div>
    </c:forEach>
</div>
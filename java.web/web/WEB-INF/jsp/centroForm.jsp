<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="include/head.jsp" /> 
    <c:if test="${centro!=null}">
        <body>
            <form class="form-horizontal" action="guardar.htm" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Editar Centro</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputNombreCentro" class="col-sm-2 control-label">Nombre Centro</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNombreCentro" name="inputNombreCentro" placeholder="Nombre..." value="${centro.getNombreCentro()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCiudad" class="col-sm-2 control-label">Ciudad</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputCiudad" name="inputCiudad">
                                <c:forEach items="${ciudades}" var="ciudad">
                                    <option value="${ciudad.getIdCiudad()}" <c:if test="${centro.getIdCiudad()==ciudad.getIdCiudad()}">selected</c:if> >${ciudad.getNombreCiudad()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                     <div class="form-group">
                        <label for="inputUsuario" class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputUsuario" name="inputUsuario">
                                <c:forEach items="${celUsuario}" var="usuario">
                                    <option value="${usuario.getIdUsuario()}" <c:if test="${centro.getIdUsuario()==usuario.getIdUsuario()}">selected</c:if> >${usuario.getNombre()} ${usuario.getApellidoPat()} (${usuario.getUsuario()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                        <input type="hidden" id="inputIdUsuario" name="inputIdCentro" value="${centro.getIdCentro()}">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </body>  
    </c:if>
    <c:if test="${centro==null}">
        <body>
                        <form class="form-horizontal" action="guardar.htm" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Editar Centro</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputNombreCentro" class="col-sm-2 control-label">Nombre Centro</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNombreCentro" name="inputNombreCentro" placeholder="Nombre..." >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCiudad" class="col-sm-2 control-label">Ciudad</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputCiudad" name="inputCiudad">
                                <c:forEach items="${ciudades}" var="ciudad">
                                    <option value="${ciudad.getIdCiudad()}">${ciudad.getNombreCiudad()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                     <div class="form-group">
                        <label for="inputUsuario" class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputUsuario" name="inputUsuario">
                                <c:forEach items="${celUsuario}" var="usuario">
                                    <option value="${usuario.getIdUsuario()}">${usuario.getNombre()} ${usuario.getApellidoPat()} (${usuario.getUsuario()})</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                        <input type="hidden" id="inputIdUsuario" name="inputIdUsuario" value="${centro.getIdUsuario()}">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </body>          
    </c:if>
</html>




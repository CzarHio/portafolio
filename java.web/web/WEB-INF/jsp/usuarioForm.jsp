<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="include/head.jsp" /> 
    <c:if test="${usr!=null}">
        <body>
            <form class="form-horizontal" action="guardar.htm" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Editar Usuario</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputUsuario" class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputUsuario" name="inputUsuario" placeholder="usuario" value="${usr.getUsuario()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputNombre" class="col-sm-2 control-label">Nombre</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNombre" name="inputNombre" placeholder="Juan" value="${usr.getNombre()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidoPat" class="col-sm-2 control-label">Apellido Paterno</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputApellidoPat" name="inputApellidoPat" placeholder="Perez" value="${usr.getApellidoPat()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidoMat" class="col-sm-2 control-label">Apellido Materno</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputApellidoMat" name="inputApellidoMat" placeholder="González" value="${usr.getApellidoMat()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="jperez@email.cl" value="${usr.getEmail()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputClave" class="col-sm-2 control-label">Perfil Usuario</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputPerfilUsuario" name="inputPerfilUsuario">
                                <c:forEach items="${perfilesUsuario}" var="perfil">
                                    <option value="${perfil.getIdPerfilUsuario()}" <c:if test="${usr.getIdPerfilUsuario()==perfil.getIdPerfilUsuario()}">selected</c:if> >${perfil.getNombrePerfil()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                        <input type="hidden" id="inputIdUsuario" name="inputIdUsuario" value="${usr.getIdUsuario()}">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </body>  
    </c:if>
    <c:if test="${usr==null}">
        <body>
            <form class="form-horizontal" action="guardar.htm" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Agregar Usuario</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="inputUsuario" class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputUsuario" name="inputUsuario" placeholder="usuario">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputClave" class="col-sm-2 control-label">Clave</label>

                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputClave" name="inputClave" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputNombre" class="col-sm-2 control-label">Nombre</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNombre" name="inputNombre" placeholder="Juan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidoPat" class="col-sm-2 control-label">Apellido Paterno</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputApellidoPat" name="inputApellidoPat" placeholder="Perez">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApellidoMat" class="col-sm-2 control-label">Apellido Materno</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputApellidoMat" name="inputApellidoMat" placeholder="González">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="jperez@email.cl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputClave" class="col-sm-2 control-label">Perfil Usuario</label>

                        <div class="col-sm-10">
                            <select class="form-control" name="inputPerfilUsuario">
                                <option></option>
                                <c:forEach items="${perfilesUsuario}" var="perfil">
                                    <option value="${perfil.getIdPerfilUsuario()}">${perfil.getNombrePerfil()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </body>          
    </c:if>
</html>




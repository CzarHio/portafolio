<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span></button>
            <h4 class="modal-title">Borrar Usuario</h4>
        </div>
        <div class="modal-body">
            <p>¿Confirmar borrar el usuario ${usr.getNombre()} ${usr.getApellidoPat()}?</p>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">No</button>
            <form method="post" action="borrar.htm">
                <input type="hidden" name="inputIdUsuario"/>
                <button type="submit" class="btn btn-danger">Si</button>
            </form>
        </div>
    </div>
    <!-- /.modal-content -->
</html>




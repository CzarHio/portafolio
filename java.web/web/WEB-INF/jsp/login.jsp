<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="include/head.jsp" />
<c:if test="${sessionScope.user!= null}">
   <c:redirect url="./perfil.htm"/>
</c:if>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="index.htm">
                    <img alt="Sistema CEM" class="img-responsive" width="350" src="/java.web/resources/dist/img/logo_large.png">
                </a>
            </div>
            <div class="login-box-body">
                <p class="login-box-msg">Ingresar a Sistema CEM</p>
                <form class="form" action="./postLogin.htm" method="POST">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" placeholder="Ingrese usuario" id="login" name="login" required="required">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" placeholder="Ingrese contraseña" id="password" name="password" required="required">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 pull-right">
                            <button type="submit" class="btn btn-primary btn-block btn-flat" id="login">Ingresar</button>
                        </div>
                    </div>
                </form>
                <br>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4><i class="icon fa fa-ban"></i> Error!</h4>
                        ${error}
                    </div>
                </c:if>
                <a id="recover-password" href="#">Recuperar contraseña</a>
                <br>
                <a href="./registrarse.htm">Registrarse en el Sistema</a>
            </div>
        </div>
        <!-- jQuery 2.1.4 -->
        <script src="/java.web/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
        <!-- Bootstrap 3.3.5 -->
        <script src="/java.web/resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="/java.web/resources/plugins/pace/pace.js"></script>
        <!-- iCheck -->
        <script src="/java.web/resources/plugins/iCheck/icheck.min.js"></script>
        <!-- SweetAlert -->
        <script src="/java.web/resources/plugins/sweetalert/sweetalert.min.js"></script>
        <!-- page script -->
        <script src="/java.web/resources/dist/js/scripts.js"></script>
    </body>
</html>

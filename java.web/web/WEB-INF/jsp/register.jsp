<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="include/head.jsp" />
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="index.htm">
                    <img alt="Sistema CEM" class="img-responsive" width="350" src="/java.web/resources/dist/img/logo_large.png">
                </a>
            </div>
            <div class="login-box-body">
                <p class="login-box-msg">Ingresar a Sistema CEM</p>
                <form action="./registro.htm" method="POST">
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Ingrese Nombre" id="nombre" name="nombre" type="text" required="required">
                        <span class="glyphicon glyphicon-bookmark form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Ingrese Apellido Paterno" id="apellidoPat" name="apellidoPat" type="text" required="required">
                        <span class="glyphicon glyphicon-briefcase form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Ingrese Apellido Materno" id="apellidoMat" name="apellidoMat" type="text" required="required">
                        <span class="glyphicon glyphicon-cutlery form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Ingrese Usuario" id="login" name="login" type="text" required="required">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Correo" type="email" name="email" required="required">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Contraseña" type="password" name="pass" required="required">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input class="form-control" placeholder="Repetir Contraseña" type="password" name="pass1" required="required">
                        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="checkbox">
                                <input type="checkbox" name="agree" value="1" class="checkbox" required="required"> Acepto los <a href="#">Términos de Uso</a>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Registro</button>
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
                <a href="./login.htm">Ya tengo cuenta en el Sistema</a>
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

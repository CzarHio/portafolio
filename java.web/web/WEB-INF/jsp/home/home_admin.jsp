<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="well"><h1>Bienvenido Admin</h1></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-solid box-primary ">
                    <div class="box-header with-border">
                        <h3 class="box-title">Participación a Programa Pendientes</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Centro</th>
                                    <th>Programa</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaParticipacion}" var="participacion">
                                    <tr>
                                        <td>${participacion.getNombreCentro()}</td>
                                        <td>${participacion.getNombrePrograma()}</td>
                                        <td>${participacion.getFecha()}</td>
                                        <td>${participacion.getEstado()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>  
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Postulación a Programa Pendientes</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Alumno</th>
                                        <th>Programa</th>
                                        <th>Centro</th>
                                        <th>Familia</th>
                                        <th>Fecha</th>
                                        <th>Estado</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listaPostulacion}" var="postulacion">
                                        <tr>
                                            <td>${postulacion.getNombreAlumno()}</td>
                                            <td>${postulacion.getNombrePrograma()}</td>
                                            <td>${postulacion.getNombreCentro()}</td>
                                            <td>${postulacion.getNombreFamilia()}</td>
                                            <td>${postulacion.getFechaCreacion()}</td>
                                            <td>${postulacion.getEstado()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>  
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Inscripción de Familias Pendientes</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Email</th>
                                        <th>Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listaUsuario}" var="usuario">
                                        <tr>
                                            <td>${usuario.getNombre()} ${usuario.getApellidoPat()} ${usuario.getApellidoMat()}</td>
                                            <td>${usuario.getEmail()}</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-md-6">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Últimos Alumnos Registrados</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-original-title="Minimizar" data-toggle="tooltip">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Email</th>
                                        <th>Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listaUsuario}" var="usuario">
                                        <tr>
                                            <td>${usuario.getNombre()} ${usuario.getApellidoPat()} ${usuario.getApellidoMat()}</td>
                                            <td>${usuario.getEmail()}</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    </jsp:body>
</t:Master>

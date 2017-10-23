<html>
    <jsp:include page="head.jsp" />
    <c:if test="${sessionScope.user!= null}">
        <c:redirect url="./perfil.htm"/>
    </c:if>
    <body class="skin-blue fixed sidebar-mini sidebar-mini-expand-feature">
        <!-- Site wrapper -->
        <div class="wrapper" style="height: auto; min-height: 100%;">
            <jsp:include page="main-header.jsp" />
            <jsp:include page="sidebar.jsp" />
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        ${tituloPagina}
                        <small>${subtituloPagina}</small>
                    </h1>
                </section>
                <section class="content">
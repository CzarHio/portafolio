<%@tag description="Pagina Maestra con Template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!sessionScope.logeado.equals('1')}">
   <c:redirect url="/java.web/login.htm"/>
</c:if>
<html>
    <%@attribute name="header" fragment="true" %>
    <!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
    <!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
    <!--[if !IE]><!-->
    <jsp:include page="../jsp/template/head.jsp" />
    <c:if test="${sessionScope.user!= null}">
      <c:redirect url="./perfil.htm"/>
    </c:if>
    <body class="hold-transition skin-blue layout-boxed sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">
            <jsp:include page="/WEB-INF/jsp/template/main-header.jsp" />
            <jsp:include page="/WEB-INF/jsp/template/sidebar.jsp" />
            <div class="content-wrapper">
                <jsp:doBody/>
            </div>
            <jsp:include page="/WEB-INF/jsp/template/footer.jsp" />
            <jsp:include page="/WEB-INF/jsp/template/control-sidebar.jsp" />
        </div>
        <jsp:include page="/WEB-INF/jsp/template/scripts.jsp" />
    </body>
</html>
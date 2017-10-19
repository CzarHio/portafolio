<html>
<jsp:include page="head.jsp" />
<c:if test="${sessionScope.user!= null}">
   <c:redirect url="./perfil.htm"/>
</c:if>
<body class="hold-transition skin-blue layout-boxed sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="main-header.jsp" />
     <jsp:include page="sidebar.jsp" />
     <div class="content-wrapper">
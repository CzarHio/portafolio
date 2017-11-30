<%-- 
    Document   : Master
    Created on : 09-dic-2015, 12:41:50
    Author     : Joe
--%>

<%@tag description="Pagina Maestra" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!sessionScope.logeado.equals('1')}">
   <c:redirect url="/login.htm"/>
</c:if>
<%@attribute name="header" fragment="true" %>
<jsp:include page="/WEB-INF/jsp/include/top.jsp" />
<jsp:doBody/>
<jsp:include page="/WEB-INF/jsp/include/bottom.jsp" />

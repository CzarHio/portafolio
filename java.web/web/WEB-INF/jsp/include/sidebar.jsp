<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
<c:forEach items="${listaMenu}" var="menu">
    ${menu.getTitulo()}<br>
</c:forEach>
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
          
    <c:forEach items="${listaMenu}" var="menu">
        <li class="header">${menu.getTitulo().toUpperCase()}</li>
        
            <c:forEach items="${menu.getListaMenu()}" var="menuItem">
                <c:if test="${menuItem.getPadre()==0}">
                    <li class="treeview">
                        <a href="${menuItem.getUrl()}">
                            <i class="fa fa-dashboard"></i>
                            <span>${menuItem.getTitulo()}</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                            <ul class="treeview-menu">
                                <c:forEach items="${menu.getListaMenu()}" var="menuItem2">
                                  <c:if test="${menuItem2.getPadre()==menuItem.getIdMenuItem()}">
                                     <li><a href="${menuItem2.getUrl()}"><i class="fa fa-circle-o"></i> ${menuItem2.getTitulo()}</a></li>
                                  </c:if>
                                </c:forEach>
                            </ul>
                        </a>
                    </li>
                </c:if> 
            </c:forEach>
    </c:forEach>
    </section>
    <!-- /.sidebar -->
  </aside>

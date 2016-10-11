<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Ignite</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/index.html"><spring:message code="menu.home" /><span class="sr-only"></span></a></li>        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="menu.organizations" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/organization/add.html"><spring:message code="menu.add" /></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/organization/list.html"><spring:message code="menu.list" /></a></li>            
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="menu.teams" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/team/add.html"><spring:message code="menu.add" /></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/team/list.html"><spring:message code="menu.list" /></a></li>            
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="menu.members" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/member/add.html"><spring:message code="menu.add" /></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/member/list.html"><spring:message code="menu.list" /></a></li>            
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="?language=en_US"><img src="${pageContext.request.contextPath}/resources/images/US.png" border="0"></a></li>
        <li><a href="?language=en_UK"><img src="${pageContext.request.contextPath}/resources/images/UK.png" border="0"></a></li>        
      </ul>      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
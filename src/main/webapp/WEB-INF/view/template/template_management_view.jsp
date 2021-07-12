<%@ include file="../parts/header.jsp" %>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/">Home</a></li>
    <li class="breadcrumb-item"><a href="/template">${pageName}</a></li>
    <li class="breadcrumb-item active" aria-current="page">${pagePartName}</li>
  </ol>
</nav>
<div class="container" style="max-height: 75%;">
    <h2 style="text-align: center">Template Management</h2>
</div>
<c:if test="${pagePartName == 'Template List'}">
    <%@ include file="template_list.jsp" %>
</c:if>
<c:if test="${pagePartName == 'Template Creation'}">
    <%@ include file="template_creator_view.jsp" %>
</c:if>
<%@ include file="../parts/footer.jsp" %>
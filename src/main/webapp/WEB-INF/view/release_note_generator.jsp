<%@ include file="parts/header.jsp" %>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Release Note Generator</li>
  </ol>
</nav>
<div class="modal bd-example-modal-lg" id="modal">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <span class="close" id="closeModal">  &times;</span>
            <div class="modal-body" id="modalBody">
            </div>
            <script>
            $('#closeModal').click(function() {
                $('#modalBody').empty();
                modal.style.display = "none";
            });
            </script>
        </div>
    </div>
</div>
<div class="container">
    <h2 style="text-align: center">Release Notes Generator</h2>
</div>
<div style="display: flex; justify-content: center; align-items: center;">
    <div class="container border rounded" style="width: 50%; display: inline-block;">
        <form class="container" style="margin: 5px;" method="GET" action="/releasenotes/create">
            <table class="table">
                <tr class="form-group">
                    <td colspan="1"><label for="project">Project:</label></td>
                    <td colspan="2">
                        <select class="form-control" id="project" name="project" required>
                            <option>BBox</option>
                            <option>IM</option>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td colspan="1"><label for="version">Version:</label></td>
                    <td colspan="2">
                        <input type="text" class="form-control" id="version" name="version" required>
                    </td>
                </tr>
                <tr class="form-group">
                    <td colspan="1"><label for="template">Template:</label></td>
                    <td colspan="2">
                        <select class="form-control" id="template" name="template" required>
                            <c:forEach items="${templates}" var="template">
                                <option value="${template.name}">${template.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td colspan="3">
                        <button type="button" id="submit" class="btn btn-primary" value="Start">Start</button>
                        <script>
                        $("#submit").click(function(){
                            var BASE_URL = window.location.origin+"/releasenotes/create?";
                            var project = 'project='+document.getElementById("project").value;
                            var version = '&version='+document.getElementById("version").value;
                            var selectedTemplate = '&template='+document.getElementById("template").value;
                            var visualUrl = BASE_URL+project+version+selectedTemplate;
                            var modal = document.getElementById("modal");
                            var span = document.getElementById("closeModal");
                            $.get(visualUrl, function(data){
                                modal.style.display = "block";
                                $('#modalBody').append(data);
                            });
                        });
                        </script>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ include file="parts/footer.jsp" %>
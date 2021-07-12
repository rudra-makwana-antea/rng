<%@ include file="parts/header.jsp" %>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item active" aria-current="page">Home</li>
  </ol>
</nav>
<div class="container">
    <h2 style="text-align: center; padding-bottom: 5px;">Generated Release Notes</h2>
</div>
<div  id="message" style="display: none; width: 25%; text-align: center;"
    class="alert alert-success container" role="alert">
    <p>Link copied</p>
</div>
<div class="modal" id="modal">
    <div class="modal-dialog modal-dialog-scrollable modal-xl">
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
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Release Notes For</th>
                <th scope="col">Template</th>
                <th scope="col">Visualize</th>
                <th scope="col">Refetch</th>
                <th scope="col">Copy Link</th>
                <th scope="col">Export</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${releases == null}">
                <tr>
                    <td><p>THERE ARE NO RELEASES</p></td>
                </tr>
            </c:if>
            <c:if test="${releases != null}">
                <c:forEach items="${releases}" var="projectVersion" varStatus="loop">
                    <tr>
                        <form>
                            <td scope="row"><input type="hidden" id="${projectVersion}" name="projectVersion" value="${projectVersion}">${projectVersion}</td>
                            <td>
                                <select class="form-control" id="templates_${projectVersion}" name="selectedTemplate">
                                    <c:forEach items="${templates}" var="template">
                                        <option value="${template.name}">${template.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><button type="button" class="btn btn-success" value="Visualize" id="visual_${loop.index}">Visualize</button></td>
                            <td><button type="button" class="btn btn-info" value="Refetch" id="refetch_${loop.index}">Refetch</button></td>
                            <td><button type="button" class="btn btn-secondary" value="Copy Link" id="copy_${loop.index}">Copy Link</button></td>
                            <td><button type="button" class="btn btn-primary" value="Export" id="export_${loop.index}">Export</button></td>
                            <script>
                                $("#copy_${loop.index}").click(function(){
                                    var BASE_URL = window.location.origin+"/visualize?";
                                    var projectVersion = 'projectVersion='+document.getElementById("${projectVersion}").value;
                                    var selectedTemplate = '&selectedTemplate='+document.getElementById("templates_${projectVersion}").value;
                                    var copyUrl = BASE_URL+projectVersion+selectedTemplate;
                                    navigator.clipboard.writeText(copyUrl);
                                    var box = document.getElementById("message");
                                    box.style.display = "block";
                                    $("#message").delay(3000).slideUp(300);
                                });
                                $("#visual_${loop.index}").click(function(){
                                    var BASE_URL = window.location.origin+"/visualize?";
                                    var projectVersion = 'projectVersion='+document.getElementById("${projectVersion}").value;
                                    var selectedTemplate = '&selectedTemplate='+document.getElementById("templates_${projectVersion}").value;
                                    var visualUrl = BASE_URL+projectVersion+selectedTemplate;
                                    var modal = document.getElementById("modal");
                                    var span = document.getElementById("closeModal");
                                    $.get(visualUrl, function(data){
                                        modal.style.display = "block";
                                        $('#modalBody').append(data);
                                    });
                                });
                                $("#export_${loop.index}").click(function(){
                                    var BASE_URL = window.location.origin+"/export?";
                                    var projectVersion = 'projectVersion='+document.getElementById("${projectVersion}").value;
                                    var selectedTemplate = '&selectedTemplate='+document.getElementById("templates_${projectVersion}").value;
                                    var exportUrl = BASE_URL+projectVersion+selectedTemplate;
                                    var modal = document.getElementById("modal");
                                    var span = document.getElementById("closeModal");
                                    $.get(exportUrl, function(data){
                                        modal.style.display = "block";
                                        $('#modalBody').append(data);
                                    });
                                });
                                $("#refetch_${loop.index}").click(function(){
                                    var BASE_URL = window.location.origin+"/refetch?";
                                    var projectVersion = 'projectVersion='+document.getElementById("${projectVersion}").value;
                                    var selectedTemplate = '&selectedTemplate='+document.getElementById("templates_${projectVersion}").value;
                                    var refetchUrl = BASE_URL+projectVersion+selectedTemplate;
                                    $("#refetch_${loop.index}").html("Loading");
                                    $.get(refetchUrl, function(data){
                                        if(data == "reloaded"){
                                            $("#refetch_${loop.index}").html("Refetch");
                                        }
                                    });
                                });
                            </script>
                        </form>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</div>
<%@ include file="parts/footer.jsp" %>
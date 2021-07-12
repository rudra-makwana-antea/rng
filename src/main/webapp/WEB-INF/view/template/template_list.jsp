<div class="container" style="text-align: center">
    <a href="/template/creation" class="btn btn-primary" role="button" style="text-align: center;">New Template</a>
</div>
<div class="row" style="margin: 5px;">
<div class="container-fluid col border rounded" style="margin: 5px;">
    <div class="container">
        <h2 style="text-align: center">Templates</h2>
    </div>
    <table class="table">
        <tbody>
            <c:if test="${templates == null}">
                <tr>
                    <td><p>THERE ARE NO TEMPLATES AVAILABLE</p></td>
                </tr>
            </c:if>
            <c:if test="${templates != null}">
                <c:forEach items="${templates}" var="template" varStatus="loop">
                    <form method="get">
                        <tr>
                            <td scope="row">${loop.index+1}</td>
                            <td scope="row"><b>${template.name}</b></td>
                            <td>
                                <input type="hidden" name="templateName" value="${template.name}">
                                <button type="submit" class="btn btn-secondary" formaction="/template/preview">Preview</button>
                            </td>
                            <td>
                                <input type="submit" class="btn btn-danger" formaction="/template/delete" value="Delete">
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</div>
<div class="container-fluid col border rounded" style="margin: 5px; overflow: scroll; height:600px;">
    <div class="container">
        <h2 style="text-align: center;">Preview</h2>
    </div>
    <hr/>
    <div class="overflow-auto">
        <c:if test="${previewTemplate == null}">
            <p>Click on any of the template in lists to see its preview</p>
        </c:if>
        <c:if test="${previewTemplate != null}">
            ${previewTemplate}
        </c:if>
    </div>
</div>
</div>
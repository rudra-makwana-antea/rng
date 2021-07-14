<div class="container" style="text-align: center;">
    <a href="/template" class="btn btn-primary" role="button" style="text-align: center;">Template List</a>
</div>
<div class="row" style="margin: 5px;">
<div class="container-fluid col border rounded" style="margin: 5px; overflow: scroll; height:600px;">
    <div class="container">
        <h2 style="text-align: center">New Template Form</h2>
    </div>
    <hr style="margin-top:0px;"/>
    <form:form action="/template" method="POST" modelAttribute="template">
        <div class="form-group">
            <form:label for="name" path="name">Name</form:label>
            <form:input type="text" class="form-control" id="name" path="name"/>
        </div>
        <div class="form-group">
            <form:hidden class="form-control" id="templateId" path="id" rows="3"></form:hidden>
            <label for="templateHeader">Header</label>
            <form:textarea class="form-control" id="templateHeader" path="header" aria-describedby="headerHelp" rows="3"></form:textarea>
            <small id="headerHelp" class="form-text text-muted">Appears at the start of the release notes.</small>
        </div>
        <div class="form-group">
            <form:label for="issue" path="issue">Each Issue</form:label>
            <form:textarea class="form-control" id="issue" path="issue" aria-describedby="issueHelp" rows="3"></form:textarea>
            <small id="issueHelp" class="form-text text-muted">Appears once for each issue.</small>
        </div>
        <div class="form-group">
            <form:label for="footer" path="footer">Footer</form:label>
            <form:textarea class="form-control" id="footerPart" path="footer" aria-describedby="footerHelp" rows="3"></form:textarea>
            <small id="footerHelp" class="form-text text-muted">Appears after the issue end. It is optional.</small>
        </div>
      <button type="submit" class="btn btn-primary" onClick="myFun()">Submit</button>
    </form:form>
</div>
<div class="container-fluid col border rounded" style="margin: 5px;">
    <div class="container">
        <h2 style="text-align: center;">How To</h2>
    </div>
    <hr style="margin-top:0px;"/>
    <div class="overflow-auto">
        <p>You can use substitutions like <mark>&#36&#123this&#125</mark> to display some information in the Header and Each issue fields.</p>
        <p>Example: &#36&#123version&#125</p>
        <p class="h3">Header</p>
        <p>Substitutions refer to the version. Recognised values are version and project.</p>
        <p class="h3">Each Issue</p>
        <p>Substitutions refer to the issue. Recognised values are ID, numberInProject, releaseNote, tracker, project and
           version, url, and date.</p>
    </div>
</div>
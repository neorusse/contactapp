<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading">Add Contact</div>
                <div class="panel-body">
                    <form:form action="/contact" method="post" modelAttribute="contact">
                        <form:hidden path="id" />
                        <fieldset class="form-group">
                            <form:label path="firstName">First Name</form:label>
                            <form:input path="firstName" type="text" class="form-control" required="required" />
                            <form:errors path="firstName" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="lastName">Last Name</form:label>
                            <form:input path="lastName" type="text" class="form-control" required="required" />
                            <form:errors path="lastName" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="gender">Gender</form:label>
                            <form:input path="gender" type="text" class="form-control" required="required" />
                            <form:errors path="gender" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="phone">Phone</form:label>
                            <form:input path="phone" type="text" class="form-control" required="required" />
                            <form:errors path="phone" cssClass="text-warning" />
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
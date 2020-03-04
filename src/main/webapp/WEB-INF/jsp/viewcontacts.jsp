<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>

<div class="container">
    <div>
        <a type="button" class="btn btn-primary btn-md" href="/contact">Add Contact</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Contact List</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th width="16%">S/N</th>
                        <th width="16%">First Name</th>
                        <th width="16%">Last Name</th>
                        <th width="16%">Gender</th>
                        <th width="16%">Phone</th>
                        <th width="16%">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td>${contact.id}</td>
                            <td>${contact.firstName}</td>
                            <td>${contact.lastName}</td>
                            <td>${contact.gender}</td>
                            <td>${contact.phone}</td>
                            <td><a type="button" class="btn btn-success"
                                   href="/update-contact?id=${contact.id}">Update</a>&nbsp;&nbsp;&nbsp;
                                <a type="button" class="btn btn-danger" onclick="return confirm('Please confirm your action!')"
                                   href="/delete-contact/${contact.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<%@ include file="common/footer.jspf"%>
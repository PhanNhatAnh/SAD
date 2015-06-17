<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<section class="content-mid" style="width: 80%;">
	<div class="content-mid-header">
		<h1>Manage Account</h1>
	</div>
	<div class="problem-container">
		<div class="problem-item">
		<table id="myTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>No.</th>
					<th>Account Name</th>
					<th>isAdmin</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="acc" items="${LISTACC }" varStatus="num">
					<tr><form action="updateAcc" method="get">
						<td>${num.count }<input name="id" value="${acc.accountID }" type="hidden"/></td>
						<td><input name="username" value="${acc.username }" type="text"/></td>
						<td>
							<c:if test="${acc.role == 'admin'}">
								<input type="checkbox" name="isAdmin" checked="checked">
							</c:if>
							<c:if test="${acc.role != 'admin'}">
								<input type="checkbox" name="isAdmin" >
							</c:if>
						</td>
						<td><input type="submit" value="Update" class="btn-info"> </td>
						<td><a href="deleteAcc?id=${acc.accountID }">
						<img alt="delete" src="resources\img\remove.png"> </a></td>
						</form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</section>
<!-- DATA TABES SCRIPT -->
<script src="//code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js" type="text/javascript"></script>
<<script type="text/javascript">
$(document).ready(function() {
    $('#myTable').dataTable();
} );
</script>

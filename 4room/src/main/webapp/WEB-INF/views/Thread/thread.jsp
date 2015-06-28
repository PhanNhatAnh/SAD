<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!-- AdminLTE -->
<link href="resources/AdminLTE/css/AdminLTE.css" rel="stylesheet"
	type="text/css" />
<section class="content-mid" style="width: 80%;">
	<div class="content-mid-header">
		<h1>${THREAD.name }</h1>
	</div>
	<div class="problem-container">
		<c:forEach var="comment" items="${COMMENTS }">
			<div class="problem-item">
				<div class="problem-header">
					<div class="problem-user">
						<a href=""><img width="17px" height="17px"
							src="resources/img/avatar/${comment.accountID.avatarImg}.jpg">
							<span>${comment.accountID.username }</span></a>
					</div>
					<span class="post-time"> <fmt:formatDate
							value="${comment.lastEdit }" pattern="${DATETIME_FORMAT}" /></span>
					<!-- user is admin -->
					<c:if test="${USER.role == 'admin'}">
						<span class="post-view"> <a href="editComment?id=${comment.commentID }"><span
								class="glyphicon glyphicon-pencil">Edit</span></a> <input
							type="checkbox">
						</span>
					</c:if>
				</div>
				<div class="problem-body">
					<div class="problem-content" style="margin-left: 0px;">
						<h2>${comment.content}</h2>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</c:forEach>
		<!-- not empty user -->
		<c:if test="${not empty USER}">
			<div class="problem-item">
				<div class="problem-body">
					<div class='box-body pad'>
						<form action="sendComment" method="post">
							<textarea id="editor" name="comment" rows="5" cols="100"
								placeholder="Write comment....">                                            
                    </textarea>
							<input type="hidden" name="threadID" value="${THREAD.threadID }">
							<input type="submit" value="Send">
						</form>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</section>
<!-- CK Editor -->
<script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('editor');
	});
</script>
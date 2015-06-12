<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
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
			</div>
			<div class="problem-body">				
				<div class="problem-content">
					<h2>${comment.content}</h2>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		</c:forEach>
	</div>
</section>

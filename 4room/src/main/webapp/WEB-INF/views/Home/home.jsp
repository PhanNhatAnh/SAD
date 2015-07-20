<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>
<section class="content-mid">
	<div class="content-mid-header">
		<h4><a href="home">4 room</a></h4> 
		<c:if test="${not empty USER }">
			<button type="button"><a href="newThread">New Thread</a></button>
		</c:if>
		<c:if test="${empty USER }">
			<h1></h1>
		</c:if>
		<div class="tabs">
			<ul>
				<li class="active"><a href="newest">newest</a></li>
				<li><a href="oldest">oldest</a></li>							
			</ul>
		</div>
	</div>
	<div class="problem-container">
		<c:forEach var="thread" items="${THREAD }">
			<div class="problem-item">
				<div class="problem-header">
					<div class="problem-user">
						<a href=""><img width="17px" height="17px"
							src="resources/img/avatar/${thread.lastUpdateBy.avatarImg}.jpg">
							<span>${thread.lastUpdateBy.username }</span></a>
					</div>
					<span class="post-time"><fmt:formatDate
							value="${thread.lastUpdate }" pattern="${DATETIME_FORMAT}" /> </span>
					<!-- user is admin -->
					<c:if test="${USER.role == 'admin'}">
						<span class="post-view"> <a href="#"><span
								class="glyphicon glyphicon-pencil">Edit</span></a> <input
							type="checkbox">
						</span>
					</c:if>
				</div>
				<div class="problem-body">
					<div class="problem-content" style="margin-left: 0px;">
						<a href="thread?threadID=${thread.threadID }"
							style="text-decoration: none;">
							<h2>${thread.name}</h2>
						</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<script type="text/javascript">
$(document).ready(function () {
	var selector = '.tabs li';

	$(selector).on('click', function(){
	    $(selector).removeClass('active');
	    $(this).addClass('active');
	});
});
</script>

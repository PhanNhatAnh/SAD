<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<section class="content-mid">
	<div class="content-mid-header">
		<h1>4 room</h1>
		<div class="tabs">
			<ul>
				<li class="active"><a href="#">newest</a></li>
				<li><a href="#">oldest</a></li>
				<li><a href="#">my thread</a></li>
				<li><a href="#">my follows</a></li>
			</ul>
		</div>
	</div>
	<div class="problem-container">
		<c:forEach var="thread" items="${THREAD }">
			<div class="problem-item">
			<div class="problem-header">
				<div class="problem-user">
					<a href=""><img width="17px" height="17px" 
					src="resources/img/avatar/${thread.accountID.avatarImg}.jpg">
					<span>${thread.accountID.username }</span></a>
				</div>
				<span class="post-time">just now</span> <span class="post-view">123
					comment</span>
			</div>
			<div class="problem-body">
				<div class="problem-vote">
					<div class="stat-count">16</div>
					<div class="stat-desc">follows</div>
				</div>
				<div class="problem-content">
					<a href="#" style="text-decoration: none;">
					<h2>${thread.name}</h2></a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		</c:forEach>
		
	</div>
</section>
<nav class="content-right" style="float: right;">
	<div class="widget-welcomebox">
		<h2>SpaceShip Battle</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Phasellus urna nisi, fringilla quis bibendum sit amet, malesuada id
			eros. Sed suscipit aliquam volutpat...</p>
		<p>
			<a href="#">Read More...</a>
		</p>
	</div>
	<div class="subscriptions widget">
		<h3 class="widget-header">
			<a href="#">Online members</a>
		</h3>
		<ul class="widget-list">
		<c:forEach var="member" items="${MEMBERS }">
			<li><a class="listitem" href=""><img width="17px"
					height="17px" src="resources/img/avatar/${member.avatarImg }.jpg">${member.username}</a></li>
		</c:forEach>
			
		</ul>
	</div>
</nav>
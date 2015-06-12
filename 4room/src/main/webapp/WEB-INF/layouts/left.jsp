<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="content-left">
	<div class="profilewidget">
		<c:if test="${not empty USER}">
			<div class="useravatar">
				<img alt="${USER.username}"
					src="resources/img/avatar/${USER.avatarImg}.jpg"
					style="border: 1px solid #e8dbdb">
			</div>
			<div class="profilewidget-name">
				<div>
					<a class="profilewidget-username" href="#">${USER.username}</a>
				</div>
				<div class="profilewidget-stats">
					<c:if test="${USER.role == 'admin'}">
						<img alt="icon"
							src="resources/img/icon/${USER.accIconID.icon }.png"
							style="width: 18px; height: 18px;">
						<font color="red">Administration</font>
					</c:if>
					<c:if test="${USER.role == 'member'}">
						<img alt="icon"
							src="resources/img/icon/${USER.accIconID.icon }.png"
							style="width: 18px; height: 18px;">
						<font color="blue">member</font>
					</c:if>
				</div>
			</div>
		</c:if>
		<!-- not empty user -->
		<c:if test="${empty USER}">
			<div class="useravatar">
				<img alt="" src="resources/img/avatar/guest.jpg"
					style="border: 1px solid #e8dbdb">
			</div>
			<div class="profilewidget-name">
				<div>
					<a class="profilewidget-username" href="#">Guest</a>
				</div>
		</c:if>

	</div>
	<div class="activity widget">
		<h3 class="widget-header">
			<a href="#">ACTIVITY</a>
		</h3>
		<ul class="widget-list">
			<li><a class="listitem" href="#">Threads <c:if
						test="${NUMTHREAD != 0}">
						<span class="widget-item-count">${NUMTHREAD}</span>
					</c:if></a></li>
			<li><a class="listitem" href="#">Comments <c:if
						test="${NUMCOMMENT != 0}">
						<span class="widget-item-count">${NUMCOMMENT}</span>
					</c:if>
			</a></li>
			<li><a class="listitem" href="#">Messages <c:if
						test="${NUMMESS != 0}">
						<span class="widget-item-count">${NUMMESS}</span>
					</c:if>
			</a></li>
		</ul>
	</div>
	<div class="subscriptions widget" 
	style="border-top: 1px solid #e5e3e4;margin-right: 10px;">
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
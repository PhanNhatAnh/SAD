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
				<img alt="" src="resources/img/avatar/member.jpg"
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
			<li><a class="listitem" href="#">My Thread <span
					class="widget-item-count">2</span></a></li>
			<li><a class="listitem" href="#">Comment <span
					class="widget-item-count">5</span></a></li>
			<li><a class="listitem" href="#">Messages</a></li>
		</ul>
	</div>
	<div class="widget-button">
		<h4 class="flat3d-button">Top 10 of Score</h4>
	</div>
	<div class="hotquestions widget">
		<ul class="widget-wraplist">
			<c:forEach var="rate" items="${RATE}">
				<li><img src="resources/img/icon/icon2.png"
					style="width: 18px; height: 18px;"> <font color="">${rate.name}</font>
					<font color="" style="float: right; padding-right: 5px;">${rate.score}</font></li>
			</c:forEach>
		</ul>
	</div>
	<!-- <div class="favoritetags widget">
		<h3 class="widget-header">
			<a href="#">FAVORITE TAGS</a>
		</h3>
		<div class="widget-taglist">
			<a href="" class="tag">equation</a> <a href="" class="tag">caculus</a>
			<a href="" class="tag">olympia</a> <a href="" class="tag">lhp</a>
		</div>
	</div>
	<div class="topics widget">
		<h3 class="widget-header">
			<a href="#">TOPICS</a>
		</h3>
		<ul class="widget-list">
			<li><a class="listitem" href="">Math Olympia <span
					class="widget-item-count">3</span></a></li>
			<li><a class="listitem" href="">LHP Math Group</a></li>
			<li><a class="listitem" href="">Madyss the kadric... <span
					class="widget-item-count">20+</span></a></li>
		</ul>
	</div>
	<div class="subscriptions widget">
		<h3 class="widget-header">
			<a href="#">SUBCRIPTSTION</a>
		</h3>
		<ul class="widget-list">
			<li><a class="listitem" href=""><img width="17px"
					height="17px" src="img/hainnt.png">HaiNNT</a><span
				class="widget-item-count">4</span></li>
			<li><a class="listitem" href=""><img width="17px"
					height="17px" src="img/hainnt.png">Thao Huynh</a></li>
		</ul>
	</div> -->
</nav>
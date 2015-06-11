<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<section class="content-mid">
	<div class="content-mid-header">
		<h1>All problems</h1>
		<div class="tabs">
			<ul>
				<li class="active"><a href="#">newest</a></li>
				<li><a href="#">trending</a></li>
				<li><a href="#">my tags</a></li>
				<li><a href="#">unanswered</a></li>
			</ul>
		</div>
	</div>
	<div class="problem-container">
		<div class="problem-item">
			<div class="problem-header">
				<div class="problem-user">
					<a href=""><img width="17px" height="17px" src="img/hainnt.png"><span>HaiNNT</span></a>
				</div>
				<span class="post-time">just now</span> <span class="post-view">123
					views</span>
			</div>
			<div class="problem-body">
				<div class="problem-vote">
					<div class="stat-count">16</div>
					<div class="stat-desc">votes</div>
					<div class="stat-count">2</div>
					<div class="stat-desc">answers</div>
				</div>
				<div class="problem-content">
					<h2>Nunc id mi eget massa imperdiet posuere. Integer blandit,
						sapien eget bibendum gravida?</h2>
					<p>Aliquam congue, enim a malesuada luctus, mi libero molestie
						ipsum, quis la cinia sapien augue a nunc. Curabitur quis lobortis
						dolor. Vestibulum non molestie dui. Vivamus vestibulum placerat
						aliquam. Proin semper sem non nisl aliquet commodo sit amet
						lobortis felis. Quisque quis nisl non massa luctus mattis. Mauris
						in...</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="problem-footer">
				<a class="seemore-link" href="#">See more...</a> <span class="tags">
					<a href="" class="tag">equation</a> <a href="" class="tag">caculus</a>
					<a href="" class="tag">olympia</a> <a href="" class="tag">lhp</a>
				</span>
			</div>
		</div>
		<div class="problem-item">
			<div class="problem-header">
				<div class="problem-user">
					<a href=""><img width="17px" height="17px" src="img/hainnt.png"><span>HaiNNT</span></a>
				</div>
				<span class="post-time">just now</span> <span class="post-view">123
					views</span>
			</div>
			<div class="problem-body">
				<div class="problem-vote">
					<div class="stat-count">16</div>
					<div class="stat-desc">votes</div>
					<div class="stat-count">2</div>
					<div class="stat-desc">answers</div>
				</div>
				<div class="problem-content">
					<h2>Nunc id mi eget massa imperdiet posuere. Integer blandit,
						sapien eget bibendum gravida?</h2>
					<p>Aliquam congue, enim a malesuada luctus, mi libero molestie
						ipsum, quis la cinia sapien augue a nunc. Curabitur quis lobortis
						dolor. Vestibulum non molestie dui. Vivamus vestibulum placerat
						aliquam. Proin semper sem non nisl aliquet commodo sit amet
						lobortis felis. Quisque quis nisl non massa luctus mattis. Mauris
						in...</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="problem-footer">
				<a class="seemore-link" href="#">See more...</a> <span class="tags">
					<a href="" class="tag">equation</a> <a href="" class="tag">caculus</a>
					<a href="" class="tag">olympia</a> <a href="" class="tag">lhp</a>
				</span>
			</div>
		</div>
	</div>
</section>
<nav class="content-right">
	<div class="widget-welcomebox">
		<h2>SpaceShip Battle</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Phasellus urna nisi, fringilla quis bibendum sit amet, malesuada id
			eros. Sed suscipit aliquam volutpat...</p>
		<p>
			<a href="#">Read More...</a>
		</p>
	</div>
	<div class="widget-button">
		<h4 class="flat3d-button">Top Ten Rating</h4>
	</div>
	<div class="hotquestions widget">
		<ul class="widget-wraplist">
			<c:forEach var="rate" items="${RATE}">
					<li><img src="resources/img/logo.jpg" style="width: 18px;height: 18px;">
					<font color="">${rate.name}</font>  
					<font color="" style="float: right;">${rate.score}</font></li>
				</c:forEach>
		</ul>
	</div>
</nav>
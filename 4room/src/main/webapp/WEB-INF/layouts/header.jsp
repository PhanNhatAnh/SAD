<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
a.username {
	color: white;
	display: inline-block;
	vertical-align: middle;
	font-size: 13px;
	text-decoration: none;
}

.useravatar {
	margin: 9px;
	display: inline-block;
	vertical-align: middle;
}

.useravatar img {
	height: 32px;
	width: 32px;
}

.menu_profile {
	display: inline-block;
}

.menu_profile:hover .sub-menu {
	z-index: 1;
	opacity: 1;
}

.sub-menu {
	padding: 5px 0px;
	position: absolute;
	z-index: -1;
	opacity: 0;
	transition: opacity linear 0.15s;
	box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.2);
	background: #ffffff;
	width: 170px;
	top: 30px;
	left: 1060px;
}

.sub-menu li {
	display: block;
	font-size: 14px;
}

.sub-menu li a {
	padding: 10px 30px;
	display: block;
	text-decoration: none;
}

.sub-menu li a:hover,.sub-menu .current-item a {
	background: #a73e2d;
	text-decoration: none;
}
</style>
<header>
	<div class="container">
		<div class="logo">
			<a href="home"><h3>SpaceShip Battle</h3></a>
		</div>
		<ul class="navmenu">
			<li><a href="#">Event</a></li>
			<li><a href="#">Discussion</a></li>
			<li><a href="#">Download</a></li>
		</ul>
		<div class="profile">
		<c:if test="${not empty USER}">
			<div class="notification">
				<span class="notif active"> <span class="count">27</span>
				</span> <span class="mess"> </span>
			</div>
			<div class="menu_profile" style="display: inline-block;">
					<a href="#" class="username">${USER.username}
						<div class="useravatar">
							<img alt="${USER.username}" src="resources/img/avatar.jpg">
						</div>
					</a>
					<ul class="sub-menu">
						<li><a href="#">My Profile</a></li>
						<li><a href="logout">Logout</a></li>
					</ul>
				</c:if>
				
				<!-- not empty user -->
				<c:if test="${empty USER}">
				<div style="margin-top: 10px;">
				<font color="white"><a href="login">Login</a></font> || <font color="white">
					<a href="register">Register</a></font>
				</div>
				</c:if>
			</div>
		</div>
	</div>
</header>
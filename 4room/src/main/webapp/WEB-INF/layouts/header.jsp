<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
header {
	width: auto;
	height: 50px;
	background-color: #a73e2d;
	cursor: default;
	margin: auto;
	margin-bottom: 10px;
}

.container {
	max-width: 1000px;
	margin: auto;
}

.logo {
	background-size: 50%;
	background-position: center;
	background-repeat: no-repeat;
	display: inline-block;
	width: 200px;
	height: 60px;
	vertical-align: middle;
}

ul.navmenu {
	display: inline-block;
	list-style-type: none;
	margin: 0;
	padding: 0;
	vertical-align: middle;
}

ul.navmenu li.active {
	background-color: rgba(0, 0, 0, 0.3);
}

ul.navmenu li {
	display: inline-block;
	color: white;
	font-size: 13px;
	padding: 7px 13px;
	border-radius: 5px;
	text-shadow: 0 3px 5px rgba(0, 0, 0, 0.6);
}

ul.navmenu li a {
	color: white;
	text-decoration: none;
}

.unlogin {
	float: right;
	height: 50px;
	padding: 0;
	margin-top: 5px;
	vertical-align: middle;
}

.login {
	float: right;
	height: 50px;
	padding: 0;
	vertical-align: middle;
}

.search {
	width: 150px;
	height: 5px;
	padding: 0;
	vertical-align: middle;
}


.notification {
	display: inline-block;
	margin-right: 10px;
}

.notification span.notif.active {
	background-position: 0 -17px;
	opacity: 1;
}

.notification>span .count {
	background-color: white;
	opacity: 1;
	position: absolute;
	font-size: 8px;
	font-weight: bold;
	padding: 0 3px;
	border-radius: 2px;
	box-shadow: 0 1px 1px black;
	right: -4px;
	top: -6px;
}

.menu_profile {
	display: inline-block;
}

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

.notification>span {
	margin: 0 5px;
	display: inline-block;
	vertical-align: middle;
	width: 17px;
	height: 17px;
	background-image: url('QnA/../img/notification.png');
	background-repeat: no-repeat;
	cursor: pointer;
	opacity: 0.7;
	position: relative;
}

.menu {
	width: 1000px;
	margin: 0px auto;
}

.menu li {
	margin: 0px;
	list-style: none;
	font-family: 'Ek Mukta';
}

.menu a {
	transition: all linear 0.15s;
	color: #919191;
}

.menu li:hover>a,.menu .current-item>a {
	text-decoration: none;
	color: #be5b70;
}

.menu .arrow {
	font-size: 11px;
	line-height: 0%;
}

/*----- Top Level -----*/
.menu>ul>li {
	float: left;
	display: inline-block;
	position: relative;
	font-size: 19px;
}

.menu>ul>li>a {
	padding: 10px 40px;
	display: inline-block;
	text-shadow: 0px 1px 0px rgba(0, 0, 0, 0.4);
}

.menu>ul>li:hover>a,.menu>ul>.current-item>a {
	background: #2e2728;
}

/*----- Bottom Level -----*/
/*.menu li:hover .sub-menu {
        z-index:1;
        opacity:1;
    }*/
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
	font-size: 16px;
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
			<!-- <a href="home"><img src="resources/img/logo.jpg" width="170px"
				height="50px" /></a> -->
			<a href="home"><h3 style="color: white;margin-top: 15px;">SpaceShip Battle</h3></a>	
		</div>
		<ul class="navmenu">
			<li><a href="#">Event</a></li>
			<li><a href="#">Discussion</a></li>
			<li><a href="#">Dowload</a></li>
		</ul>
		<ul class="navmenu">
			<form action="search">
				<input type="text" name="txtSearchWord" value=""
					placeholder="Search" /> <input type="submit" value="Search"
					name="action" />
			</form>
		</ul>
		<c:if test="${user != null}">
			<div style="float: right;height: 50px;">
				<div class="notification">
					<span class="notif active"> <span class="count">27</span>
					</span>
				</div>

				<div class="menu_profile">
					<a href="#" class="username">${user.username}
						<div class="useravatar">
							<span class="glyphicon glyphicon-user"></span>
						</div>
					</a>
					<ul class="sub-menu">
						<c:url var="profile" value="DispatcherServlet">
							<c:param name="action" value="myprofile" />
							<c:param name="userId" value="${user.userId}" />
						</c:url>
						<li><a href="${profile}">My Profile</a></li>
						<li><a href="logout">Logout</a></li>
						<c:if test="${user.role.roleName eq 'admin'}">
							<li><a href="admin/management">Admin Panel</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>

		<!-- empty user -->
		<c:if test="${empty user}">
			<div style="float: right;height: 50px;">
				<a href="login">Login</a> <a href="register">Register</a>
			</div>
		</c:if>
	</div>
</header>
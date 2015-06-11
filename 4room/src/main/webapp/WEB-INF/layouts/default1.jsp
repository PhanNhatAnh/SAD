<%@ page language="java" contentType="charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SpaceShip Battle</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="resources/css/normalize.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
	<!--header-->
	<tiles:insertAttribute name="header" ignore="true" />
	<!-- /header -->

	<section class="content">
		<!-- left -->
		<tiles:insertAttribute name="left" />
		<!-- /left -->

		<!-- Content -->
		<tiles:insertAttribute name="body" />
		<!-- /Content -->
		<br class="spacer" />
	</section>

	<!-- footer -->
	<tiles:insertAttribute name="footer" />
	<!-- /footer -->
	<script src="resources/js/plugins.js"></script>
	<script src="resources/js/main.js"></script>
</body>
</html>

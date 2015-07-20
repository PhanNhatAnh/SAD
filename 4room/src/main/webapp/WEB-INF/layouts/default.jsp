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
<!-- BOOSTRAP -->
<link rel="stylesheet" href="resources/bootstrap/css/boostrap.min.css">
<!-- DATA TABLES -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<script src="resources/js/vendor/modernizr-2.6.2.min.js"></script>
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/bootstrap/js/boostrap.min.js"></script>
</head>
<body style="background: url(resources/img/new.png);">
	<!--header-->
	<tiles:insertAttribute name="header" ignore="true" />
	<!-- /header -->

	<section class="content">
		<!-- right -->
		<tiles:insertAttribute name="right" ignore="true"/>
		<!-- /right -->

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

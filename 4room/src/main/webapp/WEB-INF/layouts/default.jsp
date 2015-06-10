<%@ page language="java" contentType="charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SpaceShip Battle</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap-theme.min.css">

<!-- jQuery -->
<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>

</head>

<body>
	<!--header-->
	<tiles:insertAttribute name="header" ignore="true" />
	<!-- /header -->

	<!-- body -->
	<div class="container">
		<div class="">
			<!-- not empty user -->
			<c:if test="${user != null}">

			</c:if>

			<!-- left -->
			<div class="pull-left">
				<tiles:insertAttribute name="left" />
			</div>
			<!-- /left -->

			<!-- right -->
			<div class="pull-right">
				<tiles:insertAttribute name="right" />
			</div>
			<!-- /right -->

			<!-- Content -->
			<tiles:insertAttribute name="body"/>
			<!-- /Content -->

		</div>
	</div>
	<!-- /body -->

	<!-- footer -->
	<tiles:insertAttribute name="footer" />
	<!-- /footer -->

	<!-- bootstrap -->
	<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
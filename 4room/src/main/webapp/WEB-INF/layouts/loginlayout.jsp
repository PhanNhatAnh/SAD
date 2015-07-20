<%@ page language="java" contentType="charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SpaceShip Battle</title>

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="resources/bootstrap/css/bootstrap-theme.min.css">

<!-- jquery -->
<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>

</head>
<body class="loginPage" style="background: url(resources/img/new.png);">
  <div class="row">
    <div class="container">
      <a href="home">
        <h3>SpaceShip Battle</h3>
      </a>
    </div>
  </div>
  <!-- End .row -->

  <!-- Content -->
  <div class="container">
    <tiles:insertAttribute name="body" />
  </div>

<!-- Bootstrap -->
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
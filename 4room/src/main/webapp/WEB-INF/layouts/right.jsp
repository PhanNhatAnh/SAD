<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="content-right" style="float: right;">
	<div class="widget-welcomebox">
		<h2>SpaceShip Battle</h2>
		<p>Become a hero who go around the galaxy 
			to find the man who killed his family.
		  	It isn't an avenge, it is about justice .
		   	Lone way , far from home and try to safe people in
		    the galaxy far from the bad guy The Mighty Dark ...</p>
		<p>
			<a href="#">Read More...</a>
		</p>
	</div>
	<div class="widget-button">
		<h4 class="flat3d-button">Top 10 of Score</h4>
	</div>
	<div class="hotquestions widget">
		<ul class="widget-wraplist">
			<c:forEach var="rate" items="${RATE}">
				<li><img src="resources/img/icon/${rate.icon}.png"
					style="width: 18px; height: 18px;"> <font color="">${rate.name}</font>
					<font color="" style="float: right; padding-right: 5px;">${rate.score}</font></li>
			</c:forEach>
		</ul>
	</div>
</nav>
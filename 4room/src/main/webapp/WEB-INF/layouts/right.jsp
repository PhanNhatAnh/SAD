<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
/* @group right */
#right { display: block; float: left; overflow: hidden; }
#right .gcontent {
	display: block;
	margin: 10px;
}

#right .gcontent .head {
	background: #589fc6;
	border: 1px solid #3e82a7;
	padding-left: 8px;
}

#right .gcontent .head h3 {
	color: #fafcfd;
	font-weight: bold;
	font-size: 1.4em;
}

#right .gcontent .boxy {
	border-radius: 0px 0px 5px 5px;
	border: 1px solid #ccc;
	border-top: 0px;
	padding: 10px 8px;
	background: #f9f9f9;
}

#right .gcontent .boxy p{
  	font-size: 12px;
  	text-align: center;
  	color: #333333;
  	margin: 5px 0;
}

#right .gcontent .boxy .badgeCount {
	margin-bottom: 30px;
}

#right .gcontent .boxy .badgeCount a img {
	margin-right: 8px;
}

#right .gcontent .boxy span {
	font-size: 1.2em;
	display: block;
	margin-bottom: 7px;
}

#right .gcontent .boxy .friendslist {
	display: block;
	margin-bottom: 15px;
}

#right .gcontent .boxy .friend {
	border-top: 1px solid #ccc;
	float: left;
	height: 40px;
	padding: 5px 5px 5px 4px;
	width: 100px;
}

#right .gcontent .boxy .friend img {
	border: 1px solid #ccc;
	float: left;
	padding: 2px;
	background: #fff;
	margin-right: 4px;
}

#right .gcontent .boxy .friend .friendly {
	position: relative;
	top: 16px;
	font-size: 1.2em;
}
</style>
<div id="right">

	<div class="gcontent">
		<div class="boxy" style="background-color : #ccc">
			<h2 style="color: red;">SpaceShip Battle</h2>
			<p>Subcriptions</p>
			<p><a href="#">Read More...</a></p>
		</div>
	</div>

	<div class="gcontent">
		<div class="head">
			<h3>Top Ten</h3>
		</div>
		<div class="boxy">
			<div class="badgeCount">
				<c:forEach var="rate" items="${RATE}">
					<font color="blue">${rate.name}</font>  
					<font color="blue" class="pull-right">${rate.score}</font><br/>
				</c:forEach>
			</div>
		</div>
	</div>

</div>
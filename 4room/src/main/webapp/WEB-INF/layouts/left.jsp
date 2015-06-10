<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
/* @group right */
#left { display: block; width: 250px; float: left; overflow: hidden; }
#left .gcontent {
	display: block;
	margin-bottom: 10px;
}

#left .gcontent .head {
	background: #589fc6;
	border: 1px solid #3e82a7;
	padding-left: 8px;
}

#left .gcontent .head h1 {
	color: #fafcfd;
	font-weight: bold;
	font-size: 1.4em;
}

#left .gcontent .boxy {
	border-radius: 0px 0px 5px 5px;
	border: 1px solid #ccc;
	border-top: 0px;
	padding: 10px 8px;
	background: #f9f9f9;
}

#left .gcontent .boxy .badgeCount {
	margin-bottom: 30px;
}

#left .gcontent .boxy .badgeCount a img {
	margin-right: 8px;
}

#left .gcontent .boxy span {
	font-size: 1.2em;
	display: block;
	margin-bottom: 7px;
}

#left .gcontent .boxy .friendslist {
	display: block;
	margin-bottom: 15px;
}

#left .gcontent .boxy .friend {
	border-top: 1px solid #ccc;
	float: left;
	height: 40px;
	padding: 5px 5px 5px 4px;
	width: 100px;
}

#left .gcontent .boxy .friend img {
	border: 1px solid #ccc;
	float: left;
	padding: 2px;
	background: #fff;
	margin-right: 4px;
}

#left .gcontent .boxy .friend .friendly {
	position: relative;
	top: 16px;
	font-size: 1.2em;
}
</style>
<div id="left">
	<div class="gcontent">
		<div class="head">
			<h1>Favourite Tags</h1>
		</div>
		<div class="boxy">
			<p>My favorite Tags ${user.username}</p>

			<div class="badgeCount">
				<c:forEach var="favoritetags" items="${user.favoritetags}">
					<a href="#" class="tag">${favoritetags.tag.tagName}</a>
				</c:forEach>

			</div>
			<form action="DispatcherServlet" method="POST">
				<span id="tag_group"></span> <input type="text" id="fTag"
					placeholder="Add To favorite Tag" /> <input id="save"
					style="display: inline-block;" type="submit" value="OK" /> <input
					type="hidden" name="action" value="addFavoriteTag" />
			</form>
		</div>
	</div>

	<div class="gcontent">
		<div class="head">
			<h1>Subcriptions</h1>
		</div>
		<div class="boxy">
			<p>Subcriptions - ${user.subcriptions.size()} total</p>

			<div class="friendslist clearfix">
				<c:forEach var="subcriptions" items="${user.subcriptions}">
					<div class="friend">
						<span class="friendly"><a href="#">${subcriptions.question.title}</a></span>
					</div>
				</c:forEach>
				<!--                            <span><a href="#">See all...</a></span>-->
			</div>
		</div>
	</div>
</div>
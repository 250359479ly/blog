<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>星·光</title>
<link type="text/css" rel="styleSheet"  href="${APP_PATH}/static/css/index.css" />
<link rel="shortcut icon" type="image/x-icon" href="${APP_PATH}/static/img/web-icon.png" media="screen" />
<link rel="stylesheet" href="${APP_PATH}/static/css/bootstrap.min.css">
<script src="${APP_PATH}/static/js/jquery-3.2.1.min.js"></script>
<script src="${APP_PATH}/static/js/bootstrap.min.js"></script>
</head>
<body background="${APP_PATH}/static/img/bg.png">

	<div>
		<header id="header">
			<nav>
				<ul>
					<li><a href="${APP_PATH}/">首页</a> <a href="${APP_PATH}/about">关于</a>
					</li>
				</ul>
				<div class="my-info" onmouseover="hiddeewm()"
					onmouseout="hiddeewm()">
					<figure></figure>
					<span>星·光</span>
					<div id="hiddenewm" hidden="true">
						<img src="${APP_PATH}/static/img/me.jpg" width="200px"
							height="200px">
						<p></p>
					</div>
				</div>
			</nav>
		</header>
		<div id="bg">
			<p>
				和所有以梦为马的诗人一样 <br> <i>岁月易逝 一滴不剩</i>
			</p>
		</div>
	</div>
	<div id="container">
		<c:forEach items="${articles}" var="article">
			<article class="article">
				<time>${article.localTime}</time>
				<h2 class="title">
					<a href="${APP_PATH}/article?id=${article.id}">${article.title}</a>
				</h2>
				<span><i>${article.keywords}</i></span>
				<section class="article-content markdown-body">
					<blockquote>
						<p>${article.desci}</p>
					</blockquote>
					......
				</section>
				<footer>
					<a href="${APP_PATH}/article?id=${article.id}">阅读全文</a>
				</footer>
			</article>
		</c:forEach>
		<div style="text-align: center">
			<ul class="pagination">
				<li <c:if test="${pageInfo.pageNum==1}">class="disabled"</c:if>><a
					href="${APP_PATH}/?page=1">&laquo;</a></li>
				<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
					<li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
						href="${APP_PATH}/?page=${pageNo}">${pageNo}</a></li>
				</c:forEach>
				<li
					<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
					href="${APP_PATH}/?page=${pageInfo.pages}">&raquo;</a></li>
			</ul>
		</div>
	</div>
	<footer id="footer">
		<section id="copyright">
			<p style="font-size: 20px">
				© 2019 <a href="${APP_PATH}/">星·光</a>
			</p>
		</section>
	</footer>
</body>
</html>
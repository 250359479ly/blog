<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>星·光</title>
<link rel="shortcut icon" type="image/x-icon"
	href="${APP_PATH}/static/img/web-icon.png" media="screen" />
<link type="text/css" rel="styleSheet"  href="${APP_PATH}/static/css/about.css" />

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
		<div id="container">
			<article class="article">
				<p>
					<br> <br> <br> <br>
				<h3>你来人间一趟，你要看看太阳</h3>
				<br> <br>
				<h3>
					邮箱:<a href="ly250359478@163.com">ly250359478@163.com</a>
				</h3>
				<br> <br> <br> <br>
				</p>
			</article>
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
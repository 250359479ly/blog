<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>星·光</title>
<link rel="shortcut icon" type="image/x-icon"
	href="${APP_PATH}/static/img/web-icon.png" media="screen" />
<link rel="stylesheet" href="${APP_PATH}/static/css/bootstrap.min.css">
<link type="text/css" rel="styleSheet"  href="${APP_PATH}/static/css/detail.css" />
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
		<div id="container">
			<article class="article">
				<time id="time1">${article.localTime}</time>
				<h2 style="text-align: center;">${article.title}</h2>
				<p style="position: center" class="text-info">点击量:${article.click}</p>
				<section>
					<blockquote>
						<p>${article.desci}</p>
					</blockquote>
					<p id="zhengwen">${article.content}</p>
					<p
						style="text-align: center; color: #ccc; font-size: 12px; margin-top: 40px;">
						希望你今年过得比去年好一点 <br> 是因为有我
					</p>
					<p
						style="margin: 5em 0 1em; text-align: center; color: #83b8ec; font-size: .8em">
						<span>Have a nice day :)</span>
					</p>
				</section>
			</article>
		</div>
		<% int i =1;    %>
		<c:forEach items="${comments}" var="comment">


			<article class="comment">
				<section style="text-align: left">
					<%= i++  %>楼&nbsp;&nbsp;${comment.name}&nbsp;&nbsp;${comment.date}<br />
					<br />
					<p>${comment.content}</p>
					<br />
				</section>
			</article>
		</c:forEach>
		<div class="form-horizontal" role="form" style="margin: 10px">
			<div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">评论</label>
				<div class="col-sm-3">
					<textarea id="content" class="form-control" rows="3"
						placeholder="文明上网，理性发言"></textarea>
				</div>
			</div>
			<input id="articleId" type="hidden" value="${article.id}">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">昵称</label>

				<div class="col-sm-3">
					<input type="text" id="name" class="form-control" placeholder="昵称">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-3">
					<input type="email" id="email" class="form-control"
						placeholder="邮箱">
				</div>
			</div>
			<div class="form-group" style="position: relative; left: 13%">
				<br />
				<p style="text-align: right; color: red; position: absolute"
					id="info"></p>
				<br />
				<button id="commentButton" class="btn btn-default" type="submit">提交</button>
			</div>

		</div>
		
		<div style="position: relative; left: 12%">
			<c:if test="${!empty lastArticle }">
				<div>
					<a href="${APP_PATH}/article/?id=${lastArticle.id}"><h4>
							<span class="label label-primary">上一篇:${lastArticle.title}</span>
						</h4></a>
				</div>
			</c:if>
			<c:if test="${!empty nextArticle }">
				<div>
					<a href="${APP_PATH}/article/?id=${nextArticle.id}"><h4>
							<span class="label label-success">下一篇:${nextArticle.title}</span>
						</h4></a>
				</div>
			</c:if>
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
<script>
	  $("#commentButton").click(function () {
	      if($("#content").val()==''&&$("#name").val()==''&&$("#email").val()==''){
	          $("#info").text("提示:请输入评论内容,昵称和邮箱");
	      }
	      else if ($("#content").val()==''){
	          $("#info").text("提示:请输入评论内容");
	      }
	      else if($("#name").val()==''){
	          $("#info").text("提示:请输入昵称");
	      }
	      else if($("#email").val()==''){
	          $("#info").text("提示:请输入邮箱");
	      }
	      else {
	       	  $("#info").text("");
	          $.ajax({
	        	  type: "POST",
	              url: "${APP_PATH}/api/comment/add",
	              data: {
	                  content: $("#content").val() ,
	                  name: $("#name").val(),
	                  email: $("#email").val(),
	                  articleId:$("#articleId").val(),
	              },
	              dataType: "json",
	              success: function(data) {
	                  if(data.stateCode.trim() == "1") {
	                      $("#info").text("评论成功,跳转中...");
	                      window.location.reload();
	                  } else  {
	                      $("#info").text("评论失败...");
	                  }
	              }
	          });
	      }
	  })
</script>
</html>
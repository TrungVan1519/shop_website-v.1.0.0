<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/public/img/icon_oval.png" var="iconOval" />
<c:url value="/public/img/icon_facebook.png" var="iconFacebook" />
<c:url value="/public/img/icon_google.png" var="iconGoogle" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Online Shop</title>

<jsp:include page="../partials/general_css.jsp" />
<link rel="stylesheet"
	href='<c:url value="/public/css/views/login.css"/>'>

</head>
<body id="body-login">
	<div id="body-flex-login">
		<div id="container-login">

			<div id="container-login__left" class="material-white-color">
				<div class="container-login__header material-text-center">
					<span class="material-header">Welcome</span> <br> Let's create
					your fashion!
				</div>
				<div class="container-login__footer">
					<p>
						<img alt="icon_oval" src="${ iconOval }"> Luôn cập nhật xu
						hướng thời trang mới nhất
					</p>
					<p>
						<img alt="icon_oval" src="${ iconOval }"> Giảm hơn 50% đối
						với khách VIP
					</p>
					<p>
						<img alt="icon_oval" src="${ iconOval }"> Tận tình tư vấn để
						tạo nên phong cách thời trang của bạn
					</p>
				</div>
			</div>

			<div id="container-login__right">

				<div class="container-login__header material-grey-color">
					<span id="login-span"
						class="material-header-secondary material-active pointer-cursor">Login</span>
					/ <span id="signup-span"
						class="material-header-secondary pointer-cursor">Register</span> /
					<span> <a class="badge"
						href="${ pageContext.request.contextPath }/">Back to home</a>
					</span>
				</div>

				<div class="container-login__content text-center">
					
					<c:choose>
						<c:when test="${ accountCreated == true }">
							<p class="text-success font-weight-bold">Create account successfully.</p>
						</c:when>
						<c:when test="${ accountCreated == false }">
							<p class="text-warning font-weight-bold">Create account fail.</p>
						</c:when>
					</c:choose>
					
					<c:if test="${ signupMessage != null }">
						<p class="text-danger font-weight-bold">${ signupMessage }</p>
					</c:if>

					<!-- Login form -->
					<form id="login-form">
						<div class="material-form-group">
							<input class="material-icon-email" placeholder="Email"
								type="email" name="email" value="${ sessionEmail }" />
						</div>
						<div class="material-form-group">
							<input class="material-icon-password" placeholder="Password"
								type="password" name="password" />
						</div>
						<div class="material-form-group">
							<button class="material-btn material-btn-primary" type="button">
								<span class="material-header-secondary">Login</span>
							</button>
						</div>
					</form>
					<!-- Login form -->

					<!-- Sign up form -->
					<form id="signup-form" action="${ pageContext.request.contextPath }/auth/signup" method="POST">
						<div class="material-form-group">
							<input class="material-icon-email" placeholder="Email"
								type="email" name="email" />
						</div>
						<div class="material-form-group">
							<input class="material-icon-password" placeholder="Password"
								type="password" name="password" />
						</div>
						<div class="material-form-group">
							<input class="material-icon-password"
								placeholder="Retype Password" type="password" name="re_password" />
						</div>
						<div class="material-form-group">
							<button class="material-btn material-btn-primary" type="submit">
								<span class="material-header-secondary">Sign up</span>
							</button>
						</div>
					</form>
					<!-- Sign up form -->

				</div>

				<div class="container-login__footer">
					<div class="container-login__social">
						<img alt="icon_oval" src="${ iconFacebook }"> <img
							alt="icon_oval" src="${ iconGoogle }">
					</div>
				</div>

			</div>

		</div>
	</div>

	<jsp:include page="../partials/general_js.jsp" />

</body>
</html>

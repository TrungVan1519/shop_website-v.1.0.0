<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Online Shop</title>

<jsp:include page="partials/general_css.jsp" />
<link rel="stylesheet"
	href='<c:url value="/public/css/views/home.css"/>'>

</head>
<body>
	<div class="container-fluid p-0 m-0">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

			<a class="navbar-brand" href="#"> <img class="app-icon"
				alt="app-icon" src="<c:url value="/public/img/icon_app.png" />">
				gBean Shop
			</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">

				<!-- Right items -->
				<div class="navbar-nav mr-auto">
					<a class="nav-item nav-link active" href="<c:url value="/" />">Home</a>
					<a class="nav-item nav-link active" href="<c:url value="/dashboard" />">Dashboard</a>
					<div class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Category</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<c:forEach var="category" items="${ categories }">
								<a class="dropdown-item"
									href='<c:url value="/category/${ category.id }"/>'>${ category.name }</a>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- Right items -->

				<!-- Left items -->
				<div class="navbar-nav ml-auto">
					<c:choose>
						<c:when test="${ sessionEmail == null }">
							<a class="nav-item nav-link"
								href="${ pageContext.request.contextPath }/auth/login">Login</a>
							<a class="nav-item nav-link"
								href="${ pageContext.request.contextPath }/auth/singup">Sign
								up</a>
						</c:when>
						<c:otherwise>
							<a class="nav-item nav-link" href="#">${ sessionEmail }</a>
						</c:otherwise>
					</c:choose>
					<a class="nav-item nav-link" href='<c:url value="/shopping-cart"/>'><i
						class="fas fa-shopping-cart"></i> <span id="shopping-cart-size">${ shoppingCartSize }</span>
					</a>
				</div>
				<!-- Left items -->

			</div>

		</nav>
	</div>

	<main class="container mt-5 text-center">
		<div class="row">
			<div class="col-sm-2 text-left">
				<h3>Category</h3>
				<ul class="aside">
					<c:forEach var="category" items="${ categories }">
						<li><a href="#">${ category.name }</a></li>
					</c:forEach>
				</ul>
			</div>

			<!-- Product list -->
			<div class="col-sm-10">
				<h3>${ chosenCategory.name }</h3>
				<div class="row">
					<c:forEach var="product" items="${ products }">
						<div class="col-md-3 col-sm-6 mt-3">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value="/public/img/product-upload/${ product.img }" />"
									alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title">${ product.name }</h4>
									<p class="card-text">${ product.cost }$</p>
									<p class="card-text">${ product.description }</p>
									<a class="btn btn-primary"
										href="<c:url value="/details/product/${ product.id }" />">Chi
										tiết</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- Product list -->

		</div>
	</main>

	<footer class="container-fluid text-center bg-dark text-white p-5">
		<div class="row">
			<div class="col-sm-4">
				<h2>Infomation</h2>
				<p>Infomation about shop</p>
			</div>
			<div class="col-sm-4">
				<h2>Contact</h2>
				<p>Email of shop</p>
			</div>
			<div class="col-sm-4">
				<h2>Feedback</h2>
				<form>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="email">
					</div>
					<div class="form-group">
						<textarea class="form-control" rows="3" placeholder="feedbacks"></textarea>
					</div>
					<div class="form-group">
						<button class="btn btn-primary" type="submit">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</footer>

	<jsp:include page="partials/general_js.jsp" />
</body>
</html>

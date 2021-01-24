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

			<div class="col-sm-8">
				<div class="row">
					<div class="col-sm-4">
						<img class="card-img-top"
							src="<c:url value="/public/img/product-upload/${ product.img }" />"
							alt="Card image cap">
					</div>
					<div class="col-sm-8">
						<h3 id="product" data-id="${ product.id }">${ product.name }</h3>
						<h3 id="cost" data-cost="${ product.cost }">${ product.cost }$</h3>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Quantity</th>
									<th scope="col">Size</th>
									<th scope="col">Color</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${ product.productDetails.size() > 0 }">
										<c:forEach var="productDetail"
											items="${ product.productDetails }">
											<tr>
												<th scope="row">${ productDetail.id }</th>
												<td class="qty">${ productDetail.qty }</td>
												<td class="size"
													data-size="${ productDetail.productSize.id }">${ productDetail.productSize.size }</td>
												<td class="color"
													data-color="${ productDetail.productColor.id }">${ productDetail.productColor.color }</td>
												<td><button class="btn btn-primary btn-shopping-cart">Add
														to shopping cart</button></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="4"><h3>There is no product.</h3></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-2">${ product.description }</div>
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

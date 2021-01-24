<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Online Shop| Admin</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 







</script>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href='<c:url value="/public/vendors/admin-template/css/bootstrap.min.css"/>'>

<!-- Custom CSS -->
<link rel="stylesheet"
	href='<c:url value="/public/vendors/admin-template/css/style.css"/>'>
<link rel="stylesheet"
	href='<c:url value="/public/vendors/admin-template/css/morris.css"/>'>

<!-- Graph CSS -->
<link rel="stylesheet"
	href='<c:url value="/public/vendors/admin-template/css/font-awesome.css"/>'>

<!-- jQuery -->
<script
	src="<c:url value="/public/vendors/admin-template/js/jquery-2.1.4.min.js"/>"></script>
<!-- //jQuery -->

<link
	href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400'
	rel='stylesheet' type='text/css' />
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>

<!-- lined-icons -->
<link rel="stylesheet"
	href="<c:url value="/public/vendors/admin-template/css/icon-font.min.css"/>"
	type='text/css' />
<!-- //lined-icons -->

</head>
<body>
	<div class="page-container">
		<!--/content-inner-->
		<div class="left-content container"
			style="background: white; height: 100vh">
			<div class="row">
				<div class="col-sm-12 col-md-6">
					<form id="productForm">
						<h1 class="text-primary">Products</h1>
						<div class="form-group">
							<label for="productName">Product name</label> <input
								class="form-control" type="text" name="productName"
								id="productName" placeholder="Enter product name">
						</div>
						<div class="form-group">
							<label for="productCost">Product cost</label> <input
								class="form-control" type="number" name="productCost"
								id="productCost" placeholder="Enter product cost">
						</div>
						<div class="form-group">
							<label for="productDescription">Product description</label>
							<textarea class="form-control" id="productDescription"
								name="productDescription" rows="3"></textarea>
						</div>
						<div class="form-group">
							<label for="productImg">Product image</label> <input type="file"
								name="productImg" class="form-control-file" id="productImg">
						</div>
						<div class="form-group">
							<label for="category">Category</label> <select
								class="form-control" id="category" name="categoryId">
								<c:forEach var="category" items="${ categories }">
									<option value="${ category.id }">${ category.name }</option>
								</c:forEach>
							</select>
						</div>
					</form>

					<hr>

					<h1 class="text-primary">Product Details</h1>
					<div id="clonedProductDetails"></div>

					<div id="originalProductDetails" class="productDetails">
						
						<input type="hidden" class="product-detail-id" name="productDetailId">
					
						<div class="form-group">
							<label for="productSize">Product size</label> <select
								class="form-control" id="productSize" name="productSizeId">
								<c:forEach var="productSize" items="${ productSizes }">
									<option value="${ productSize.id }">${ productSize.size }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="productColor">Product color</label> <select
								class="form-control" id="productColor" name="productColorId">
								<c:forEach var="productColor" items="${ productColors }">
									<option value="${ productColor.id }">${ productColor.color }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="productQty">Product quantity</label> <input
								class="form-control" type="number" name="productQty"
								id="productQty" placeholder="Enter product quantity" min="1"
								value="1">
						</div>

						<button type="button"
							class="btn btn-success btn-sm btn-new-product-detail"
							style="margin-right: 0; margin-left: auto; display: block">
							New product detail <i class="fa fa-plus-circle"
								aria-hidden="true"></i>
						</button>
					</div>

					<div class="form-group">
						<button id="add-product" type="button" class="btn btn-primary">Add
							product</button>
						<button id="update-product" type="button" class="btn btn-primary" style="display: none;">Update
							product</button>
						<button id="cancel" type="button" class="btn btn-primary" style="display: none;">Cancel</button>
					</div>
				</div>

				<div class="col-sm-12 col-md-6">
					<div class="float-right">
						<button id="remove-product" type="button"
							class="btn btn-secondary">Remove product</button>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input"
											id="checkbox-all"> <label
											class="custom-control-label" for="checkbox-all">All</label>
									</div>
								</th>
								<th>#</th>
								<th>Name</th>
								<th>Cost</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="${ products }">
								<tr>
									<td>
										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												value="${ product.id }">
										</div>
									</td>
									<th scope="row" data-product-id="${ product.id }">${ product.id }</th>
									<td>${ product.name }</td>
									<td>${ product.cost }$</td>
									<td>
										<button type="button"
											class="btn btn-warning btn-update-product">Update</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="text-center">
						<nav aria-label="Page navigation">
							<ul class="pagination pagination-lg">
								<li class="page-item"><a class="page-link" href="#!"
									aria-label="Previous"> <span aria-hidden="true">«</span> <span
										class="sr-only">Previous</span>
								</a></li>

								<c:forEach var="i" begin="1" end="${ numberOfPages }" step="1">
									<c:choose>
										<c:when test="${ i == 1 }">
											<li class="page-item active"><a class="page-link"
												href="#!">${ i }</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="#!">${ i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<li class="page-item"><a class="page-link" href="#!"
									aria-label="Next"> <span aria-hidden="true">»</span> <span
										class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!--//content-inner-->
		<!--/sidebar-menu-->
		<div class="sidebar-menu">
			<header class="logo1">
				<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span>
				</a>
			</header>
			<div style="border-top: 1px ridge rgba(255, 255, 255, 0.15)"></div>
			<div class="menu">
				<ul id="menu">
					<li><a href='<c:url value="/dashboard"/>'><i
							class="fa fa-tachometer"></i> <span>Dashboard</span>
							<div class="clearfix"></div></a></li>

					<li id="menu-academico"><a
						href="<c:url value="/dashboard/product"/>"><i
							class="fa fa-cubes nav_icon"></i><span>Product</span>
							<div class="clearfix"></div></a></li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<script>
		var toggle = true;

		$(".sidebar-icon").click(
				function() {
					if (toggle) {
						$(".page-container").addClass("sidebar-collapsed")
								.removeClass("sidebar-collapsed-back");
						$("#menu span").css({
							"position" : "absolute"
						});
					} else {
						$(".page-container").removeClass("sidebar-collapsed")
								.addClass("sidebar-collapsed-back");
						setTimeout(function() {
							$("#menu span").css({
								"position" : "relative"
							});
						}, 400);
					}

					toggle = !toggle;
				});
	</script>
	<!--js -->
	<script
		src="<c:url value="/public/vendors/admin-template/js/jquery.nicescroll.js"/>"></script>
	<script
		src="<c:url value="/public/vendors/admin-template/js/scripts.js"/>"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<c:url value="/public/vendors/admin-template/js/bootstrap.min.js"/>"></script>
	<!-- /Bootstrap Core JavaScript -->

	<!-- morris JavaScript -->
	<script
		src="<c:url value="/public/vendors/admin-template/js/raphael-min.js"/>"></script>
	<script
		src="<c:url value="/public/vendors/admin-template/js/morris.js"/>"></script>
	<script>
		$(document).ready(
				function() {
					//BOX BUTTON SHOW AND CLOSE
					jQuery('.small-graph-box').hover(function() {
						jQuery(this).find('.box-button').fadeIn('fast');
					}, function() {
						jQuery(this).find('.box-button').fadeOut('fast');
					});
					jQuery('.small-graph-box .box-close').click(function() {
						jQuery(this).closest('.small-graph-box').fadeOut(200);
						return false;
					});

					//CHARTS
					function gd(year, day, month) {
						return new Date(year, month - 1, day).getTime();
					}

					graphArea2 = Morris.Area({
						element : 'hero-area',
						padding : 10,
						behaveLikeLine : true,
						gridEnabled : false,
						gridLineColor : '#dddddd',
						axes : true,
						resize : true,
						smooth : true,
						pointSize : 0,
						lineWidth : 0,
						fillOpacity : 0.85,
						data : [ {
							period : '2014 Q1',
							iphone : 2668,
							ipad : null,
							itouch : 2649
						}, {
							period : '2014 Q2',
							iphone : 15780,
							ipad : 13799,
							itouch : 12051
						}, {
							period : '2014 Q3',
							iphone : 12920,
							ipad : 10975,
							itouch : 9910
						}, {
							period : '2014 Q4',
							iphone : 8770,
							ipad : 6600,
							itouch : 6695
						}, {
							period : '2015 Q1',
							iphone : 10820,
							ipad : 10924,
							itouch : 12300
						}, {
							period : '2015 Q2',
							iphone : 9680,
							ipad : 9010,
							itouch : 7891
						}, {
							period : '2015 Q3',
							iphone : 4830,
							ipad : 3805,
							itouch : 1598
						}, {
							period : '2015 Q4',
							iphone : 15083,
							ipad : 8977,
							itouch : 5185
						}, {
							period : '2016 Q1',
							iphone : 10697,
							ipad : 4470,
							itouch : 2038
						}, {
							period : '2016 Q2',
							iphone : 8442,
							ipad : 5723,
							itouch : 1801
						} ],
						lineColors : [ '#ff4a43', '#a2d200', '#22beef' ],
						xkey : 'period',
						redraw : true,
						ykeys : [ 'iphone', 'ipad', 'itouch' ],
						labels : [ 'All Visitors', 'Returning Visitors',
								'Unique Visitors' ],
						pointSize : 2,
						hideHover : 'auto',
						resize : true
					});

				});
	</script>
	<!-- Third-party libraries -->
	<script src='<c:url value="/public/vendors/wow.min.js"/>'></script>
	<script src='<c:url value="/public/vendors/jquery-3.5.1.min.js"/>'></script>
	<script src='<c:url value="/public/vendors/isotope.pkgd.min.js"/>'></script>
	<script src='<c:url value="/public/vendors/gsap.min.js"/>'></script>
	<script
		src='<c:url value="/public/vendors/fancybox/dist/jquery.fancybox.min.js"/>'></script>

	<!-- App js -->
	<script src='<c:url value="/public/js/app.js"/>'></script>
</body>
</html>
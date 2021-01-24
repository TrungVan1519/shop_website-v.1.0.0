$(document).ready(function() {
	new WOW().init();

	$("#login-form button[type='button']").click(function(e) {
		let email = $("input[type='email']").val();
		let password = $("input[type='password']").val();

		$.ajax({
			url: "/online-shop/ajax-api/login",
			method: "POST",
			data: {
				email,
				password,
			},
			success: function(path) {
				window.location.href =
					window.location.href.replace("/auth/login", path);
			}
		});
	});

	$("#signup-form").hide();
	$("#login-span").click(function(e) {
		$(this).addClass("material-active");
		$("#signup-span").removeClass("material-active");
		$("#login-form").show();
		$("#signup-form").hide();
	});

	$("#signup-span").click(function(e) {
		$(this).addClass("material-active");
		$("#login-span").removeClass("material-active");
		$("#login-form").hide();
		$("#signup-form").show();
	});


	$(".btn-shopping-cart").click(function(e) {
		let tableRow = $(this).closest("tr");

		let productDetailId = tableRow.children("th").text();
		let productId = $("#product").data("id");
		let sizeId = tableRow.children(".size").data("size");
		let colorId = tableRow.children(".color").data("color");
		let qty = tableRow.children(".qty").text();
		let cost = $("#cost").data("cost");

		let productName = $("#product").text();
		let size = tableRow.children(".size").text();
		let color = tableRow.children(".color").text();

		$.ajax({
			url: "/online-shop/ajax-api/add-shopping-cart",
			method: "GET",
			data: {
				productDetailId,
				productId,
				sizeId,
				colorId,
				qty,
				cost,
				productName,
				size,
				color,
			},
			success: function(shoppingCartSize) {
				$("#shopping-cart-size").text(shoppingCartSize);
			},
		});
	});

	calSumAll();
	$(".itemCart-qty").change(function() {
		// update front-end
		let tableRow = $(this).closest("tr");
		let productId = tableRow.children(".product").data("id");
		let sizeId = tableRow.children(".size").data("size");
		let colorId = tableRow.children(".color").data("color");
		let qty = $(this).val();
		let cost = $(this).closest("tr").children(".cost").data("cost");

		$(this).closest("tr").children(".cost").text(`${qty * cost}$`);

		let sumAll = 0;
		$(".cost").each(function() {
			sumAll += +$(this).text().replace("$", "");
		});
		$("#sumAll").text(`${sumAll}$`);

		// update shopping cart java session back-end
		$.ajax({
			url: "/online-shop/ajax-api/update-shopping-cart",
			method: "GET",
			data: {
				productId,
				sizeId,
				colorId,
				qty,
			},
		});
	});

	$(".btn-remove-shopping-cart").click(function(e) {
		let tableRow = $(this).closest("tr");
		let productId = tableRow.children(".product").data("id");
		let sizeId = tableRow.children(".size").data("size");
		let colorId = tableRow.children(".color").data("color");

		$.ajax({
			url: "/online-shop/ajax-api/remove-shopping-cart",
			method: "GET",
			data: {
				productId,
				sizeId,
				colorId,
			},
			success: function() {
				tableRow.remove();
				calSumAll();
			},
		});
	});

	$("body").on("click", ".page-item", function() {
		let pageNumber = $(this).text();
		let self = $(this);

		$.ajax({
			url: "/online-shop/ajax-api/paging",
			method: "GET",
			data: {
				pageNumber,
			},
			success: function(html) {
				$("tbody").html(html);
				$(".page-item").removeClass("active");
				self.addClass("active");
			},
		});
	});

	$("#checkbox-all").change(function() {
		if (this.checked) {
			$("input").each(function() {
				$(this).attr("checked", true);
			});
		} else {
			$("input").each(function() {
				$(this).attr("checked", false);
			});
		}
	});

	$("#remove-product").click(function() {
		$("tbody input:checked").each(function() {
			let self = $(this);
			let productId = $(this).val();

			$.ajax({
				url: "/online-shop/ajax-api/remove-product",
				method: "GET",
				data: {
					productId,
				},
				success: function() {
					self.closest("tr").remove();
				},
			});
		});
	});

	$("body").on("click", ".btn-new-product-detail", function() {
		//$(this).remove();
		let content = $("#originalProductDetails").clone().removeAttr("id");
		$("#clonedProductDetails").append(content);
	});

	let productImg = "";
	$("#productImg").change(function(e) {
		let files = e.target.files;
		productImg = files[0].name;

		let forms = new FormData();
		forms.append("file", files[0]);

		$.ajax({
			url: "/online-shop/ajax-api/upload-product-img",
			method: "POST",
			data: forms,
			enctype: "multipart/form-data",
			contentType: false,
			processData: false,
		});
	});

	$("#add-product").click(function() {

		let formData = $("#productForm").serializeArray();

		let json = {};
		json.productImg = productImg;
		$.each(formData, function(i, field) {
			json[field.name] = field.value;
		});

		json.productDetails = [];
		$(".productDetails").each(function() {
			let tmp = {};
			tmp.productSizeId = $(this).find("[name='productSizeId']").val();
			tmp.productColorId = $(this).find("[name='productColorId']").val();
			tmp.productQty = $(this).find("[name='productQty']").val();

			json.productDetails.push(tmp);
		});

		$.ajax({
			url: "/online-shop/ajax-api/add-product",
			method: "POST",
			data: {
				json: JSON.stringify(json),
			},
		});
	});

	// Have to change to .btn-prefill instead of .btn-update-product
	let productId = 0;
	$("body").on("click", ".btn-update-product", function() {
		$("#add-product").css("display", "none");
		$("#update-product").css("display", "inline");
		$("#cancel").css("display", "inline");

		productId = $(this).closest("tr").children("th[scope='row']").data("product-id");

		$.ajax({
			url: "/online-shop/ajax-api/get-products-by-id",
			method: "POST",
			data: {
				productId,
			},
			success: function(json) {
				$("#productName").val(json.name);
				$("#productCost").val(json.cost);
				$("#productDescription").text(json.description);
				$("#category").val(json.category.id);

				$("#clonedProductDetails").empty();
				if (json.productDetails.length > 0) {

					$("#originalProductDetails").hide();
					json.productDetails.forEach(productDetail => {
						let content = $("#originalProductDetails").clone().removeAttr("id");
						content.show();

						content.find(".product-detail-id").val(productDetail.id);
						content.find("#productSize").val(productDetail.productSize.id);
						content.find("#productColor").val(productDetail.productColor.id);
						content.find("#productQty").val(productDetail.qty);

						if (json.productDetails.indexOf(productDetail) !== (json.productDetails.length - 1)) {
							content.find(".btn-new-product-detail").remove();
						}

						$("#clonedProductDetails").append(content);
					});
				} else {
					$("#originalProductDetails").show();
				}
			}
		});
	});

	$("#update-product").click(function() {

		let formData = $("#productForm").serializeArray();

		let json = {};
		json.productImg = productImg;
		$.each(formData, function(i, field) {
			json[field.name] = field.value;
		});

		json.productDetails = [];
		$("#clonedProductDetails .productDetails").each(function() {
			let tmp = {};
			tmp.productDetailId = $(this).find("[name='productDetailId']").val();
			tmp.productSizeId = $(this).find("[name='productSizeId']").val();
			tmp.productColorId = $(this).find("[name='productColorId']").val();
			tmp.productQty = $(this).find("[name='productQty']").val();

			json.productDetails.push(tmp);
		});

		if (productId != 0) {
			json.productId = productId;
		}

		$.ajax({
			url: "/online-shop/ajax-api/update-product",
			method: "POST",
			data: {
				json: JSON.stringify(json),
			},
		});

		// reset productId for next req
		productId = 0;
	});
});

function calSumAll() {
	let sumAll = 0;
	$(".cost").each(function() {
		let cost = $(this).data("cost");
		let qty = $(this).closest("tr").find(".itemCart-qty").val();

		$(this).text(`${qty * cost}$`);

		sumAll += (cost * qty);
	});
	$("#sumAll").text(`${sumAll}$`);
}

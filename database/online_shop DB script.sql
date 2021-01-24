DROP DATABASE IF EXISTS `online_shop`; -- online shop DB v.1.0.0
CREATE DATABASE IF NOT EXISTS `online_shop` CHARACTER SET utf8 collate utf8_general_ci;
USE `online_shop`;

--
-- Table structure for table `role`
--
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(20) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `employee`
--
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(30) NOT NULL DEFAULT 'unknown',
  `address` nvarchar(200) DEFAULT 'unknown',
  `gender` bit DEFAULT 1,
  `cmnd` char(14) DEFAULT 'unknown',
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `id_role` int(11),
  PRIMARY KEY (`id`),
  KEY `K_employee_role` (`id_role`),
  CONSTRAINT `FK_employee_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `category`
--
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(100) NOT NULL DEFAULT 'unknown',
  `img` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Table structure for table `product`
--
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(100) NOT NULL DEFAULT 'unknown',
  `cost` varchar(50) NOT NULL DEFAULT 'unknown',
  `description` text,
  `img` text,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `K_product_category` (`id_category`),
  CONSTRAINT `FK_product_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Table structure for table `product_color`
--
DROP TABLE IF EXISTS `product_color`;
CREATE TABLE `product_color` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` nvarchar(50) NOT NULL DEFAULT 'unknown',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Table structure for table `product_size`
--
DROP TABLE IF EXISTS `product_size`;
CREATE TABLE `product_size` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `size` nvarchar(20) NOT NULL DEFAULT 'unknown',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `bill`
--
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` nvarchar(100) NOT NULL,
  `phone_number` char(12) DEFAULT 'unknown',
  `ship_address` nvarchar(200) DEFAULT 'unknown',
  `status` bit DEFAULT 0,
  `createdAt` varchar(50) DEFAULT 'unknown',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `sale`
--
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(200) NOT NULL,
  `startAt` varchar(50) DEFAULT 'unknown',
  `endAt` varchar(50) DEFAULT 'unknown',
  `description` text,
  `img` text,
  `cost` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ---------------------------------------------- Association ---------------------------------------------
--
-- Table structure for table `product_detail`
--
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qty` int(11) DEFAULT NULL,
  `createdAt` varchar(50) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL,
  `id_product_size` int(11) DEFAULT NULL,
  `id_product_color` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_productDetail_product` (`id_product`),
  KEY `K_productDetail_productSize` (`id_product_size`),
  KEY `K_productDetail_productColor` (`id_product_color`),
  CONSTRAINT `FK_productDetail_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_productDetail_productSize` FOREIGN KEY (`id_product_size`) REFERENCES `product_size` (`id`),
  CONSTRAINT `FK_productDetail_productColor` FOREIGN KEY (`id_product_color`) REFERENCES `product_color` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

--
-- Table structure for table `bill_detail`
--
DROP TABLE IF EXISTS `bill_detail`;
CREATE TABLE `bill_detail` (
  `id_bill` int(11) NOT NULL,
  `id_product_detail` int(11) NOT NULL,
  `qty` int(11) DEFAULT 0,
  `cost` varchar(100) DEFAULT 'unknown',
  PRIMARY KEY (`id_bill`,`id_product_detail`),
  KEY `K_billDetail_productDetail` (`id_product_detail`),
  CONSTRAINT `FK_billDetail_bill` FOREIGN KEY (`id_bill`) REFERENCES `bill` (`id`),
  CONSTRAINT `FK_billDetail_productDetail` FOREIGN KEY (`id_product_detail`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `sale_detail`
--
DROP TABLE IF EXISTS `sale_detail`;
CREATE TABLE `sale_detail` (
  `id_product` int(11) NOT NULL,
  `id_sale` int(11) NOT NULL,
  PRIMARY KEY (`id_product`, `id_sale`),
  KEY `K_saleDetail_product` (`id_product`),
  CONSTRAINT `FK_saleDetail_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_saleDetail_sale` FOREIGN KEY (`id_sale`) REFERENCES `sale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ---------------------- Dumping data
INSERT INTO `online_shop`.`role` (`name`) VALUES ('admin');
INSERT INTO `online_shop`.`role` (`name`) VALUES ('employee');
INSERT INTO `online_shop`.`role` (`name`) VALUES ('user');

INSERT INTO `online_shop`.`employee` (`name`, `gender`, `cmnd`, `email`, `password`, `id_role`) VALUES ('dauXanh', b'1', '123', 'dauXanh@gmail.com', '123', '4');
INSERT INTO `online_shop`.`employee` (`name`, `gender`, `cmnd`, `email`, `password`, `id_role`) VALUES ('rauMuong', b'0', '123', 'rauMuong@gmail.com', '123', '5');
INSERT INTO `online_shop`.`employee` (`name`, `gender`, `cmnd`, `email`, `password`, `id_role`) VALUES ('khoaiSan', b'0', '123', 'khoaiSan@gmail.com', '123', '6');

INSERT INTO `online_shop`.`category` (`name`) VALUES ('Áo');
INSERT INTO `online_shop`.`category` (`name`) VALUES ('Quần');
INSERT INTO `online_shop`.`category` (`name`) VALUES ('Găng tay');
INSERT INTO `online_shop`.`category` (`name`) VALUES ('Mũ');
INSERT INTO `online_shop`.`category` (`name`) VALUES ('Kính');
INSERT INTO `online_shop`.`category` (`name`) VALUES ('Giầy');
INSERT INTO `online_shop`.`category` (`name`) VALUES ('Khăn quàng');

INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Quần jeans', '10', 'Quần rất tốt', '11');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Áo khoác', '20', 'Áo rất bền', '10');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Áo ba lỗ', '10', 'Áo rất mát', '10');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Quần tất', '30', 'Quần rất đẹp', '11');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Mũ cối', '50', 'Mũ rất bền', '13');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Giầy adidas', '30', 'Giầy rất đẹp', '15');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Áo bà ba', '40', 'Phù hợp với tất cả mọi người', '10');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Quần short', '20', 'Phù hợp vào mùa hè', '11');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Váy ngắn', '20', 'Phù hợp vào mùa hè', '11');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Giầy thường đình', '30', 'Hàng VN chất lượng cao', '15');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Áo giáp chống đạn', '20', 'Đồ xịn', '10');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Áo giáp ironman', '100', '100% làm từ Fe', '10');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Áo vest', '50', 'Dành cho quý ông lịch lãm', '10');
INSERT INTO `online_shop`.`product` (`name`, `cost`, `description`, `id_category`) VALUES ('Quần bò', '20', 'Dành cho mọi lứa tuổi', '11');

INSERT INTO `online_shop`.`product_color` VALUES (1,'Xanh Dương'),(2,'Xám'),(3,'Trắng'),(4,'Hồng'),(5,'Xanh Dương'),(6,'Xanh Lá'),(7,'Xanh Ngọc');

INSERT INTO `online_shop`.`product_size` VALUES (1,'M'),(2,'L'),(3,'XL'),(4,'XXL');

INSERT INTO `online_shop`.`product_detail` (`qty`, `createdAt`, `id_product`, `id_product_size`, `id_product_color`) VALUES ('4', '20/12/2020', '22', '2', '3');
INSERT INTO `online_shop`.`product_detail` (`qty`, `createdAt`, `id_product`, `id_product_size`, `id_product_color`) VALUES ('2', '1/12/2020', '23', '3', '4');
INSERT INTO `online_shop`.`product_detail` (`qty`, `createdAt`, `id_product`, `id_product_size`, `id_product_color`) VALUES ('1', '8/1/2021', '24', '4', '3');
INSERT INTO `online_shop`.`product_detail` (`qty`, `createdAt`, `id_product`, `id_product_size`, `id_product_color`) VALUES ('3', '9/1/2021', '22', '2', '3');

create database Car_rent;
use Car_rent;

CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

CREATE TABLE `model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` int(11) DEFAULT NULL,
  `model` varchar(255) NOT NULL,
  `about` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_brand` (`brand`,`model`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`brand`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `admin` (
  `login` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `color` varchar(45) DEFAULT NULL,
  `engine` float DEFAULT NULL,
  `expenditure` float DEFAULT NULL,
  `automat` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `model` (`model`),
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`model`) REFERENCES `model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(45) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `details` text NULL,
  `delivery_id_to` int(11) DEFAULT NULL,
  `delivery_id_from` int(11) DEFAULT NULL,
  `child_chair` bit(1) NOT NULL,
  `gps` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_delivery_id_to` (`delivery_id_to`),
  UNIQUE KEY `unique_delivery_id_from` (`delivery_id_from`),
  KEY `admin_id` (`admin_id`),
  KEY `client_id` (`client_id`),
  KEY `car_id` (`car_id`),
  CONSTRAINT `_order_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`login`),
  CONSTRAINT `_order_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `_order_ibfk_3` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `_order_ibfk_4` FOREIGN KEY (`delivery_id_to`) REFERENCES `delivery` (`id`),
  CONSTRAINT `_order_ibfk_5` FOREIGN KEY (`delivery_id_from`) REFERENCES `delivery` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `repair_executor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `defect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `description` text NOT NULL,
  `client_price` float NOT NULL,
  `occurrence_date` date NOT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client` (`client_id`),
  KEY `car_id` (`car_id`),
  CONSTRAINT `defect_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `defect_ibfk_2` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `car_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  `price0` int(11) NOT NULL,
  `price1` int(11) NOT NULL,
  `price2` int(11) NOT NULL,
  `price3` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_car_id` (`car_id`),
  CONSTRAINT `car_price_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `car_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `option_id` (`option_id`),
  CONSTRAINT `car_options_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `car_options_ibfk_2` FOREIGN KEY (`option_id`) REFERENCES `_options` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `car_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `car_class_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `car_class_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `repaired_defect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `defect_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `date` date NOT NULL,
  `executor` int(11) NOT NULL,
  `repaired` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `executor` (`executor`),
  KEY `defect_id` (`defect_id`),
  CONSTRAINT `repaired_defect_ibfk_2` FOREIGN KEY (`executor`) REFERENCES `repair_executor` (`id`),
  CONSTRAINT `repaired_defect_ibfk_3` FOREIGN KEY (`defect_id`) REFERENCES `defect` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Insert into admin (login, password) Values ('admin', '$2a$10$Y4IS6By475TFMPlBChDDMuryfMfTRzNppRmnOvAls84jm9Z1oEEnG');
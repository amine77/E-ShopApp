-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 14 jan. 2019 à 01:45
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `learn_language`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(3000) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `description`, `name`) VALUES
(1, 'ceci est une description de la catégorie appartement', 'appartement'),
(2, 'ceci est une description de la catégorie maison', 'maison'),
(3, 'ceci est une description de la catégorie garage', 'garage'),
(4, 'ceci est une description de la catégorie terrain', 'terrain'),
(5, 'ceci est une description de la catégorie parking', 'parking'),
(6, 'ceci est une description de la catégorie bureaux et commerce', 'bureaux et commerce');

-- --------------------------------------------------------

--
-- Structure de la table `country`
--

DROP TABLE IF EXISTS `country`;
CREATE TABLE IF NOT EXISTS `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `iso3` varchar(3) NOT NULL,
  `iso_code` varchar(2) NOT NULL,
  `name` varchar(80) NOT NULL,
  `numcode` varchar(3) NOT NULL,
  `printable_name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(3000) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `unit_cost` float NOT NULL,
  `product_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfw0lwnp7diao8f6l0r6sv6dmm` (`product_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
CREATE TABLE IF NOT EXISTS `order_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `item_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdpvwuqglm5jbtdif1hqukuciu` (`item_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(3000) NOT NULL,
  `name` varchar(30) NOT NULL,
  `category_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK275nu1ncohhfur6qhxiwrm3go` (`category_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE IF NOT EXISTS `purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credit_card_expiry_date` varchar(5) NOT NULL,
  `credit_card_number` varchar(30) NOT NULL,
  `credit_card_type` int(11) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street1` varchar(50) NOT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `zip_code` varchar(10) NOT NULL,
  `discount` float DEFAULT NULL,
  `discount_rate` float DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `total` float DEFAULT NULL,
  `total_with_vat` float DEFAULT NULL,
  `total_without_vat` float DEFAULT NULL,
  `vat` float DEFAULT NULL,
  `vat_rate` float DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `user_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6yey93knikxpay98skxj9h84d` (`country_id`),
  KEY `FKbkmuajpl9rd0d2lsg0jipt944` (`user_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `term`
--

DROP TABLE IF EXISTS `term`;
CREATE TABLE IF NOT EXISTS `term` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `de` varchar(255) DEFAULT NULL,
  `en` varchar(255) DEFAULT NULL,
  `fr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `t_order_order_line`
--

DROP TABLE IF EXISTS `t_order_order_line`;
CREATE TABLE IF NOT EXISTS `t_order_order_line` (
  `order_fk` bigint(20) NOT NULL,
  `order_line_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`order_fk`,`order_line_fk`),
  UNIQUE KEY `UK_jo44h7yfc34r15gudii8hsq9q` (`order_line_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street1` varchar(50) NOT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `zip_code` varchar(10) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(10) NOT NULL,
  `uuid` varchar(256) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK5t4yyo3f0ctxayh7voqv51fmg` (`country_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

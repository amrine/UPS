-- phpMyAdmin SQL Dump
-- version 3.3.2
-- http://www.phpmyadmin.net
--
-- Serveur: 127.0.0.1
-- Généré le : Lun 04 Avril 2011 à 11:00
-- Version du serveur: 5.1.45
-- Version de PHP: 5.3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `tp2`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `cc` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `prenom` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `daten` date DEFAULT NULL,
  `pays` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`cc`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`cc`, `nom`, `prenom`, `daten`, `pays`) VALUES
(1, 'Aztakes', 'Helene', '1990-10-01', 'France'),
(2, 'Zac', '', '1990-10-01', 'France');

-- --------------------------------------------------------

--
-- Structure de la table `client_1`
--

CREATE TABLE IF NOT EXISTS `client_1` (
  `cc` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `prenom` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `daten` date DEFAULT NULL,
  `pays` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `NBTELECH` decimal(5,0) NOT NULL,
  PRIMARY KEY (`cc`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `client_1`
--

INSERT INTO `client_1` (`cc`, `nom`, `prenom`, `daten`, `pays`, `NBTELECH`) VALUES
(1, 'Aztakes', 'Helene', '1990-10-01', 'France', 1);

-- --------------------------------------------------------

--
-- Structure de la table `episodes`
--

CREATE TABLE IF NOT EXISTS `episodes` (
  `ce` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `cs` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `annee` int(11) NOT NULL,
  `saison` int(11) NOT NULL,
  `realisateur` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `de` int(11) NOT NULL,
  `lim` int(3) NOT NULL,
  PRIMARY KEY (`ce`),
  KEY `cs` (`cs`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `episodes`
--

INSERT INTO `episodes` (`ce`, `titre`, `cs`, `numero`, `annee`, `saison`, `realisateur`, `de`, `lim`) VALUES
(1, 'Noel mortel', 1, 1, 1990, 1, 'David Silverman', 1500, 0),
(2, 'Bart le genie', 1, 2, 1990, 1, 'David Silverman', 1500, 0);

-- --------------------------------------------------------

--
-- Structure de la table `fichiers`
--

CREATE TABLE IF NOT EXISTS `fichiers` (
  `cf` int(11) NOT NULL AUTO_INCREMENT,
  `ce` int(11) NOT NULL,
  `type` char(1) NOT NULL,
  `prix` int(11) NOT NULL,
  PRIMARY KEY (`cf`),
  KEY `ce` (`ce`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `fichiers`
--

INSERT INTO `fichiers` (`cf`, `ce`, `type`, `prix`) VALUES
(1, 1, 'S', 1),
(2, 1, 'L', 1),
(3, 1, 'A', 3),
(4, 2, 'S', 1),
(5, 2, 'L', 1);

-- --------------------------------------------------------

--
-- Structure de la table `series`
--

CREATE TABLE IF NOT EXISTS `series` (
  `cs` int(11) NOT NULL AUTO_INCREMENT,
  `noms` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `types` varchar(1) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`cs`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `series`
--

INSERT INTO `series` (`cs`, `noms`, `types`) VALUES
(1, 'Les Simpson', 'A');

-- --------------------------------------------------------

--
-- Structure de la table `telecharger`
--

CREATE TABLE IF NOT EXISTS `telecharger` (
  `cc` int(11) NOT NULL,
  `cf` int(11) NOT NULL,
  `datet` date DEFAULT NULL,
  `dte1` int(11) NOT NULL,
  KEY `cc` (`cc`),
  KEY `cf` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `telecharger`
--

INSERT INTO `telecharger` (`cc`, `cf`, `datet`, `dte1`) VALUES
(1, 2, '2010-02-17', 173),
(2, 2, '2010-02-17', 173);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `episodes`
--
ALTER TABLE `episodes`
  ADD CONSTRAINT `EPISODES_ibfk_1` FOREIGN KEY (`cs`) REFERENCES `series` (`cs`);

--
-- Contraintes pour la table `fichiers`
--
ALTER TABLE `fichiers`
  ADD CONSTRAINT `FICHIERS_ibfk_1` FOREIGN KEY (`ce`) REFERENCES `episodes` (`ce`);

--
-- Contraintes pour la table `telecharger`
--
ALTER TABLE `telecharger`
  ADD CONSTRAINT `TELECHARGER_ibfk_1` FOREIGN KEY (`cc`) REFERENCES `clients` (`cc`),
  ADD CONSTRAINT `TELECHARGER_ibfk_2` FOREIGN KEY (`cf`) REFERENCES `fichiers` (`cf`);

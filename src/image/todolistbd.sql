-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 15 mai 2024 à 00:32
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `todolistbd`
--

-- --------------------------------------------------------

--
-- Structure de la table `liste de tache`
--

CREATE TABLE `liste de tache` (
  `ID` int(150) NOT NULL,
  `NOM` varchar(150) NOT NULL,
  `PRIORITE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `ID` int(150) NOT NULL,
  `NOM` varchar(150) NOT NULL,
  `DETAIL` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

CREATE TABLE `tache` (
  `ID` int(150) NOT NULL,
  `NOM` varchar(150) NOT NULL,
  `DATE_DE_DEBUT` date NOT NULL,
  `DATE_DE_FIN` date NOT NULL,
  `DATE_DE_RAPPEL` date DEFAULT NULL,
  `COULEUR` varchar(150) NOT NULL,
  `DETAIL` text NOT NULL,
  `ID_LISTEDETACHE` int(150) NOT NULL,
  `PRIORITE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `ID` int(150) NOT NULL,
  `Nom_utilisateur` varchar(150) NOT NULL,
  `CONNECTION_PASSWORD` varchar(150) NOT NULL,
  `ACCES_PASSWORD` varchar(150) NOT NULL,
  `EMAIL` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `liste de tache`
--
ALTER TABLE `liste de tache`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `tache`
--
ALTER TABLE `tache`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `INDEX` (`ID_LISTEDETACHE`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `liste de tache`
--
ALTER TABLE `liste de tache`
  MODIFY `ID` int(150) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `ID` int(150) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `tache`
--
ALTER TABLE `tache`
  MODIFY `ID` int(150) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(150) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
INSERT INTO `user` (`ID`, `Nom_utilisateur`, `CONNECTION_PASSWORD`, `ACCES_PASSWORD`, `EMAIL`) VALUES ('120', 'user1', 'accesttoapp', '1234user1', 'USER1@GMAIL.COM');
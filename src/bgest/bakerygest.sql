-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 15 juin 2024 à 18:48
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
-- Base de données : `bakerygest`
--

-- --------------------------------------------------------

--
-- Structure de la table `boulangerie`
--

CREATE TABLE `boulangerie` (
  `id_boulangerie` int(255) NOT NULL,
  `nom_boulangerie` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `id_gérant` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `caissiere`
--

CREATE TABLE `caissiere` (
  `id_caissiere` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_embauche` date DEFAULT NULL,
  `id_boulangerie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prénom` varchar(50) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `numéro_telephone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `date_commande` date NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL,
  `quantité_commandée` int(11) NOT NULL,
  `nom_produit` varchar(100) NOT NULL,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `prix_total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id_facture` int(11) NOT NULL,
  `date_facture` date NOT NULL,
  `nom_produit` varchar(100) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `prix_total` decimal(10,2) NOT NULL,
  `nom_boulangerie` varchar(100) NOT NULL,
  `nom_client` varchar(100) NOT NULL,
  `nom_caissiere` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id_fournisseur` int(11) NOT NULL,
  `nom_entreprise` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `numéro_telephone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `gérant`
--

CREATE TABLE `gérant` (
  `id_gerant` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prénom` varchar(50) NOT NULL,
  `date_embauche` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id_produit` int(11) NOT NULL,
  `nom_produit` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `quantité_réelle` int(11) NOT NULL,
  `quantité_disponible` int(11) NOT NULL,
  `quantité_en_attente` int(11) NOT NULL,
  `id_boulangerie` int(11) NOT NULL,
  `id_fournisseur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `boulangerie`
--
ALTER TABLE `boulangerie`
  ADD PRIMARY KEY (`id_boulangerie`),
  ADD KEY `id_gérant` (`id_gérant`);

--
-- Index pour la table `caissiere`
--
ALTER TABLE `caissiere`
  ADD PRIMARY KEY (`id_caissiere`),
  ADD KEY `id_boulangerie` (`id_boulangerie`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_produit` (`id_produit`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id_facture`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id_fournisseur`);

--
-- Index pour la table `gérant`
--
ALTER TABLE `gérant`
  ADD PRIMARY KEY (`id_gerant`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`),
  ADD KEY `id_boulangerie` (`id_boulangerie`),
  ADD KEY `id_fournisseur` (`id_fournisseur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `boulangerie`
--
ALTER TABLE `boulangerie`
  MODIFY `id_boulangerie` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `caissiere`
--
ALTER TABLE `caissiere`
  MODIFY `id_caissiere` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id_facture` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `gérant`
--
ALTER TABLE `gérant`
  MODIFY `id_gerant` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `boulangerie`
--
ALTER TABLE `boulangerie`
  ADD CONSTRAINT `boulangerie_ibfk_1` FOREIGN KEY (`id_gérant`) REFERENCES `gérant` (`id_gerant`);

--
-- Contraintes pour la table `caissiere`
--
ALTER TABLE `caissiere`
  ADD CONSTRAINT `caissiere_ibfk_1` FOREIGN KEY (`id_boulangerie`) REFERENCES `boulangerie` (`id_boulangerie`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`),
  ADD CONSTRAINT `commande_ibfk_3` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`id_boulangerie`) REFERENCES `boulangerie` (`id_boulangerie`),
  ADD CONSTRAINT `produit_ibfk_2` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

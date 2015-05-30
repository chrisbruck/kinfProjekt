-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 30. Mai 2015 um 10:12
-- Server Version: 5.6.20
-- PHP-Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `kinf_project`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `adeliger_liste`
--

CREATE TABLE IF NOT EXISTS `adeliger_liste` (
  `adeliger_id` int(20) NOT NULL,
  `adeliger_titel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fakultaet_liste`
--

CREATE TABLE IF NOT EXISTS `fakultaet_liste` (
  `fakultaet_id` int(20) NOT NULL,
  `fakultatet_titel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `jesuit_liste`
--

CREATE TABLE IF NOT EXISTS `jesuit_liste` (
  `jesuit_id` int(11) NOT NULL,
  `jesuit_titel` int(255) NOT NULL,
  `ediert` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `nachnamen_liste`
--

CREATE TABLE IF NOT EXISTS `nachnamen_liste` (
`nachname_id` int(20) NOT NULL,
  `nachname_titel` varchar(255) NOT NULL,
  `ediert` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ort_liste`
--

CREATE TABLE IF NOT EXISTS `ort_liste` (
`ort_id` int(20) NOT NULL,
  `ort_titel` varchar(255) NOT NULL,
  `ediert` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `pid` int(20) NOT NULL,
  `seite_original` int(20) NOT NULL,
  `nummer_hess` int(20) NOT NULL,
  `anrede` varchar(255) NOT NULL,
  `adeliger_id` int(20) NOT NULL,
  `jesuit_id` int(20) NOT NULL,
  `tag` int(20) NOT NULL,
  `monat` int(20) NOT NULL,
  `jahr` int(20) NOT NULL,
  `jahr_normalisiert` varchar(255) NOT NULL,
  `studienjahr_normalisiert` int(20) NOT NULL,
  `fakulataet_id` int(11) NOT NULL,
  `graduierter` int(11) DEFAULT NULL,
  `titel` varchar(255) NOT NULL,
  `titel_normalisiert` varchar(255) DEFAULT NULL,
  `fundort` varchar(255) DEFAULT NULL,
  `anmerkungen` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `personen_nachnamen`
--

CREATE TABLE IF NOT EXISTS `personen_nachnamen` (
  `person_id` int(20) NOT NULL,
  `nachname_id` int(20) NOT NULL,
  `quelle_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `personen_vornamen`
--

CREATE TABLE IF NOT EXISTS `personen_vornamen` (
  `personen_id` int(20) NOT NULL,
  `vornamen_id` int(20) NOT NULL,
  `quellen_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `persontest`
--

CREATE TABLE IF NOT EXISTS `persontest` (
  `pid` int(20) NOT NULL,
  `seite_original` int(20) NOT NULL,
  `nummer_hess` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_ort`
--

CREATE TABLE IF NOT EXISTS `person_ort` (
  `person_id` int(20) NOT NULL,
  `ort_id` int(20) NOT NULL,
  `quelle_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_seminar`
--

CREATE TABLE IF NOT EXISTS `person_seminar` (
  `person_id` int(20) NOT NULL,
  `seminar_id` int(20) NOT NULL,
  `quelle_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_studienfach`
--

CREATE TABLE IF NOT EXISTS `person_studienfach` (
  `person_id` int(20) NOT NULL,
  `studienfach_id` int(20) NOT NULL,
  `quelle_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_wirtschaftslage`
--

CREATE TABLE IF NOT EXISTS `person_wirtschaftslage` (
  `person_id` int(20) NOT NULL,
  `wl_id` int(20) NOT NULL,
  `quelle_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `quellen_liste`
--

CREATE TABLE IF NOT EXISTS `quellen_liste` (
`quelle_id` int(20) NOT NULL,
  `quelle_titel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `seminar_liste`
--

CREATE TABLE IF NOT EXISTS `seminar_liste` (
`seminar_id` int(20) NOT NULL,
  `seminar_titel` varchar(255) NOT NULL,
  `ediert` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `studienfach_liste`
--

CREATE TABLE IF NOT EXISTS `studienfach_liste` (
  `studienfach_id` int(20) NOT NULL,
  `studienfach_titel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `vornamen_liste`
--

CREATE TABLE IF NOT EXISTS `vornamen_liste` (
`vorname_id` int(20) NOT NULL,
  `vorname_titel` varchar(255) NOT NULL,
  `ediert` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `wirtschaftslage_liste`
--

CREATE TABLE IF NOT EXISTS `wirtschaftslage_liste` (
`wl_id` int(20) NOT NULL,
  `wl_titel` varchar(255) NOT NULL,
  `ediert` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zusatz_liste`
--

CREATE TABLE IF NOT EXISTS `zusatz_liste` (
  `zusatz_id` int(20) NOT NULL,
  `zusatz_titel` varchar(255) NOT NULL,
  `ediert` tinyint(1) NOT NULL,
  `quelle_id` int(20) NOT NULL,
  `person_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adeliger_liste`
--
ALTER TABLE `adeliger_liste`
 ADD PRIMARY KEY (`adeliger_id`,`adeliger_titel`);

--
-- Indexes for table `fakultaet_liste`
--
ALTER TABLE `fakultaet_liste`
 ADD PRIMARY KEY (`fakultaet_id`);

--
-- Indexes for table `nachnamen_liste`
--
ALTER TABLE `nachnamen_liste`
 ADD PRIMARY KEY (`nachname_id`);

--
-- Indexes for table `ort_liste`
--
ALTER TABLE `ort_liste`
 ADD PRIMARY KEY (`ort_id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
 ADD PRIMARY KEY (`pid`), ADD UNIQUE KEY `pid` (`pid`);

--
-- Indexes for table `personen_nachnamen`
--
ALTER TABLE `personen_nachnamen`
 ADD PRIMARY KEY (`person_id`,`nachname_id`,`quelle_id`);

--
-- Indexes for table `personen_vornamen`
--
ALTER TABLE `personen_vornamen`
 ADD PRIMARY KEY (`personen_id`,`vornamen_id`,`quellen_id`);

--
-- Indexes for table `person_ort`
--
ALTER TABLE `person_ort`
 ADD PRIMARY KEY (`person_id`,`ort_id`,`quelle_id`);

--
-- Indexes for table `person_seminar`
--
ALTER TABLE `person_seminar`
 ADD PRIMARY KEY (`person_id`,`seminar_id`,`quelle_id`);

--
-- Indexes for table `person_wirtschaftslage`
--
ALTER TABLE `person_wirtschaftslage`
 ADD PRIMARY KEY (`person_id`,`wl_id`,`quelle_id`);

--
-- Indexes for table `quellen_liste`
--
ALTER TABLE `quellen_liste`
 ADD PRIMARY KEY (`quelle_id`);

--
-- Indexes for table `seminar_liste`
--
ALTER TABLE `seminar_liste`
 ADD PRIMARY KEY (`seminar_id`);

--
-- Indexes for table `vornamen_liste`
--
ALTER TABLE `vornamen_liste`
 ADD PRIMARY KEY (`vorname_id`);

--
-- Indexes for table `wirtschaftslage_liste`
--
ALTER TABLE `wirtschaftslage_liste`
 ADD PRIMARY KEY (`wl_id`);

--
-- Indexes for table `zusatz_liste`
--
ALTER TABLE `zusatz_liste`
 ADD PRIMARY KEY (`zusatz_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nachnamen_liste`
--
ALTER TABLE `nachnamen_liste`
MODIFY `nachname_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ort_liste`
--
ALTER TABLE `ort_liste`
MODIFY `ort_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `quellen_liste`
--
ALTER TABLE `quellen_liste`
MODIFY `quelle_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `seminar_liste`
--
ALTER TABLE `seminar_liste`
MODIFY `seminar_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `vornamen_liste`
--
ALTER TABLE `vornamen_liste`
MODIFY `vorname_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `wirtschaftslage_liste`
--
ALTER TABLE `wirtschaftslage_liste`
MODIFY `wl_id` int(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

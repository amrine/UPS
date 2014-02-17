/* Diallo Alpha Oumar Binta
	Tp1 SIAW
	L2 info Paul Sabatier
*/

INSERT INTO `forum`.`faq` (
`pseudofaq` ,
`emailfaq` ,
`messagefaq` ,
`datefaq`
)
VALUES (
'alpha', 'f@yahoo.fr', 'test de la faq', '2011-08-25 00:37:27'
);

SELECT forum_name, topic_title, topic_first_poster_name, topic_time, topic_views, topic_replies, topic_last_poster_name, topic_last_post_time
FROM membre_topics, membre_forums
WHERE membre_topics.forum_id = membre_forums.forum_id
/* Q1 creation des tables */

CREATE TABLE `astuces` (
`code` int NOT NULL AUTO_INCREMENT ,
`titre` text COLLATE latin1_general_ci NOT NULL ,
`contenu` text COLLATE latin1_general_ci NOT NULL ,
PRIMARY KEY ( `code` ) 
);

INSERT INTO `astuces` VALUES (1,"installation","Nous allons voir comment virtualiser 
un syst&egrave;me d'exploitation sous Ubuntu ou une de ses nombreuses variantes (Kubuntu, Xubuntu...) .
Je vous conseille quand m&ecirc;me d'avoir une machine avec un minimum de puissance,
 il faut savoir que quand vous serez sur votre syst&egrave;me virtuel, 
votre syst&egrave;me actuel sera actif en arri&egrave;re plan.");

CREATE TABLE `cours` (
`code` int NOT NULL AUTO_INCREMENT ,
`titre` text COLLATE latin1_general_ci NOT NULL ,
`auteur` varchar(100) COLLATE latin1_general_ci NOT NULL ,
`datecre` date,
`types` varchar(10) COLLATE latin1_general_ci NOT NULL ,
`vu` int NOT NULL ,
`lien` varchar(100) COLLATE latin1_general_ci NOT NULL ,
PRIMARY KEY ( `code` ) 
);

INSERT INTO `cours` VALUES (2,'installation linux vracnkhdfkzghnehj','alpha',2010/02/09,'P',30,'comment installer linux');
CREATE TABLE `livreor` (
`id` int NOT NULL AUTO_INCREMENT ,
`pseudo` varchar(255) COLLATE latin1_general_ci NOT NULL ,
`message` text COLLATE latin1_general_ci NOT NULL ,
PRIMARY KEY ( `id` ) 
);


SELECT post_subject
FROM `membre_posts` where post_subject  not like "%Re:%"





CREATE TABLE `EPISODES` (
`ce` int( 11 ) NOT NULL AUTO_INCREMENT ,
`titre` varchar( 30 ) COLLATE latin1_general_ci NOT NULL ,
`cs` int(11) NOT NULL,
`numero` int(11) NOT NULL,
`annee` int(11) NOT NULL,
`saison` int(11) NOT NULL,
`realisateur` varchar(30) COLLATE latin1_general_ci NOT NULL,
`de` int(11) NOT NULL,
`lim` int(3) NOT NULL,
PRIMARY KEY ( `ce` ),
FOREIGN KEY (`cs`) REFERENCES `SERIES`(`cs`)
);

CREATE TABLE `CLIENTS` (
`cc` int( 11 ) NOT NULL AUTO_INCREMENT ,
`nom` varchar( 30 ) COLLATE latin1_general_ci NOT NULL ,
`prenom` varchar(30) COLLATE latin1_general_ci NOT NULL,
`daten` date,
`pays` varchar(30) COLLATE latin1_general_ci NOT NULL,
PRIMARY KEY ( `cc` )
);

CREATE TABLE `FICHIERS` (
`cf` int( 11 ) NOT NULL AUTO_INCREMENT ,
`ce` int(11) NOT NULL,
`type` char(1)  NOT NULL,
`prix` int(11) NOT NULL,
PRIMARY KEY ( `cf` ),
FOREIGN KEY (`ce`) REFERENCES `EPISODES`(`ce`)
);

CREATE TABLE `TELECHARGER` (
`cc` int( 11 ) NOT NULL  ,
`cf` int( 11 ) NOT NULL  ,
`datet` date,
`dte1` int( 11 ) NOT NULL  ,
FOREIGN KEY (`cc`) REFERENCES `CLIENTS`(`cc`),
FOREIGN KEY (`cf`) REFERENCES `FICHIERS`(`cf`)
);

/* Q 2

il est impossible de supprimer la relation CLIENT: une contrainte de clé étrangère échoue

*/

/* Q3 insertion des données */
INSERT INTO `CLIENTS` VALUES (1,'Aztakes','Helene','1990-10-01','France');
INSERT INTO `SERIES` VALUES (1,'Les Simpson','A');
INSERT INTO `EPISODES` VALUES (1,'Noel mortel',1,1,1990,1,'David Silverman',1500,0);
INSERT INTO `EPISODES` VALUES (2,'Bart le genie',1,2,1990,1,'David Silverman',1500,0);
INSERT INTO `FICHIERS` VALUES (1,1,'S',0);
INSERT INTO `FICHIERS` VALUES (2,1,'L',0.99);
INSERT INTO `FICHIERS` VALUES (3,1,'A',2.99);
INSERT INTO `FICHIERS` VALUES (4,2,'S',0.50);
INSERT INTO `FICHIERS` VALUES (5,2,'L',0.99);
/* Q 4*/
INSERT INTO `TELECHARGER` VALUES(1,2,'2010-02-17',173);

/* Q 5

INSERT INTO `CLIENTS` VALUES(1,'Assein','Maroc','1989-09-04','France');
renvoie " Duplicate entry '1' for key 'PRIMARY'" car elle esciste deja

INSERT INTO `FICHIERS` VALUES(6,3,'S',0);
cette instruction ne marche pas car le code client 6 et le code fichier 3 n'existe pas dans la base

*/

/* Q 6

on ne peut pas supprimer la serie les simpsons car sa clé primaire est utlisé comme clé etrangere das d'autres tables

*/

/* Q 7 les fichiers en streaming sont vendus a  0.99 */
UPDATE `FICHIERS` SET `prix`= 0.99 WHERE `type` = 'S';

/* Q 8 */
CREATE TABLE `CLIENT_1` (
`cc` int( 11 ) NOT NULL AUTO_INCREMENT ,
`nom` varchar( 30 ) COLLATE latin1_general_ci NOT NULL ,
`prenom` varchar(30) COLLATE latin1_general_ci NOT NULL,
`daten` date,
`pays` varchar(30) COLLATE latin1_general_ci NOT NULL,
PRIMARY KEY ( `cc` )
);

INSERT INTO `CLIENT_1`
SELECT *
FROM `CLIENTS`
WHERE cc =1;

/*Q 9 */
ALTER TABLE `CLIENT_1` ADD `NBTELECH` decimal(5) not null;

UPDATE CLIENT_1 SET NBTELECH = ( SELECT count( TELECHARGER.cc )
FROM TELECHARGER
WHERE CLIENT_1.cc = TELECHARGER.cc ) ;




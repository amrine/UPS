CREATE TABLE Customer
(
SID int( 11 ) NOT NULL CHECK (SID > 0), /* La colonne "SID" ne peut accepter que des entiers supérieurs à 0. */
Last_Name varchar (30) NOT NULL,  /*"Last_Name" n’accepte pas de valeur NULL*/
First_Name varchar(30) UNIQUE); /*n'accepte pas de doublons */
);

CREATE TABLE ORDERS
(
Order_ID int( 11 ) NOT NULL,
Primary Key (Order_ID), /* cle primaire */
Foreign Key (Customer_SID) references CUSTOMER(SID)); /*cle etrangere */
Address char(50) default 'Unknown',
datet date,
);


UPDATE CLIENT_1 SET NBTELECH = ( SELECT count( TELECHARGER.cc )
FROM TELECHARGER
WHERE CLIENT_1.cc = TELECHARGER.cc ) ;

ALTER TABLE `CLIENT_1` ADD `NBTELECH` decimal(5) not null;

DELETE FROM `CLIENT_1` WHERE <expression>

DROP TABLE `CLIENTS`

CREATE DATABASE `TP`

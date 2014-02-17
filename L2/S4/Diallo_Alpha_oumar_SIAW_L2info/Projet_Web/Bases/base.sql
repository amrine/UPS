create table SERIES (
cs tinyint(4) auto_increment,
noms varchar(20),
types char(1),
image varchar(20),
constraint pk_series PRIMARY KEY(cs)
) TYPE=InnoDB;# MySQL n'a retourné aucun enregistrement.


create table EPISODES (
ce tinyint(4) auto_increment,
titre varchar(50),
cs tinyint(4),
numero decimal(2),
annee decimal(4),
saison decimal(2),
realisateur varchar(20),
de decimal(6),
lim decimal(2),
image varchar(20),
constraint pk_episodes PRIMARY KEY(ce),
constraint fk_episodes_series FOREIGN KEY(cs) REFERENCES SERIES(cs)
) TYPE=InnoDB;# MySQL n'a retourné aucun enregistrement.


create table FICHIERS (
cf tinyint(4) auto_increment,
ce tinyint(4),
type char(1),
prix decimal(4,2),
constraint pk_fichiers PRIMARY KEY(cf),
constraint fk_fichiers_episodes FOREIGN KEY(ce) REFERENCES EPISODES(ce)
) TYPE=InnoDB;# MySQL n'a retourné aucun enregistrement.


create table CLIENTS (
cc tinyint(4) auto_increment,
nom varchar(20),
prenom varchar(20),
daten date,
pays varchar(15),
constraint pk_clients PRIMARY KEY(cc)
)TYPE=InnoDB;# MySQL n'a retourné aucun enregistrement.


create table TELECHARGER (
cc tinyint(4),
cf tinyint(4),
datet date,
dtel decimal(7),
constraint pk_telecharger PRIMARY KEY(cc,cf,datet),
constraint fk_telecharger_clients FOREIGN KEY(cc) REFERENCES CLIENTS(cc),
constraint fk_telecharger_fichiers FOREIGN KEY(cf) REFERENCES FICHIERS(cf)
)TYPE=InnoDB;# MySQL n'a retourné aucun enregistrement.

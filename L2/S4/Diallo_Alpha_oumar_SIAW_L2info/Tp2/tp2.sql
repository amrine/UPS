/* Requetes monotables */
/*Q1*/
select nom,prenom from CLIENTS
where pays='France';

/*Q2*/
select cc,nom,prenom from CLIENTS
where pays='France'
and pays='Angleterre';

/*Q3*/
select cc,nom,prenom from CLIENTS
where pays!='France'
and pays!='Angleterre';

/*Q4*/
select distinct pays from CLIENTS;

/*Q5*/
SELECT titre
FROM episode
where titre LIKE '%simpson';

/*requetes multitables */
/*Q1*/
select clients.cc,nom,prenom from clients,telecharger
where clients.cc = telecharger.cc;

/*Q2*/
select clients.cc,nom,prenom from clients,telecharger,fichiers
where clients.cc = telecharger.cc 
and fichiers.cf = telecharger.cf
and type= 'A';
/*Q3*/
select titre from episodes,fichiers,telecharger
where episodes.ce = fichiers.ce
and  fichiers.cf = telecharger.cf
and type ='A';
/*Q4*/
select titre,noms from series,episodes,fichiers,telecharger
where series.cs = episodes.cs
and episodes.ce = fichiers.ce
and fichiers.cf = telecharger.cf
and type='A';
/*Q5*/
select noms from series,clients,telecharger
where clients.cc = telecharger.cc
and pays = 'France';
/*Q6*/
select clients.cc,nom,prenom from series,clients,episodes,telecharger,fichiers
where series.cs = episodes.cs
and episodes.ce = fichiers.ce
and fichiers.cf = telecharger.cf
and pays = 'France'
and noms in ('Les Simpson','Dexter');
/*

/* Requetes multitables (ensemblistes/imbriqués) */
/*Q1*/
select cc,nom,prenom from clients
where cc in(select cc from telecharger where cf in(select cf from fichiers where type= 'A'));
/*Q2*/
/*imbriqué */
select cc,nom,prenom from clients
where cc in(select cc from telecharger where cf in(select cf from fichiers where type in ('A','L')));
/*ensembliste */
SELECT cc, nom, prenom
FROM clients
WHERE cc
IN (SELECT cc FROM telecharger WHERE cf IN (SELECT cf FROM fichiers WHERE TYPE = 'A'))
UNION 
SELECT cc, nom, prenom
FROM clients WHERE cc
IN ( SELECT cc FROM telecharger WHERE cf IN ( SELECT cf FROM fichiers WHERE TYPE = 'L'));

/*Q3*/
SELECT cc, nom, prenom
FROM clients
WHERE cc
IN (SELECT cc FROM telecharger WHERE cf IN (SELECT cf FROM fichiers WHERE ce in (select ce from episodes where cs in (select cs from series))))
and pays= 'France ';
/*Q4*/
select cc,nom,prenom from clients
where  cc not in (select cc from telecharger);

/* requetes agrégées et groupements */
/*Q1*/
select count(*) from series;
/*Q2*/
select count(clients.cc),nom,prenom from clients,telecharger
where clients.cc = telecharger.cc
group by clients.cc;
/*Q3*/
select count(clients.cc),nom,prenom from clients,telecharger
where clients.cc = telecharger.cc
group by clients.cc,nom,prenom;
/*Q4*/
select titre,max(prix) from episodes,fichiers
where episodes.ce = fichiers.ce;


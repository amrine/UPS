/*Question 4
1)--une parcelle resultant d'une division est forcemment de superficie plus petite
--que la parcelle d'origine
--mutante=parcelle contraignante=op_division parc_division
--
*/


create or replace trigger Superficie_Ok3
before update of idparcop on Op_Division
declare
idParcOrg Parcelle.idparcelle%type;
superfParcOrg Parcelle.superf%type;
maxSuperf Parcelle.superf%type;
begin
 select idparcelle,superf into idParcOrg,superfParcOrg  from parcelle ,op_division where idparcelle=idparcop;
 select max(superf) into maxSuperf from parcelle 
 where
	idparcelle in( select idparcres from parc_division where idop in
	(select idop from op_division where idparcop=idparcOrg));
--compare les deux superficies
  if(maxSuperf > superfParcOrg) then
	raise_application_error(-20006,'la superficie de la parcelle resultante d''une
	  division doit avoir une superficie plus petite que la parcelle d''origine ');
  end if;
end;
/
show errors;



create or replace trigger Superficie_Ok
before update of superf on Parcelle
declare
idParcOrg Parcelle.idparcelle%type;
superfParcOrg Parcelle.superf%type;
maxSuperf Parcelle.superf%type;
begin
 select idparcelle,superf into idParcOrg,superfParcOrg  from parcelle ,op_division where idparcelle=idparcop;
 select max(superf) into maxSuperf from parcelle 
 where
	idparcelle in( select idparcres from parc_division where idop in
	(select idop from op_division where idparcop=idparcOrg));
--compare les deux superficies
  if(maxSuperf > superfParcOrg) then
	raise_application_error(-20006,'la superficie de la parcelle resultante d''une
	  division doit avoir une superficie plus petite que la parcelle d''origine ');
  end if;
end;
/
show errors;

create or replace trigger Superficie_Ok2
before insert or update on Parc_division
for each row
declare
idParcOrg Parcelle.idparcelle%type;
superfParcOrg Parcelle.superf%type;
maxSuperf Parcelle.superf%type;
begin
  select idparcop into idParcOrg from op_division where idop=:new.idop;
  select max(superf) into maxSuperf from parcelle
  where
	idparcelle in( select idparcres from parc_division where idop in
	(select idop from op_division where idparcop=idparcOrg));
--compare les deux superficies
  if(maxSuperf > superfParcOrg) then
	raise_application_error(-20008,'la superficie de la parcelle resultante d''une
	  division doit avoir une superficie plus petite que la parcelle d''origine ');
  end if;
end;
/
show errors;

--update parcelle set superf=800 where idparcelle='P6'

/*Question 4
2)la somme des superficies des parcelles resultant d'une division est egale à la superficie de
la parcelle d'origine
*/
create or replace trigger SommeSuperficie_Ok
before update on Parcelle
declare
idParcOrg Parcelle.idparcelle%type;
superfParcOrg Parcelle.superf%type;
sommeSuperf Parcelle.superf%type;
begin
 select idparcelle,superf into idParcOrg,superfParcOrg  from parcelle ,op_division where idparcelle=idparcop;
 select sum(superf) into sommeSuperf from parcelle 
 where
	idparcelle in( select idparcres from parc_division where idop in
	(select idop from op_division where idparcop=idparcOrg));
--compare les deux superficies
  if(superfParcOrg < sommeSuperf) then
	raise_application_error(-20007,'la somme des superficies des parcelles resultant d''une
	  division est égale à la superficie de la parcelle d''origine ');
  end if;
end;
/
show errors;

/*Question 3
trigger table
*/
create or replace trigger Parcelle_Ok
after insert or delete on Sommet
declare
cpteur Number;
i Number;
begin
 if(inserting) then
	select count(*) into cpteur from Sommet s1
	group by s1.idparcelle
	having count(idborne)!=(select max(numordre) from sommet s2
	where s1.idparcelle=s2.idparcelle);
 		if(cpteur>0) then 
  			raise_application_error(-20005,'ordre sequentielle non respecte');
		end if;
 end if;
 if (deleting) then
	for idp in(select idparcelle from Sommet s1
				group by s1.idparcelle
				having count(idborne)!=(select max(numordre) from sommet s2
				where s1.idparcelle=s2.idparcelle)) loop
		i:=1;
		for numparc in(select numordre from sommet where idparcelle=idp.idparcelle) loop
			if (numparc.numordre != i) then
				update sommet set numordre=i where idparcelle=idp.idparcelle
				and numordre=numparc.numordre;
			end if;
		i:=i+1;
		end loop;		
	end loop;
 end if;
end;
/
show errors;
--test
--insertion
insert into sommet  values('P1',25,'G');
--suppression
delete from sommet where idparcelle='P5' and numordre=2;
/*Question 2*/
create or replace trigger Division_Ok
before insert or update on Op_Division
for each row
declare
vType Parcelle.TypeP%type;
vDatecre Parcelle.datecd%type;
begin
 select TypeP,datecd into vType,vDatecre from Parcelle
 where idparcelle=:new.idparcop;
 if(vType!='TERRE' or vType!='BOIS') then
  raise_application_error(-20002,'Type de parcelle non reconnu pour etre divisé');
 elsif(:new.nbparcres<2) then
  raise_application_error(-20003,'Nombre de parcelle resultant de la division inferieur a 2');
 elsif(:new.dateop < vDatecre) then
  raise_application_error(-20004,'date d''opertion posterieure à la date de creation');
 end if;
end;
/
show errors;


/*Question 1 */
create or replace trigger Borne_Distinct
after insert or update on Borne
declare
val Number;
begin
  select count(*) into val from Borne b,Borne c
	where b.x=c.x
	and b.y=c.y
	and b.idborne != c.idborne;
if(val>0) then 
  raise_application_error(-20001,'Les bornes ne sont pas distinctes');
end if;
end;
/
show errors;


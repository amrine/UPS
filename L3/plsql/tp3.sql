
/*
Question 1
*/

create or replace procedure Inserer_Parcelle(vIdParc Parcelle.IdParcelle%Type, VNumPropC Parcelle.NumPropC%Type,
vTypeP  Parcelle.TypeP%Type,vSuperf  Parcelle.Superf%Type) is

Identifiant_Existant Exception;
Proprietaire_Inconnu Exception;
Type_Parcelle_Inconnu Exception;
flag Number;
vParcExistant Number;
vPropExistant Number;
Begin
-- test unicité d'une parcelle
 flag:=1;
 select count(*) into vParcExistant  from Parcelle where IdParcelle=vIdParc;
 if(vParcExistant=0) then raise Identifiant_Existant ;
 end if;
-- test existance du propriétaire
 flag:=2;
 select count(NumPropC) into vPropExistant  from Proprietaire where NUMPropC=VNumPropC;
 if(vPropExistant=0) then raise Proprietaire_Inconnu ;
 end if;
--test du type de parcelle
 if(vTypeP!='TERRE' and vTypeP!='BOIS' and vTypeP!='HABITATION') then raise Type_Parcelle_Inconnu;
 end if;
-- insertion de la parcelle
 insert  into Parcelle values(vIdParc,vNumPropC,vTypeP,SYSDATE,vSuperf);
 commit;
 Exception
  when NO_DATA_FOUND then
	if(flag=1) then dbms_output.put_line('Identifiant de parcelle déjà Existant');
	elsif (flag=2) then dbms_output.put_line('Proprietaire Inconnu');
	end if;
  when Type_Parcelle_Inconnu then dbms_output.put_line('Ce type de parcelle est inconnu');
  when TOO_MANY_ROWS then dbms_output.put_line('Parcelle en double');
  when DUP_VAL_ON_INDEX then dbms_output.put_line('Cette parcelle existe déjà');
End;
/
show errors;
--test insertion
set serveroutput on;
accept vIdp prompt 'Entrer un numéro de parcelle :';
accept vIdProp prompt 'Entrer un numéro de propriétaire :';
accept vType prompt 'Entrer un type de parcelle :';
accept vSuper prompt 'Entrer une superficie :';
Begin
Inserer_Parcelle('&vIdp',&vIdProp,'&vType',&vSuper);
end;
/


/*Question 2
*/
create or replace procedure Verifier_Parcelle(vIdParc Parcelle.IdParcelle%Type) is
borneInvalide Exception;
parcelleConfondue Exception;
vNbreBorne Number;
premiereOccurence boolean;
continuer boolean;
cursor c1 is (select idborne from sommet where idparcelle=vIdParc);
cursor c2 is (select idparcelle from sommet where idparcelle<>vIdParc
				group by idparcelle
				having count(idparcelle)=(select count(*) from sommet where idparcelle=vIdParc));
Begin
  select count(idparcelle) into vNbreBorne from Sommet where Sommet.IdParcelle=vIdParc;
  if(vNbreBorne <=2 ) then
	raise borneInvalide;
  end if;
  continuer:= true;
for c2Line in c2 loop
	for c3Line in (select idborne from sommet where idparcelle=c2Line.idparcelle) loop
	  for c1Line in c1 loop
		if(c3Line.idborne=c1Line.idborne) then
		  premiereOccurence:= true;
		  continuer:= false;
		 else premiereOccurence:= false;
		 end if;
	  end loop;
	  if(continuer= false) then raise parcelleConfondue;
	  end if;
	end loop;	  
end loop;			
Exception
	when borneInvalide then dbms_output.put_line('Nombre de borne inferieur ou egal a 2 : erreur');
	when parcelleConfondue then dbms_output.put_line('une autre parcelle existe avec les memes bornes');
End;
/


create or replace package MonPackage as
  procedure Inserer_Parcelle(vIdParc Parcelle.IdParcelle%Type, VNumPropC Parcelle.NumPropC%Type,
vTypeP  Parcelle.TypeP%Type,vSuperf  Parcelle.Superf%Type);
  procedure Inserer_Proprio(nom proprietaire.NomProp%type,ville proprietaire.Ville%type);
  procedure Diviser_Parcelle(numeroParcelle Parcelle.idparcelle%type,nbreDiv number);
  function Perimetre(numeroParcelle Parcelle.idparcelle%type) return number;
  function PlusGrandeParcelle(numProprio proprietaire.NumPropC%type) return parcelle.idparcelle%type;
end;
/


create or replace package body MonPackage as

procedure Inserer_Parcelle(vIdParc Parcelle.IdParcelle%Type, VNumPropC Parcelle.NumPropC%Type,
vTypeP  Parcelle.TypeP%Type,vSuperf  Parcelle.Superf%Type) is

Identifiant_Existant Exception;
Proprietaire_Inconnu Exception;
Type_Parcelle_Inconnu Exception;
flag Number;
vParcExistant Number;
vPropExistant Number;
Begin
-- test unicité d'une parcelle
 flag:=1;
 select count(*) into vParcExistant  from Parcelle where IdParcelle=vIdParc;
 if(vParcExistant=0) then raise Identifiant_Existant ;
 end if;
-- test existance du propriétaire
 flag:=2;
 select count(NumPropC) into vPropExistant  from Proprietaire where NUMPropC=VNumPropC;
 if(vPropExistant=0) then raise Proprietaire_Inconnu ;
 end if;
--test du type de parcelle
 if(vTypeP!='TERRE' and vTypeP!='BOIS' and vTypeP!='HABITATION') then raise Type_Parcelle_Inconnu;
 end if;
-- insertion de la parcelle
 insert  into Parcelle values(vIdParc,vNumPropC,vTypeP,SYSDATE,vSuperf);
 commit;
 Exception
  when NO_DATA_FOUND then
	if(flag=1) then dbms_output.put_line('Identifiant de parcelle déjà Existant');
	elsif (flag=2) then dbms_output.put_line('Proprietaire Inconnu');
	end if;
  when Type_Parcelle_Inconnu then dbms_output.put_line('Ce type de parcelle est inconnu');
  when TOO_MANY_ROWS then dbms_output.put_line('Parcelle en double');
  when DUP_VAL_ON_INDEX then dbms_output.put_line('Cette parcelle existe déjà');
End;

procedure Inserer_Proprio(nom proprietaire.NomProp%type,ville proprietaire.Ville%type) is
  cle number;
	begin
	select max(numpropc) into cle from proprietaire;
	cle:=cle+1;
	INSERT into proprietaire values(cle,nom,ville);
	commit;
	exception
	  when DUP_VAL_ON_INDEX then dbms_output.put_line('Ce proprietaire est déjà enregistrer');
end;

procedure Diviser_Parcelle(numeroParcelle Parcelle.idparcelle%type,nbreDiv number) is
TypeP varchar;
cle number;
mauvaisType exception;
  begin
  select typep into TypeP from parcelle where idparcelle=numeroParcelle;
  if(TypeP <>'TERRE') then raise mauvaisType; end if;
  select max(idop) into cle from op_division;
	cle:=cle+1;
  INSERT into op_division values(cle,numeroParcelle,nbreDiv,SYSDATE);
	commit;
  exception
  when mauvaisType dbms_output.put_line('Vous avez entré un mauvais type de parcelle');
  when DUP_VAL_ON_INDEX then dbms_output.put_line('parcelle deja divisé');
end;

end;
/

show errors;

set serveroutput on;
accept nom prompt 'Entrer votre nom :';
accept ville prompt 'Entrer votre ville :';
Begin
Inserer_Proprio('&nom','&ville');
end;
/


  /*l3inf36*/
  
  
  
  
  
  
  
  
  
  
  
//comportement general
template <typename T>
class MaClasseTemplate
{
	T Data; //donnee de type generique
public:
	MaClasseTemplate();
	//accesseur a la volee
	T GetData() const { return Data;}
	//mutateur defini hors de la classe
	void SetData(const T Param);

};

//definition externe du mutateur (egalement dans le point .h
template <typename T>
void MaClasseTemplate<T>::SetData(const T Param)
{
	Data = Param;
}


//specialisation pour le comportement general
template <typename H>
class MaClasse
{
public:
	H Fonction(); //comportement general

};

//specialisation pour le type char
template <>
class MaClasse<char>
{
public:
	char Fonction(); //comportement pour le type char

};
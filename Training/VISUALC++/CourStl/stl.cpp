#include "stlTemplate.h"

//cet alias permet d'utiliser ClassI a la place de MaClasseTemplate<int>
typedef MaClasseTemplate<int> ClassI;

//modele de fonction general
template <typename Z>
void testFonction(Z Param)
{
	//comportement general
}

//specialisation pour le type int
template <>
void testFonction<int>(int Param)
{
	//comportement pour le type int
}
void main(void)
{
	MaClasseTemplate<int> objet1;//ok, type de donnee correctement utilise
	ClassI objet2; //ok

	testFonction('f');//comportement general
	testFonction(12);//comportement special de type int
}
/*function d=distance_vect(sequence1,sequence2,indicei,indicej)
    v1=sequence1(:,indicei); %pour prendre toute la i eme ligne 
    v2=sequence2(:,indicej);
    d = 0;
    for i=1:13
        d=d+(v1(i)-v2(i)).^2;
    end
    d = sqrt(d);
    
    %sqrt((sum(a-b)).^2)
    %13 calculs de distance (entre 1 mot de jacque et les 13 de alphonse
end
*/

#include <iostream> //necesaire pour le count donc ici pas besoin ?
#include <cmath>
using namespace std;

//mettre double* a la place de double[] ? float ?
float distance_vect(float* sequence1,float* sequence2,int indicei,int indicej, int dim_mfcc)  //int tailleMot1 , int tailleMot2 ? virer le dim_mfcc ?
{
	//declarer v1 et v2 ?
	int const tailleTableau(dim_mfcc);
	float v1[tailleTableau];
	float v2[tailleTableau];

	
	//deja transposé
	cout << "le 1er vecteur est : " << endl;
	for (int i(0); i<dim_mfcc ; i++){ 
        v1[i] = sequence1[indicei + dim_mfcc*i];
	//cout << v1[i];
    }
	//cout << "" << endl;
	
	for (int i(0); i<dim_mfcc ; i++){ 
       v2[i] = sequence2[indicej + dim_mfcc*i];
    }
	
    float d(0);
    for (int i(0); i<dim_mfcc ; i++){ 
        d = d + pow((v1[i]-v2[i]),2); 
    }
    d = sqrt(d);
    return d;
    //sqrt((sum(a-b)).^2)
}

int main()
{
	float retour;
	
	float sequence1[9] = {2, 1, 1, 1, 3, 1, 0, 0, 1}; 
	float sequence2[9] = {5, 1, 0, 1, 0, 0, 3, 5, 1}; 
	//tester si ca crée bien v1 et v2
	//afficher v1 et v2
	retour = distance_vect(sequence1,sequence2,0,0,3); 
	//affichage du retour du sous programme
	cout << "Le resultat est : " << retour << endl; 
}

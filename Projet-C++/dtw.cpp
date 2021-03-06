/*******************************************************************************
 *
 * Drone control through voice recognition -- PC to drone communication
 * Team GYTAM, feb. 2016
 *
 *
 ******************************************************************************/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h> // for memcmp
#include <stdint.h> // for int16_t and int32_t
#include <math.h>
#include <iostream>
#include "dtw.h"
#define FLOAT_MAX 10000000.0 


float distEucl(float* c_k, float* c_unk , int dim_mfcc,int i,int j){
	return (float)i;
}

float min (float f1,float f2,float f3){
	if(f1<f2 && f1<f3)
		return f1;
	if(f2<f1 && f2<f3)
		return f2;
	if(f3<f1 && f3<f2)
		return f3;
}

using namespace std;

//mettre double* a la place de double[] ? float ?
float distance_vect(float* sequence1,float* sequence2,int indicei,int indicej, int dim_mfcc)  //int tailleMot1 , int tailleMot2 ? virer le dim_mfcc ?
{
	//declarer v1 et v2 ?
	int const tailleTableau(dim_mfcc);
	float v1[tailleTableau];
	float v2[tailleTableau];

	
	//deja transposé
	//cout << "le 1er vecteur est : " << endl;
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


/**
* Dtw function that given two matrix of cep coefficient computes distance
* between those two signals.
*  @param n_ck      Dimension of unknow signal s1
*  @param n_cunk    Dimension of know signal    s2
*  @param dim_mfcc  Size of nfcc decompostion base
*  @param c_k       Matrix of know signal
*  @param c_unk     Matrix of unknow signal
*  @return Distance between the two signals
*/


float dtw(int n_ck, int n_cunk, int dim_mfcc, float* c_k, float* c_unk) {

	int d1=1;
	int d2=1;
	int d3=1;
	int i,j;
	float f1,f2,f3,dist,D;
	int nb_elem = n_ck*n_cunk;
	float * ptr_g = (float*) malloc (nb_elem);
	for(i=0;i<nb_elem;i++)
	{
		ptr_g[i]=0;
	}
	for(i=1;i<n_ck;i++)
	{
		ptr_g[i*n_ck]=FLOAT_MAX;
		
	}
	for(i=1;i<n_ck;i++)
	{
		ptr_g[i]=FLOAT_MAX;
		for(j=1;j<n_cunk;j++)
		{
			dist=distance_vect( c_k, c_unk ,i,j,dim_mfcc);
			f1=ptr_g[(i-1)*n_ck+j]+d1*dist;
			f2=ptr_g[(i-1)*n_ck+j-1]+d1*dist;
			f3=ptr_g[(i)*n_ck+j-1]+d1*dist;
			ptr_g[i*n_ck+j]=min(f1,f2,f3);
		}
	}
	//D=ptr_g[i*n_ck+n_cunk]/(n_ck+n_cunk);
	D=ptr_g[nb_elem-1]/(n_ck+n_cunk);
	for(i=0;i<n_ck;i++)
	{
		printf(" \n");
		for(j=0;j<n_cunk;j++)
		{
			printf("%f ",ptr_g[i*n_ck+j]);
		}
	}
	//printf("\n d= %f \n",ptr_g[nb_elem-1]);
	//free (ptr_g);
return D;
}


/*function [ D, g ]= CalculDistanceDTW (sequence1 , sequence2 ,distance)
d1=1;
d2=1;
d3=1;
tailleS1=length(sequence1)+1;
tailleS2=length(sequence2)+1;1

g=zeros(tailleS1,tailleS2);
g
for i= 2:tailleS2
    g(1,i)=inf;
end
for i= 2:tailleS1
    g(i,1)=inf;
    
    for j= 2:tailleS2 
        A=[g(i-1,j)+d1*feval(distance,sequence1,sequence2,i-1,j-1),...
           g(i-1,j-1)+d2*feval(distance,sequence1,sequence2,i-1,j-1),...
           g(i,j-1)+d3*feval(distance,sequence1,sequence2,i-1,j-1)];
        g(i,j)=min(A);
    end         
end
D=g(tailleS1,tailleS2)/((tailleS1)+(tailleS2));
end
*/
/* int main ()
{
float test=1.1;
int n_ck=4;
 int n_cunk=4;
 int dim_mfcc=2;
 float* c_k=&test;
 float* c_unk=c_k;
 float f1=1.0;
 float f2=1.0;
 float f3=1.0;
  	float sequence1[9] = {2, 1, 1, 1, 3, 1, 2, 5, 1}; 
	float sequence2[9] = {2, 1, 1, 1, 3, 1, 2, 5, 1}; 
f1= dtw( 9,9,dim_mfcc,  sequence1,sequence2);
 printf("\n d= %f \n",f1);

  return 0;
}   */


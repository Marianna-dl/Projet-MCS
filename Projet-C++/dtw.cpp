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
#include <algorithm>
#define FLOAT_MAX 10000000.0 
#include <limits>

//#define FLOAT_MAX 1000


float min (float f1,float f2,float f3){
	if(f1<f2 && f1<f3)
		return f1;
	if(f2<f1 && f2<f3)
		return f2;
	if(f3<f1 && f3<f2)
		return f3;
}

using namespace std;


float distance_vect(float* sequence1,float* sequence2,int indicei,int indicej)  
{  
	
	float d =  sequence1[indicei]-sequence2[indicej]; 
	/*std::cout.setf(std::ios_base::fixed, std::ios_base::floatfield);
	std::cout.precision(4);*/
	return fabs(d);
    
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
	double myinf = std::numeric_limits<double>::infinity();

	int d1=1;
	int d2=1;
	int d3=1;
	int i,j;
	float f1,f2,f3,dist,D;
	int nb_elem = (n_ck+1)*(n_cunk+1);
	
	float * ptr_g = (float*) malloc (sizeof(float) * nb_elem);
	int cpt = 0;
	
	//Initialisation de la matrice
	for(i=0;i<nb_elem;i++)
	{
		ptr_g[i] = 0;
	
	}
	
	for(i=1;i<n_cunk+1;i++)
	{
		ptr_g[i] = myinf;
	}
	

	for(i=1;i<n_ck+1;i++){
		ptr_g[i*(n_cunk+1)] = myinf;
	}
	
	

	for(i=1;i<n_ck+1;i++)
	{
		for(j=1;j<n_cunk+1;j++)
		{ 	
			dist=distance_vect( c_k, c_unk ,i-1,j-1);
			
			f1=ptr_g[(i-1)*(n_cunk+1)+j]+d1*dist;
			f2=ptr_g[(i-1)*(n_cunk+1)+j-1]+d1*dist;
			f3=ptr_g[i*(n_cunk+1)+(j-1)]+d1*dist;
			ptr_g[i*(n_cunk+1)+j]=min(f1,f2,f3);
			
		}

	}
	/*
	for(i=0;i<nb_elem;i++){
		
		cpt++;
		cout<<ptr_g[i]<<" ";
		
		if(cpt == n_cunk+1){
			cout<<endl;
			cpt = 0;
		}
	}*/

	cout<<endl;

	/*std::cout.setf(std::ios_base::fixed, std::ios_base::floatfield);
	std::cout.precision(4);*/
	//cout<<ptr_g[nb_elem-1]<<endl;
	//cout<<n_ck+1+n_cunk+1<<endl;
	D = ptr_g[nb_elem-1]/(n_ck+1+n_cunk+1);
	//free(ptr_g);
	return D;

}

/*
int main ()
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
	// m01 arrete toi
  	float sequence1[20] = {-29.47844,4.56099,0.39657,-0.01331,0.04076,2.17803,-0.76123,-0.16993,1.01295,-1.13256,0.25960,-0.26968,0.52417,-30.08583,5.21871,2.47175,1.70361,0.60264,1.17376,-0.72708}; 
	// m01 fais un flip
	//float sequence2[20] = {-31.46570,5.98452,1.58243,0.83127,-0.01257,1.16902,-0.45578,0.85829,-0.73704,-1.47210,-0.72572,0.03230,1.32257,-31.06094,7.30821,1.83087,1.22177,1.04746,0.99569, 0.14940}; 
	//float sequence2[10] = {-29.47844,-4.56199,2.39657,-0.01331,2.17803, -8.47844,4.56099,0.39657,-0.01331,0.04076}; 
	
	// f01 arrete toi
  	float sequence2[20] = {-36.80542,5.88896,1.57628,1.92571,0.85888,1.27760,-1.72685,0.27818,0.60532,0.36723,-1.44883,0.35761,-0.48190,-34.74994,6.50925,2.23733,1.57586,1.22149,0.14397,-0.45760}; 
	
	
	
f1= dtw( 20,20,dim_mfcc,sequence1, sequence2);
 printf("\n d= %f \n",f1);
 /*
 f2= dtw( 14,10,dim_mfcc,sequence3, sequence4);
 printf("\n d= %f \n",f2);*/
/*
  return 0;
}*/



#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h> // for memcmp
#include <stdint.h> // for int16_t and int32_t
#include <math.h>
#include <cstring>
#include <cstdlib>
#include <cstdio>
#include <iostream>
#include <sstream>
#include <limits>
#pragma once
#include "dtw.h"
#include "WavToMfcc.h"
# define NB_MOTS 9

using namespace std;


void parametrisation(char * chemin,float **X_mfcc, int *length_xmfcc){
	
	int i;

	FILE **p_wav = (FILE**)malloc (sizeof(FILE*));
	struct wavfile *p_header = (wavfile*)malloc(sizeof(wavfile)) ;
	int16_t  buffer;
	
	int16_t * xFiltered=(int16_t*)malloc(sizeof(int16_t));
	int  newLength;
	float threshold = 0.0;

	wavRead ( p_wav, chemin, p_header ) ;

	int16_t* result = new int16_t[p_header->totallength];
	int x = 0;
	int cpt = 0;
	
   while(fread(&buffer,sizeof(buffer),1,*p_wav)>0){
	result[x] = buffer;
	cpt++;
	x++;
   }	
   
   //cout<<"file "<<cpt<<endl;
   
   fclose(*p_wav);
	
   removeSilence(result, cpt, &xFiltered, &newLength, threshold);
   computeMFCC(X_mfcc, length_xmfcc, xFiltered, newLength ,p_header->frequency, 512, 256, 13, 20);
	
  

	/*free(p_header);
	free(xFiltered);*/
}

 int main ()
{
	double myinf = std::numeric_limits<double>::infinity();
	float D;
	int motsReconnus;
	std::string vocabulaire [NB_MOTS] ={"_arretetoi.wav", "_avance.wav","_droite.wav", "_etatdurgence.wav", "_faisunflip.wav", "_gauche.wav", "_recule.wav", "_tournedroite.wav", "_tournegauche.wav"};
	int  matriceConfusion[NB_MOTS][NB_MOTS];
	float * distances[NB_MOTS][NB_MOTS];
	char * fichier ="corpus/dronevolant_nonbruite/";
	int indiceMin;
	float distanceMin = myinf ;
	float tauxReco;
	std::string locuteur = "M01" ;
	int nbHyp = 1;
	std::string hypotheses[] = {"M02"};
	//char * chemin="";
	std::string chemin = "corpus/dronevolant_nonbruite/";
   
	
	float * X_mfcc1=(float*)malloc(sizeof(float));
	int length_xmfcc1;
	float *X_mfcc2=(float*)malloc(sizeof(float));
	int length_xmfcc2;
	
	/*
	char * chemin = "corpus/dronevolant_nonbruite/";
	
	int length_xmfcc1;
	parametrisation(chemin,&X_mfcc1,&length_xmfcc1);
	
	
	//chemin = "corpus/dronevolant_nonbruite/"+hypotheses+"_"+"_avance.wav";
	// chemin = "corpus/dronevolant_nonbruite/M02_faisunflip.wav";
	float *X_mfcc2=(float*)malloc(sizeof(float));
	int length_xmfcc2;
	parametrisation(chemin,&X_mfcc2,&length_xmfcc2);

	cout<<"length "<<length_xmfcc1<<endl;
	cout<<"length "<<length_xmfcc2<<endl;*/
	
	for(int i = 0; i < nbHyp; i++){
		motsReconnus = 0;
		matriceConfusion[NB_MOTS][NB_MOTS] = {};
		distances[NB_MOTS][NB_MOTS] = {};
		
		for(int j = 0; j< NB_MOTS; j++){
			chemin = "corpus/dronevolant_nonbruite/";
			chemin += locuteur + vocabulaire[j];
			parametrisation(const_cast<char*>(chemin.c_str()),&X_mfcc1,&length_xmfcc1);
			
			for(int hyp = 0; hyp < NB_MOTS ; hyp ++){
				chemin = "corpus/dronevolant_nonbruite/";
				chemin += hypotheses[i] +  vocabulaire[hyp];
				parametrisation(const_cast<char*>(chemin.c_str()),&X_mfcc2,&length_xmfcc2);
				
				D = dtw(length_xmfcc1, length_xmfcc2, 13, X_mfcc1, X_mfcc2 );
				
				//cout<<"distance "<<D<<endl;
				if (D < distanceMin) {
					distanceMin = D;
					indiceMin = hyp;

				}

			}
	
		matriceConfusion[j][indiceMin] = 1;

		}
		
		//Matrice de confusion
		for(int mot = 0; mot < NB_MOTS; mot++){
			if(matriceConfusion[mot][mot] == 1){
				motsReconnus += 1;
			}
			
		}
		std::cout.setf(std::ios_base::fixed, std::ios_base::floatfield);
		std::cout.precision(2);
		tauxReco = (motsReconnus/(float)NB_MOTS)*100;
		cout<<"Taux de reconnaissance  "<<tauxReco<<endl;
		
	}
	
	
	//D = dtw(length_xmfcc1, length_xmfcc2, 13, X_mfcc1, X_mfcc2 );
	

	//cout <<"\n  d =  "<< D <<"  .\n" ;  
	/*free(X_mfcc1);
	free(X_mfcc2);*/
  return 0;
  
} 









  

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h> // for memcmp
#include <stdint.h> // for int16_t and int32_t
#include <math.h>
#include <iostream>
#pragma once
#include "dtw.h"
#include "WavToMfcc.h"
#define FLOAT_MAX 10000000.0 

using namespace std;

// ----------------------IN---------------OUT---------------OUT----------
void parametrisation (char * chemin, float **X_mfcc, int *length_xmfcc){
	int i,j=0;
	FILE **p_wav = (FILE**)malloc (sizeof(FILE));
	wavfile *p_header = (wavfile*)malloc (sizeof(wavfile));
	int tailleSeq;
	int16_t * xFiltered=(int16_t*)malloc(sizeof(int16_t));
	int  newLength;
	float threshold;
	*X_mfcc = new float[13*(512 + 1)];
	//float *X_mfcc1=(float*)malloc(sizeof(float));
	int length_xmfcc1;
	//cout <<"\n  here " ; 
	wavRead ( p_wav, chemin,p_header ) ;//-----------------------ok compile
	p_header->signal=(int16_t*)malloc(sizeof(int16_t));
	while( fread(p_header->signal,sizeof(int16_t), 1, *p_wav)>0){
		j++;
	}
	 removeSilence(p_header->signal, j, &xFiltered, &newLength, threshold);
	 computeMFCC(X_mfcc, &length_xmfcc1,xFiltered, newLength,p_header->frequency, 512, 256, 13, 20);
	// X_mfcc=&X_mfcc1;
	cout <<length_xmfcc1 <<"-----//////////";
	 length_xmfcc=&length_xmfcc1;
	/*    for (i=0; i < length_xmfcc1; i++){ 
		cout << X_mfcc[i]<<"  " ;
    }*/
}

 int main ()
{
	int i=0;
	float D=0.25;
	char * chemin = "corpus/dronevolant_nonbruite/F02_arretetoi.wav";
	char * chemin2 = "corpus/dronevolant_nonbruite/F01_arretetoi.wav";
	float *X_mfcc1=(float*)malloc(sizeof(float));
	int length_xmfcc1;
	float *X_mfcc2=(float*)malloc(sizeof(float));
	int length_xmfcc2;
	parametrisation(chemin,&X_mfcc1,&length_xmfcc1);
	parametrisation(chemin,&X_mfcc2,&length_xmfcc2);
D= dtw(length_xmfcc1, length_xmfcc2, 13, X_mfcc1, X_mfcc2);

 cout <<"------------"<< length_xmfcc1 <<"-------------------------------------------------" ;  
   for (i=0; i < length_xmfcc1; i++){ 
		cout << X_mfcc1[i]<<"  " ;
    }
	
	cout <<"\n  d=  "<< D <<"  .\n" ;  
  return 0;
  /*copute
  dtw*/
  
} 









  
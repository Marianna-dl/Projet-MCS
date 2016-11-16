
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


void parametrisation(char * chemin,float **X_mfcc, int *length_xmfcc ){
	
	int i;

	FILE **p_wav = (FILE**)malloc (sizeof(FILE*));
	struct wavfile *p_header = (wavfile*)malloc(sizeof(wavfile)) ;
	int16_t  buffer;
	
	int16_t * xFiltered=(int16_t*)malloc(sizeof(int16_t));
	int  newLength;
	float threshold = 0.0;


	wavRead ( p_wav, chemin, p_header ) ;//-----------------------ok compile

	int16_t* result = new int16_t[p_header->totallength];
	int x = 0;
	int cpt = 0;
	std::cout.setf(std::ios_base::fixed, std::ios_base::floatfield);
	std::cout.precision(5);
	
   while(fread(&buffer,sizeof(buffer),1,*p_wav)>0){
	result[x] = buffer;
	cpt++;
	x++;
   }	
   cout<<"\n"<<cpt<<"\n";
   fclose(*p_wav);
	
   removeSilence(result, cpt, &xFiltered, &newLength, threshold);
   
   cout<<newLength<<"\n";
   
   float *X_mfcc1=(float*)malloc(sizeof(float));
   int length_xmfcc1;
   
   computeMFCC(&X_mfcc1, &length_xmfcc1, xFiltered, newLength ,p_header->frequency, 512, 256, 13, 20);
   
   cout<<length_xmfcc1<<"\n";
	
}

 int main ()
{
	int i=0;
	float D=0.25;
	
	char * chemin = "corpus/dronevolant_nonbruite/F02_arretetoi.wav";
	float *X_mfcc1=(float*)malloc(sizeof(float));
	int length_xmfcc1;
	parametrisation(chemin,&X_mfcc1,&length_xmfcc1);
	
	char * chemin2 = "corpus/dronevolant_nonbruite/F01_arretetoi.wav";
	float *X_mfcc2=(float*)malloc(sizeof(float));
	int length_xmfcc2;
	parametrisation(chemin,&X_mfcc2,&length_xmfcc2);
	
/*
	/*parametrisation(chemin,&X_mfcc2,&length_xmfcc2);
D= dtw(length_xmfcc1, length_xmfcc2, 13, X_mfcc1, X_mfcc2);

 cout <<"------------"<< length_xmfcc1 <<"-------------------------------------------------" ;  
   for (i=0; i < length_xmfcc1; i++){ 
		cout << X_mfcc1[i]<<"  " ;
    }
	
	cout <<"\n  d=  "<< D <<"  .\n" ;  */
	


  return 0;
  /*copute
  dtw*/
  
} 









  
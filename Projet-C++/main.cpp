
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
   
   cout<<"file "<<cpt<<endl;
   
   fclose(*p_wav);
	
   removeSilence(result, cpt, &xFiltered, &newLength, threshold);
   computeMFCC(X_mfcc, length_xmfcc, xFiltered, newLength ,p_header->frequency, 512, 256, 13, 20);
	
  

	/*free(p_header);
	free(xFiltered);*/
}

 int main ()
{
	
	float D = 0;
	
	char * chemin = "corpus/dronevolant_nonbruite/M01_arretetoi.wav";
	float *X_mfcc1=(float*)malloc(sizeof(float));
	int length_xmfcc1;
	parametrisation(chemin,&X_mfcc1,&length_xmfcc1);
	
	
	chemin = "corpus/dronevolant_nonbruite/M02_faisunflip.wav";
	// chemin = "corpus/dronevolant_nonbruite/M02_faisunflip.wav";
	float *X_mfcc2=(float*)malloc(sizeof(float));
	int length_xmfcc2;
	parametrisation(chemin,&X_mfcc2,&length_xmfcc2);

	cout<<"length "<<length_xmfcc1<<endl;
	cout<<"length "<<length_xmfcc2<<endl;
	
	/*cout<<endl;
	cout<<"{";
	for(int i = 0; i <length_xmfcc1; i++){
		cout<<X_mfcc1[i]<<",";
	}
	
	cout<<endl;
	cout<<"{";
	for(int i = 0; i <length_xmfcc2; i++){
		cout<<X_mfcc2[i]<<",";
	}*/
	
	D = dtw(length_xmfcc1, length_xmfcc2, 13, X_mfcc1, X_mfcc2 );

	cout <<"\n  d =  "<< D <<"  .\n" ;  
	/*free(X_mfcc1);
	free(X_mfcc2);*/
  return 0;
  
} 









  

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

 int main ()
{
int i;
int j=0;

char * chemin = "corpus/dronevolant_nonbruite/F02_arretetoi.wav";
FILE **p_wav = (FILE**)malloc (sizeof(FILE*));
struct wavfile *p_header ;
int tailleSeq;
int16_t  buffer;


	wavRead ( p_wav, chemin,p_header ) ;//-----------------------ok compile

	int16_t* result = new int16_t[p_header->totallength];
	int x = 0;
	int cpt = 0;
			std::cout.setf(std::ios_base::fixed, std::ios_base::floatfield);
		std::cout.precision(5);
   while(fread(&buffer,sizeof(buffer),1,*p_wav)>0){
	result[x] = buffer;
	cpt++;
	if(x<30){

		cout << result[x]<<" \n" ;
		
	}
	x++;
   }
   
  // cout << " \n"<<cpt<<" " ;
   
   /*for(x = 0; x<20;x++){
	   
	   cout << result1[x]<<" " ;
   }*/
   
     /* for(x = 0; x<20;x++){
	   
	   cout << result<<" " ;
   }*/
   
   
    
   
    //fermeture du fichier 
    fclose(*p_wav);  
 cout << "\n" ;
/*void removeSilence(int16_t * x, int Nx, int16_t ** xFiltered, int * newLength, float threshold)*/
 //removeSilence(, int Nx, int16_t ** xFiltered, int * newLength, float threshold)
// execv("cat","cat",p_wav,NULL);
//  system ("cat corpus/dronevolant_nonbruite/F01_arretetoi.wav");
// cout("\n d= %d \n",tailleSeq);
   /* for (i=0; i < j; i++){ 
		cout << p_header->signal[i]<<" " ;
    }*/
   /*cout << "taille effective " << j << "taille total"<<p_header->totallength ;*/
// printf("\n byt pour Mr chiant in data= %d \n",p_header->bytes_in_data);
  return 0;
  /*copute
  dtw*/
  
} 









  
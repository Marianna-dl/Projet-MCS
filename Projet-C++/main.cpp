
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
//voi wavRead(FILE **p_wav, char *filename, wavfile *p_header);
char * chemin = "corpus/dronevolant_nonbruite/F02_arretetoi.wav";
FILE **p_wav = (FILE**)malloc (sizeof(FILE));
wavfile *p_header = (wavfile*)malloc (sizeof(wavfile));
int tailleSeq;

wavRead ( p_wav, chemin,p_header ) ;//-----------------------ok compile

p_header->signal=(int16_t*)malloc(sizeof(int16_t));

//tailleSeq = p_header->totallength; 

    //ouverture/lecture du fichier wave_file en mode binaire (rb) : lecture en octets
//    file = fopen(*p_wav, "rb");
  //remplissage du tableau d'échantillons data[] de la structure WAVE 
 //   for (i=0; i < (int)p_header->totallength; i++){ 
	while( fread(p_header->signal, p_header->bits_per_sample, 1, *p_wav)==1){
		j++;
	}
       
    
   
    //fermeture du fichier 
    fclose(*p_wav);  

/*void removeSilence(int16_t * x, int Nx, int16_t ** xFiltered, int * newLength, float threshold)*/
 //removeSilence(, int Nx, int16_t ** xFiltered, int * newLength, float threshold)
// execv("cat","cat",p_wav,NULL);
//  system ("cat corpus/dronevolant_nonbruite/F01_arretetoi.wav");
// cout("\n d= %d \n",tailleSeq);
    for (i=0; i < j; i++){ 
		cout << p_header->signal[i]<<" " ;
    }
   cout << "taille effective " << j << "taille total"<<p_header->totallength ;
// printf("\n byt pour Mr chiant in data= %d \n",p_header->bytes_in_data);
  return 0;
  /*copute
  dtw*/
  
} 









  
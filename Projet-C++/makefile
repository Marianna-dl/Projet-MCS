all: dtw 

dtw: main.cpp dtw.cpp libmfccOptim.cpp WavToMfcc.cpp FFTReal.cpp
	g++ main.cpp dtw.cpp libmfccOptim.cpp WavToMfcc.cpp FFTReal.cpp -o dtw
	
	
clean:
	rm *.o


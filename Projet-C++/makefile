all: dtw 

dtw: main.cpp dtw.cpp libmfccOptim.cpp WavToMfcc.cpp FFTReal.cpp
	g++ -w main.cpp dtw.cpp libmfccOptim.cpp WavToMfcc.cpp FFTReal.cpp -o dtw
	
	
clean:
	rm *.o


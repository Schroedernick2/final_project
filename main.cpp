#include <iostream>
#include <stdlib.h>
#include <limits>

//Platform == 0 --> Windows
//Platform == 1 --> Linux (for nick to use this shit)
//Platform == -1 --> other (unsupported)

//compile for windows: x86_64-w64-mingw32-g++ -static-libgcc -static-libstdc++ main.cpp -o windows_executable.exe

#if defined(_WIN32) || defined(_WIN64)
	#define PLATFORM 0
#elif defined(__linux__)
	#define PLATFORM 1
#else
	#define PLATFORM -1
#endif

using namespace std;

int check_platform(){
	switch(PLATFORM){
		case 0:
			cout << "Windows platform detected." << endl;
			return 0;
		case 1:
			cout << "Linux platform detected." << endl;
			return 1;
		default:
			cout << "Unsupported platform detected.\nExiting now..." << endl;
			return -1;	
	}
}

int main(int argc, char** argv){
	if(check_platform() == -1)
		return EXIT_FAILURE;

	switch(PLATFORM){
		case 1:
			system("rm xml/*");
			system("gnome-terminal -- bash -c \"exec java -jar TrainModel/dist/TrainModel.jar && read\"");
			system("gnome-terminal -- bash -c \"exec java -jar CTCOffice/CTC.jar && read\"");
			system("gnome-terminal -- bash -c \"exec python3 TrackController/TrackController.py && read\"");
			system("gnome-terminal -- bash -c \"exec python3 TrackModel/trackmodel.py && read\"");
			system("gnome-terminal -- bash -c \"exec java -jar TC/dist/TrainController.jar && read\"");
				
			break;
		case 0:
			system("del /Q xml\\*");
			system("start cmd /k java -jar TrainModel/dist/TrainModel.jar");
			system("start cmd /k java -jar CTCOffice/CTC.jar");
			system("start cmd /k TrackController\\TrackController.py");
			system("start cmd /k TrackModel\\trackmodel.py");
			system("start cmd /k java -jar TC/dist/TrainController.jar");
				
			break;
		default:
			cout << "module selection error\nExiting program..." << endl;
			return EXIT_FAILURE;
	}

	return EXIT_SUCCESS;
}


#include <iostream>
#include <cstdlib>
#include <limits>

//Platform == 0 --> Windows
//Platform == 1 --> Linux (for nick to use this shit)
//Platform == -1 --> other (unsupported)

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

void print_choices(){
	cout << "Run a Module: " << endl;
	cout << "\t0: Train Model\n";
	cout << "\t1: CTC\n";
	cout << "\t2: Track Controller\n";
	cout << "\t3: Track Model\n";
	cout << "\t4: Train Controller\n";
	cout << "Make selection: ";
}

int get_selection(){
	int select = 0;
	
	print_choices();

	cin >> select;

	while(select < 0 || select > 4 || cin.fail()){
    		cin.clear();
    		cin.ignore(numeric_limits<streamsize>::max(), '\n');

		print_choices();
		cin >> select;
	}

	return select;
}

int main(int argc, char** argv){
	if(check_platform() == -1)
		return EXIT_FAILURE;
	int again = 0;

	while(again != -1){
		switch(get_selection()){
			case 0:
				if(PLATFORM == 1)
					system("gnome-terminal -- bash -c \"java -jar TrainModel/dist/TrainModel.jar && read\"");
				else{}
					//find windows cmd
				break;
			case 1:
				if(PLATFORM == 1)
					system("gnome-terminal -- bash -c \"java -jar CTCOffice/dist/TrainModel.jar && read\"");
				else{}
					//find windows cmd
				break;
			case 2:
				if(PLATFORM == 1)
					system("gnome-terminal -- bash -c \"python TrackController/TrackController.py && read\"");
				else{}
					//find windows cmd
				break;
			case 3:
				if(PLATFORM == 1)
					system("gnome-terminal -- bash -c \"python TrackModel/trackmodel.py && read\"");
				else{}
					//find windows cmd
				break;
			case 4:
				if(PLATFORM == 1)
					system("gnome-terminal -- bash -c \"java -jar TC/dist/TC.jar && read\"");
				else{}
					//find windows cmd
				break;
			default:
				return EXIT_FAILURE;
		}

		cout << "Make selection (-1 to leave): ";
		cin >> again;

		while(again < -1 || again > 4 || cin.fail()){
    			cin.clear();
    			cin.ignore(numeric_limits<streamsize>::max(), '\n');

			cout << "Make selection (-1 to leave): ";
			cin >> again;
		}
	}

	return EXIT_SUCCESS;
}


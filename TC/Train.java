package traincontroller;

import java.util.*;

public class Train{
    //ID -- Line + train number
    private String trainID;
    
    //brakes and failure states
    private boolean emergencyBrake;
    private boolean serviceBrake;
    private boolean engineFailure;
    private boolean signalFailure;
    private boolean brakeFailure;
   
    
    //commanded values
    private double SuggestedSpeed;
    private double ActualSpeed;
    private double authority;
    
    //previous values for calculating power
    private double prevPower;
    private double prevError;
    private double prevMew;
    
    //current train description info
    private double power;

    private String station;
    private boolean leftDoors;
    private boolean rightDoors;
    private int temperature;
    private boolean advertisements;
    private boolean headlights;
    private boolean lights;
    private double creationTime;
    private double time;
    private double samplePeriod;
    
    //direction of train
    private boolean forward;
    private String announcementsText;
    
    //other
    //private double force;
    //private double accelerationLimit;
    //private double decelerationLimit;
    
    
    //constant values
    //private final int CAR_COUNT = 1;
    //private final int WEIGHT_PER_PERSON = 150; //lbs
    //private final double TRAIN_LENGTH = 32.2; //m
    //private final double TRAIN_HEIGHT = 3.42; //m
    //private final double TRAIN_WIDTH = 2.65; //m
    //private final double TRAIN_MASS = 40.9; //t
    private final double MAX_SPEED = 70; //km/h
    private double Ki = 0.001;
    private double Kp = 0.5;
    //private final int MAX_CAPACITY = 222; 
    //private final double MEDIUM_ACCELERATION = 0.5; //m/s^2
    //private final double SERVICE_DECELERATION = -1.2; //m/s^2
    //private final double EMERGENCY_DECELERATION = -2.73; //m/s^2
    //private final double GRAVITY = 9.8; //m/s^2
    private final double ENGINE_POWER = 120; //kw
    private final double SPEED_LIMIT = 70; //km/h
    //private final double COEFFICIENT_OF_FRICTION = 0.0003;
    
    //constant conversion values
    //private final double KW_TO_NMS = 1000; 
    //private final double TONS_TO_POUNDS = 2000;
    //private final double METERS_TO_FEET = 3.28084;
    //private final double METERS_TO_MILES = 0.000621371;
    private final double KM_TO_MILES = 0.621371;
    //private final double MPH_TO_FPS = 1.46667;
    //private final double MPH_TO_MPS = 0.44704; //miles per hour to meters per second
    
    /*******CONSTRUCTORS*******/
    
    public Train(String line){
        //set train id
        this.trainID = line;
        
        //default state
        this.emergencyBrake = false;
        this.serviceBrake = false;
        this.engineFailure = false;
        this.signalFailure = false;
        this.brakeFailure = false;
        
        //dimensions
       // this.height = Math.round(TRAIN_HEIGHT*METERS_TO_FEET*100.0)/100.0;
        //this.width = Math.round(TRAIN_WIDTH*METERS_TO_FEET*100.0)/100.0;
        //this.length = Math.round(TRAIN_LENGTH*METERS_TO_FEET*100.0)/100.0;
        //this.mass = Math.round((TRAIN_MASS*TONS_TO_POUNDS)+(WEIGHT_PER_PERSON*(crewCount+passCount))*100.0)/100.0;
        
        //current info
        this.temperature = 70;
        this.station = "NEXT_STATION";
        this.lights = false;
        this.headlights = false;
        this.rightDoors = false;
        this.leftDoors = false;
        this.advertisements = true;
        this.announcementsText = "";
        //this.passengerCount = passCount;
        //this.crewCount = crewCount;
        this.power = 0;
        this.ActualSpeed = 0;
        this.prevError = 0;
        this.prevPower = 0;
        this.prevMew = 0;
        //this.velocity = 0;
        //this.acceleration = 0;
        //this.grade = 0;
        //this.elevation = 0;
        this.samplePeriod = 1;
        this.creationTime = System.currentTimeMillis();
        
        //other
        //this.force = 0;
        //this.decelerationLimit = 0;
        //this.accelerationLimit = MEDIUM_ACCELERATION * METERS_TO_FEET;
    }
    
    /*******GETTERS********/
    
    //ID getter
    public String getTrainID(){ return trainID; }
    
    //brake and failure getters
    public boolean isEmergencyBrake(){ return emergencyBrake; }
    public boolean isServiceBrake(){ return serviceBrake; }
    public boolean isEngineFailure(){ return engineFailure; }
    public boolean isSignalFailure(){ return signalFailure; }
    public boolean isBrakeFailure(){ return brakeFailure; }
  
    
    //commanded values getters
    public double getAuthority(){ return authority; }
    public double getSuggestedSpeed(){ return SuggestedSpeed; }
    
    //current train description getters
    public double getTime(){ 
        double currTime = System.currentTimeMillis();
        
        return Math.round((currTime-this.creationTime)/1000); 
    }
    public double getPower(){ return power; }
    public double getActualSpeed(){ return ActualSpeed; }
    public double getKi(){ return Ki; }
    public double getKp(){ return Kp; }
    public double getSamplePeriod(){ return samplePeriod; }
    public String getAnnouncementsText(){return announcementsText;}
    //public double getForce(){ return force; }
    //public double getVelocity(){ return velocity; }
    //public double getAcceleration(){ return acceleration; }
    //public double getGrade(){ return grade; }
    //public int getElevation(){ return elevation; }
    public String getStation(){ return station; }
    //public int getPassengerCount(){ return passengerCount; }
    //public int getCrewCount(){ return crewCount; }
    public boolean isLeftDoors(){ return leftDoors; }
    public boolean isRightDoors(){ return rightDoors; }
    public int getTemperature(){ return temperature; }
    public boolean isAdvertisements(){ return advertisements; }
    public boolean isLights(){ return lights; }
    public boolean isHeadlights(){ return headlights; }

    
    /*******SETTERS*******/
    
    //brake and failure setters
    public void setEmergencyBrake(boolean state){ emergencyBrake = state; }
    public void setServiceBrake(boolean state){ serviceBrake = state; }
    public void setEngineFailure(boolean state){ 
        if(!state){setEmergencyBrake(false);}
        else {
            setPower(0);             // send P = 0 to TM
            setEmergencyBrake(true); // send EB sig to TM
        }
        engineFailure = state; }
    public void setSignalFailure(boolean state){ 
        if(!state){setEmergencyBrake(false);}
        else {
            setPower(0);             // send P = 0 to TM
            setEmergencyBrake(true); // send EB sig to TM
        }
        signalFailure = state; }
    public void setBrakeFailure(boolean state){ 
        if(!state){setEmergencyBrake(false);}
        else {
            setPower(0);             // send P = 0 to TM
            setEmergencyBrake(true); // send EB sig to TM
        }
        brakeFailure = state; 
    }
    
    //command value setters
    public void setActualSpeed(double speed){ this.ActualSpeed = speed;}
    public void setSpeed(double speed){ this.SuggestedSpeed = speed; }
    public void setAuthority(double authority){ this.authority = authority; }
    
    //other setters
    public void setPower(double power){ this.power = power; }
    public void setKi(double i){ this.Ki = i; }
    public void setKp(double i){ this.Kp = i; }
    //public void setGrade(double grade){ this.grade = grade; }
    //public void setElevation(int elevation){ this.elevation = elevation; }
    public void setStation(String station){ this.station = station; }
    public void setAnnouncementsText(String a){this.announcementsText = a;}
    //public void setPassengerCount(int passengerCount){ this.passengerCount = passengerCount;}
    //public void setCrewCount(int crewCount){ this.crewCount = crewCount; }
    public void setLeftDoors(boolean state){ leftDoors = state; }
    public void setRightDoors(boolean state){ rightDoors = state; }
    public void setTemperature(int temp){ temperature = temp; }
    public void setAdvertisements(boolean state){ advertisements = state; }
    public void setLights(boolean state){ lights = state; }   
    public void setHeadlights(boolean state){ headlights = state; }
    
    /*******MEMBER FUNCTIONS*******/
    
   
    
    public void updatePower(){
               
        double currentVelocity;
        
        if(brakeFailure || signalFailure || engineFailure){
            // failure mode activated, use Emergency Brake, no Power
            this.power = -5;
        }
        else{
            // add condition for activating servicce brake ..
            // if()
            
            //speed at time of function call
            currentVelocity = ActualSpeed; //mph
            //get speed to be achieved
            double commandedVelocity = SuggestedSpeed; //mph 
            
            double c = (Math.pow(currentVelocity,2))*.000088;
            double b = authority / 1760;
            if(emergencyBrake) //IF EB active, outside of failure mode
                setPower(-5);
            else if(serviceBrake)
                setPower(-1);
            else if((b <= c) && currentVelocity > 1){
                setPower(-1);
                setServiceBrake(true);
            }
            else if((b <= c) && currentVelocity <= 1){
                setPower(0);
                setServiceBrake(false);                
            }
            else{
                //error check for a value too high
                if(commandedVelocity > MAX_SPEED*KM_TO_MILES) commandedVelocity = MAX_SPEED*KM_TO_MILES;
                double sampleError = commandedVelocity - currentVelocity;
                //Calculate power from Vcurr and Vcmd. 
                //Calculate Power method 1
                if(power < ENGINE_POWER)
                    prevMew = prevMew + (samplePeriod/2)*(prevError + sampleError);
                //^else prevMew = prevMew; using 'prevmew' as current mew after, will become next times 'prevmew'
                double P1 = (Kp*sampleError) + (Ki*prevMew) ;
                P1 = (double) Math.round(P1 * 10) / 10; // round to 2 decimal places for comparison
                // Calculate Power method 2
                if(power < ENGINE_POWER)
                    prevMew = prevMew + (samplePeriod*.5)/(1/(prevError + sampleError));
                double P2 = (Kp/(1/sampleError)) + (Ki/(1/prevMew));
                P2 = (double) Math.round(P2 * 10) / 10;
            
                // Calculate Power method 2
                if( power < ENGINE_POWER)
                   prevMew = prevMew + ((samplePeriod/2)*(prevError - (-sampleError)));
                double P3 = (Kp/(1/sampleError)) + (Ki/(1/prevMew));
                P3 = (double) Math.round(P3 * 10) / 10;
                double P=0;
                if(P1 == P2) P = P1; 
                else if(P2 == P3) P = P2;
                else if(P1 == P3) P = P1;
                else {
                    announcementsText =  new String(announcementsText+ "Power not 2/3 \n");
                    return;
                }
                System.out.println(P1);
                System.out.println(P2);
                System.out.println(P3);

                prevError = sampleError;
                if(P <= ENGINE_POWER)
                    setPower(P);
                else if(P > 120)
                    setPower(120);                
                // else its bugging out!
            
                
                
            }          
        }
    }
}

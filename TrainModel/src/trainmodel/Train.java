package trainmodel;

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
    
    //dimensions
    private double height;
    private double width;
    private double length;
    private double mass;
    
    //commanded values
    private double speed;
    private double authority;
    
    //current train description info
    private double power;
    private double velocity;    //actual velocity, returned to train controller
    private double acceleration;
    private double grade;
    private int elevation;
    private String station;
    private int passengerCount;
    private int crewCount;
    private boolean leftDoors;
    private boolean rightDoors;
    private int temperature;
    private boolean advertisements;
    private boolean lights;
    private double creationTime;
    private double time;
    private double distance;
    
    //direction of train
    private boolean forward;
    
    //other
    private double force;
    private double accelerationLimit;
    private double decelerationLimit;
    private ArrayList<String> stops;
    
    
    //constant values
    private final int CAR_COUNT = 1;
    private final int WEIGHT_PER_PERSON = 150; //lbs
    private final double TRAIN_LENGTH = 32.2; //m
    private final double TRAIN_HEIGHT = 3.42; //m
    private final double TRAIN_WIDTH = 2.65; //m
    private final double TRAIN_MASS = 40.9; //t
    private final double MAX_SPEED = 70; //km/h
    private final int MAX_CAPACITY = 222; 
    private final double MEDIUM_ACCELERATION = 0.5; //m/s^2
    private final double SERVICE_DECELERATION = -1.2; //m/s^2
    private final double EMERGENCY_DECELERATION = -2.73; //m/s^2
    private final double GRAVITY = 9.8; //m/s^2
    private final double ENGINE_POWER = 120; //kw
    private final double SPEED_LIMIT = 70; //km/h
    private final double COEFFICIENT_OF_FRICTION = 0.0003;
    
    //constant conversion values
    private final double KW_TO_NMS = 1000; 
    private final double TONS_TO_POUNDS = 2000;
    private final double METERS_TO_FEET = 3.28084;
    private final double METERS_TO_MILES = 0.000621371;
    private final double KM_TO_MILES = 0.621371;
    private final double MPH_TO_FPS = 1.46667;
    private final double MPH_TO_MPS = 0.44704; //miles per hour to meters per second
    
    /*******CONSTRUCTORS*******/
    
    public Train(String line,int trainNumber,int crewCount,int passCount,ArrayList<String> stops){
        //set train id
        this.trainID = line.toUpperCase()+"_"+trainNumber;
        
        this.stops = stops;
        
        //default state
        this.emergencyBrake = false;
        this.serviceBrake = false;
        this.engineFailure = false;
        this.signalFailure = false;
        this.brakeFailure = false;
        
        //dimensions
        this.height = Math.round(TRAIN_HEIGHT*METERS_TO_FEET*100.0)/100.0;
        this.width = Math.round(TRAIN_WIDTH*METERS_TO_FEET*100.0)/100.0;
        this.length = Math.round(TRAIN_LENGTH*METERS_TO_FEET*100.0)/100.0;
        this.mass = Math.round((TRAIN_MASS*TONS_TO_POUNDS)+(WEIGHT_PER_PERSON*(crewCount+passCount))*100.0)/100.0;
        
        //current info
        this.temperature = 70;
        this.station = "NEXT_STATION";
        this.lights = false;
        this.rightDoors = false;
        this.leftDoors = false;
        this.advertisements = true;
        this.passengerCount = passCount;
        this.crewCount = crewCount;
        this.power = 0;
        this.velocity = 0;
        this.acceleration = 0;
        this.grade = 0;
        this.elevation = 0;
        this.creationTime = System.currentTimeMillis();
        
        //other
        this.force = 0;
        this.decelerationLimit = 0;
        this.accelerationLimit = MEDIUM_ACCELERATION * METERS_TO_FEET;
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
    
    //dimension getters
    public double getHeight(){ return height; }
    public double getWidth(){ return width; }
    public double getLength(){ return length; }
    public double getMass(){ return mass; }
    
    //commanded values getters
    public double getSpeed(){ return speed; }
    public double getAuthority(){ return authority; }
    
    //current train description getters
    public double getTime(int multiplier){ 
        double currTime = System.currentTimeMillis();
        
        return Math.round((currTime-this.creationTime)/1000)*multiplier; 
    }
    public double getDistance(){ return distance; }
    public double getPower(){ return power; }
    public double getForce(){ return force; }
    public double getVelocity(){ return velocity; }
    public double getAcceleration(){ return acceleration; }
    public double getGrade(){ return grade; }
    public int getElevation(){ return elevation; }
    public String getStation(){ return station; }
    public int getPassengerCount(){ return passengerCount; }
    public int getCrewCount(){ return crewCount; }
    public boolean isLeftDoors(){ return leftDoors; }
    public boolean isRightDoors(){ return rightDoors; }
    public int getTemperature(){ return temperature; }
    public boolean isAdvertisements(){ return advertisements; }
    public boolean isLights(){ return lights; }
    
    /*******SETTERS*******/
    
    //brake and failure setters
    public void setEmergencyBrake(boolean state){ emergencyBrake = state; }
    public void setServiceBrake(boolean state){ serviceBrake = state; }
    public void setEngineFailure(boolean state){ engineFailure = state; }
    public void setSignalFailure(boolean state){ signalFailure = state; }
    public void setBrakeFailure(boolean state){ brakeFailure = state; }
    
    //command value setters
    public void setSpeed(double speed){ this.speed = speed; }
    public void setAuthority(double authority){ this.authority = authority; }
    
    //other setters
    public void setPower(double power){ this.power = power; }
    public void setGrade(double grade){ this.grade = grade; }
    public void setElevation(int elevation){ this.elevation = elevation; }
    public void setStation(String station){ this.station = station; }
    public void setPassengerCount(int passengerCount){ this.passengerCount = passengerCount;}
    public void setCrewCount(int crewCount){ this.crewCount = crewCount; }
    public void setLeftDoors(boolean state){ leftDoors = state; }
    public void setRightDoors(boolean state){ rightDoors = state; }
    public void setTemperature(int temp){ temperature = temp; }
    public void setAdvertisements(boolean state){ advertisements = state; }
    public void setLights(boolean state){ lights = state; }   
    
    /*******MEMBER FUNCTIONS*******/
    
    //function to update mass.. should be used when passenger/crew count changes
    private void updateMass(){
        this.mass = Math.round(((TRAIN_MASS*TONS_TO_POUNDS)+(WEIGHT_PER_PERSON*(this.crewCount+this.passengerCount)))*100.0)/100.0;
    }
    public void updateVelocity(){
        //update mass just in case
        this.updateMass();
        
        //speed at time of function call
        double currentVelocity = this.velocity; //mph
        
        //calculate force
            double forceDown = (this.mass)*GRAVITY*(Math.cos(Math.toDegrees(Math.atan(grade/100.0)))*Math.PI/180);
            
            if(this.power > ENGINE_POWER){
                this.power = ENGINE_POWER;
            }
            if(this.power < 0 && !this.emergencyBrake)
                this.serviceBrake = true;
            else
                this.serviceBrake = false;
            
            double forceFromEng; 
            if(currentVelocity==0)
                forceFromEng = (this.power*KW_TO_NMS)/1; 
            else
                forceFromEng = (this.power*KW_TO_NMS)/(currentVelocity*MPH_TO_MPS);
         
            double forceNormal = (this.mass)*GRAVITY*(Math.sin(Math.toDegrees(Math.atan(grade/100.0)))*Math.PI/180);
            double forceFriction = forceNormal + (COEFFICIENT_OF_FRICTION*forceDown);
            
            this.force = Math.round((forceFromEng - forceFriction)*100.0)/100.0;
        
        //calculate acceleration
            //accel = force/mass
            this.acceleration = Math.round((this.force / this.mass)*100.0)/100.0;
            //if accel > limit, accel = limit
            if(this.acceleration > this.accelerationLimit)
                this.acceleration = this.accelerationLimit;
            
            //check if braking
            if(this.emergencyBrake || this.serviceBrake){
                if(this.serviceBrake && !this.emergencyBrake)
                    this.acceleration += SERVICE_DECELERATION;
                else
                    this.acceleration += EMERGENCY_DECELERATION;
            }
            this.acceleration = Math.round(this.acceleration*METERS_TO_FEET*100.0)/100.0;
        //calculate velocity
            this.velocity = currentVelocity/KM_TO_MILES + this.acceleration/MPH_TO_FPS;
            if(this.velocity <= 0){
                this.velocity = 0;
                this.acceleration = 0;
            }
            if(this.velocity > SPEED_LIMIT){ 
                this.velocity = SPEED_LIMIT; 
                this.acceleration = 0;
            }
            this.velocity = Math.round(this.velocity*KM_TO_MILES*100.0)/100.0;
            
            //calculate distance
            this.distance = Math.round((this.distance+(this.velocity/3600))*100.0)/100.0; 
        }
}
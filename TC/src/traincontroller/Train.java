package traincontroller;

import java.util.*;

public class Train{
    /*ID -- Line + train number*/
    private String trainID;
    
    /*brakes and failure states */
    private boolean emergencyBrake;
    private boolean serviceBrake;
    private boolean engineFailure;
    private boolean signalFailure;
    private boolean brakeFailure;
    private boolean passengerBrake;
    private boolean manuellySet;
   
    
    /* commanded values */
    private double speedLimit;
    private double suggestedSpeed;
    private double actualSpeed;
    private double authority;
    private double stationAuthority; 
    private double blockDistanceTraveled; 
    /* ^ for calculating granular authority */
    
    /* previous values for calculating power */
    private double prevPower;
    private double prevError;
    private double prevMew;
  
    /* current train description info */
    private double power;
    private String scheduledStation;
    private String station;
    private boolean leftDoors;
    private boolean rightDoors;
    private int temperature;
    private boolean advertisements;
    private boolean headlights;
    private boolean lights;
    private double creationTime;
    private double time;
    private String announcementsText;
    private double Ki = 150;
    private double Kp = 250;
    
    /* Constants */
    private final double MAX_SPEED = 70; //km/h
    private final double ENGINE_POWER = 120; //kw
    private final double KM_TO_MILES = 0.621371;
    private final double MPH_TO_MPS = 0.44704;
    
    
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
        this.passengerBrake = false;
        this.manuellySet = false;
        this.temperature = 70;
        this.station = "NEXT_STATION";
        this.scheduledStation = "nothing_yet";
        this.lights = false;
        this.headlights = false;
        this.rightDoors = false;
        this.leftDoors = false;
        this.advertisements = false;
        this.announcementsText = "";
        this.power = 0;
        this.suggestedSpeed = 0;
        this.actualSpeed = 0;
        this.authority = 1000; /*random high val for init, will read asap */
        this.stationAuthority = 1000;
        this.blockDistanceTraveled = 0;
        this.prevError = 0;
        this.prevPower = 0;
        this.prevMew = 0;
        this.creationTime = System.currentTimeMillis();
    }
    
    /*******GETTERS********/
    
    /* ID getter */
    public String getTrainID(){ return trainID; }
    
    /* brake and failure getters */
    public boolean isEmergencyBrake(){ return emergencyBrake; }
    public boolean isServiceBrake(){ return serviceBrake; }
    public boolean isEngineFailure(){ return engineFailure; }
    public boolean isSignalFailure(){ return signalFailure; }
    public boolean isBrakeFailure(){ return brakeFailure; }
    public boolean isPassBrake(){ return passengerBrake;}
    public boolean isManuel(){ return manuellySet; }
    
    /*commanded values getters */
    public double getAuthority(){ return authority; }
    public double getStationAuthority(){ return stationAuthority; }
    public double getBlockDistanceTraveled(){ return blockDistanceTraveled; }
    public double getSuggestedSpeed(){ return suggestedSpeed; }
    public double getSpeedLimit(){return speedLimit;}
    
    /* current train description getters */
    public double getTime(){ 
        double currTime = System.currentTimeMillis();
        return Math.round((currTime-this.creationTime)/1000); 
    }
    public double getPower(){ return power; }
    public double getActualSpeed(){ return actualSpeed; }
    public double getKi(){ return Ki; }
    public double getKp(){ return Kp; }
    public String getAnnouncementsText(){return announcementsText;}
    public String getStation(){ return station; }
    public boolean isNextStation(){
        station = getStation();
        return station.toUpperCase().equals(scheduledStation.toUpperCase());
    }
    public boolean isLeftDoors(){ return leftDoors; }
    public boolean isRightDoors(){ return rightDoors; }
    public int getTemperature(){ return temperature; }
    public boolean isAdvertisements(){ return advertisements; }
    public boolean isLights(){ return lights; }
    public boolean isHeadlights(){ return headlights; }

    
    /*******SETTERS*******/
    
    //brake and failure setters
    public void setEmergencyBrake(boolean state){
        emergencyBrake = state;
        System.out.println( trainID + state);
    }
    public void setServiceBrake(boolean state){ serviceBrake = state; }
    public void setEngineFailure(boolean state){ engineFailure = state; }
    public void setSignalFailure(boolean state){ signalFailure = state; }
    public void setBrakeFailure(boolean state){ brakeFailure = state; }
    public void setPassBrake(boolean state){ passengerBrake = state;}
    public void setManuel(boolean state){ manuellySet = state; }
    
    /* command value setters */
    public void setActualSpeed(double speed){ this.actualSpeed = speed;}
    public void setSpeed(double speed){ this.suggestedSpeed = speed; }
    public void setAuthority(double authority){ this.authority = authority; }
    public void setStationAuthority(double sa){ this.stationAuthority = sa; }
    public void setSpeedLimit(double sl){ this.speedLimit = sl; }    
    public void setBlockDistanceTraveled(double bdt) { this.blockDistanceTraveled = bdt; }
    public void setPower(double power){ this.power = power; }
    public void setKi(double i){ 
        if(i>=0){
            this.Ki = i; 
        }
    }
    public void setKp(double p){ 
        if(p>=0){
            this.Kp = p; 
        }
    }
    public void setStation(String station){ this.station = station; }
    public void setNextStation(String station){ this.scheduledStation = station; }
    public void setAnnouncementsText(String a){this.announcementsText = a;}
    public void setLeftDoors(boolean state){ leftDoors = state; }
    public void setRightDoors(boolean state){ rightDoors = state; }
    public void setTemperature(int temp){ temperature = temp; }
    public void setAdvertisements(boolean state){ advertisements = state; }
    public void setLights(boolean state){ lights = state; }   
    public void setHeadlights(boolean state){ headlights = state; }
    
    
    /*******MEMBER FUNCTIONS*******/
    
    /* update power for train */
    public void updatePower(){
        double currentVelocity;
        double commandedVelocity = 0; // preset for case Vcmd > SpeedLimit or < 0
        if(brakeFailure || signalFailure || engineFailure){
            /* failure mode activated, use Emergency Brake, no Power */
            this.power = -5;
        }
        else{
            /* speed at time of function call */
            currentVelocity = actualSpeed; /* mph */
            /* get speed to be achieved */
            if(suggestedSpeed > speedLimit){
                commandedVelocity = speedLimit;
            } else if(suggestedSpeed>0) {
                commandedVelocity = suggestedSpeed; /* mph */
            }
            /* this value c is the calculated distance traveled if braking was to 
             * start at the current velocity
            */
            double c = (Math.pow(currentVelocity,2))*.0003728; 
            double b;
            if(isNextStation() && (stationAuthority <= authority)){ 
                /* next station up is one to stop at, and no signals before */
                b = getStationAuthority() - getBlockDistanceTraveled();
                announcementsText =  new String(announcementsText+ "Stopping at next station, be prepared to exit");
            } else {
                /* either not stopping at station, or some signal is before it */
                b = authority;
            }
            b = b/1760;
            if(emergencyBrake || passengerBrake) {
                /* IF EB active, outside of failure mode */
                setPower(-5); 
            } else if((b <= c) && currentVelocity <= 1){
                /* our service brake has done it's duty */
                setPower(0);
                setServiceBrake(false);
            } else if(serviceBrake) {
                /* service brake active, remain set */
                setPower(-1);
            } else if((b <= c) && currentVelocity > 1) {
                /* initialize set of service brake */
                setPower(-1);
                setServiceBrake(true);
            } else {
                /* error check for a value too high */
                if(commandedVelocity > MAX_SPEED*KM_TO_MILES) {
                    commandedVelocity = MAX_SPEED*KM_TO_MILES;
                }
                double sampleError = (commandedVelocity - currentVelocity)*MPH_TO_MPS;
                /* sample error in Meters/second */
                /*   Calculate power from Vcurr and Vcmd.  */
                if(power < ENGINE_POWER)
                    prevMew = prevMew + (1/2)*(prevError + sampleError);
                /*sample period is .1 seconds */
                
                /*^else prevMew = prevMew; using 'prevmew' as current mew after,
                 * will become next times 'prevmew' 
                 */
                /* Calculate Power method 1 */
                double P1 = (Kp*sampleError) + (Ki*prevMew) ;
                P1 = (double) Math.round(P1 * 1000) / 1000; 
                /* round to 2 decimal places(in kW) for good comparison */
                
                /* Calculate Power 2 */
                double P2 = (Kp*sampleError) + (Ki*prevMew);
                P2 = (double) Math.round(P2 * 1000) / 1000;
            
                /* Calculate Power 3 */
                double P3 = (Kp*sampleError) + (Ki*prevMew);
                P3 = (double) Math.round(P3 * 1000) / 1000;
                
                double P=0; /*default if no consensus */
                /* chck for 2/3 */
                if(P1 == P2 && P1 >= 0){
                    P = P1;
                } else if(P2 == P3 && P2 >= 0) {
                    P = P2;
                } else if(P1 == P3 && P1 >= 0) {
                    P = P1;
                } else {
                    announcementsText =  new String(announcementsText + "Power not 2/3: everyone panic!");
                    return;
                }
                prevError = sampleError; /* save for next iteration */
                if(P<0){P = 0;} /* no negative power that is not set by brake */
                if(P <= 120000){
                    setPower(P/1000);
                }else if(P > 120000){
                    setPower(120); 
                }
                /* else its bugging out! */                
            }          
        }
    }
}

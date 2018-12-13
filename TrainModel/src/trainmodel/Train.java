package trainmodel;

import java.util.*;

/*Train Class
    Class/Structure for a train object
    Contains all data required for a train to run

    Calculates critical values in updateVelocity() function
 */
public class Train {

    //ID -- Line + train number
    private final String trainID;

    //brakes and failure states
    private boolean emergencyBrake;
    private boolean serviceBrake;
    private boolean engineFailure;
    private boolean signalFailure;
    private boolean brakeFailure;

    //dimensions
    private final double height;
    private final double width;
    private final double length;
    private double mass;

    //commanded values
    private double speed;
    private double authority;
    private double speedLimit;

    //current train description info
    private double power;
    private boolean passengerPulled;
    private double velocity;    //actual velocity, returned to train controller
    private double acceleration;
    private double grade;
    private double elevation;
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
    private double blockLength;
    private double prevDistance;
    private double stationAuthority;

    //direction of train
    private boolean forward;

    //Used to signal if the train moved onto the next track: 0 or 1
    private int next = 1;

    //distance train has traveled on current block
    private double blockDistanceTraveled = 0;

    //cumulative force of train
    private double force;

    //acceleration/deceleration limits
    private double accelerationLimit;
    private double decelerationLimit;

    //schedule of the train and the number of stops the train has completed
    public ArrayList<String> stops;
    public int numberOfStops;

    //constant values
    private final int CAR_COUNT = 1;
    private final int WEIGHT_PER_PERSON = 150; //lbs
    private final double TRAIN_LENGTH = 32.2; //m
    private final double TRAIN_HEIGHT = 3.42; //m
    private final double TRAIN_WIDTH = 2.65; //m
    private final double TRAIN_MASS = 40.9; //t
    private final double MAX_SPEED = 70; //km/h
    private final int MAX_CAPACITY = 222; //people
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
    private final double MPH_TO_MPS = 0.44704;

    /**
     * *****CONSTRUCTOR****** line --> GREEN or RED trainNumber --> specified
     * number from CTC crewCount --> should be 1 passCount --> should usually be
     * initially 0, but could be set from start stops --> schedule of the train
     * suggSpeed --> suggested speed
     */
    public Train(String line, int trainNumber, int crewCount, int passCount,
            ArrayList<String> stops, double suggSpeed) {
        //set train id
        this.trainID = line.toUpperCase() + "_" + trainNumber;

        //initialize schedule and stop counter
        this.numberOfStops = 0;
        this.stops = stops;

        //default state
        this.passengerPulled = false;
        this.emergencyBrake = false;
        this.serviceBrake = false;
        this.engineFailure = false;
        this.signalFailure = false;
        this.brakeFailure = false;

        //set suggested speed
        this.speed = suggSpeed;

        //dimensions
        this.height = Math.round(TRAIN_HEIGHT * METERS_TO_FEET * 100.0) / 100.0;
        this.width = Math.round(TRAIN_WIDTH * METERS_TO_FEET * 100.0) / 100.0;
        this.length = Math.round(TRAIN_LENGTH * METERS_TO_FEET * 100.0) / 100.0;
        this.mass = Math.round((TRAIN_MASS * TONS_TO_POUNDS)
                + (WEIGHT_PER_PERSON * (crewCount + passCount)) * 100.0) / 100.0;

        //initialize distance tracker to 0
        this.prevDistance = 0;

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

        //Set force and limits
        this.force = 0;
        this.decelerationLimit = 0;
        this.accelerationLimit = MEDIUM_ACCELERATION * METERS_TO_FEET;
    }

    /**
     * *****GETTERS*******
     */
    //ID getter
    public String getTrainID() {
        return trainID;
    }

    //BRAKE AND FAILURE GETTERS
    //returns if passenger pulled emergency brake is true or not
    public boolean isPassengerPulled() {
        return passengerPulled;
    }

    //returns state of emergencyBrake
    public boolean isEmergencyBrake() {
        return emergencyBrake;
    }

    //returns state of service brake
    public boolean isServiceBrake() {
        return serviceBrake;
    }

    //returns state of engine failure
    public boolean isEngineFailure() {
        return engineFailure;
    }

    //returns state of signal failure
    public boolean isSignalFailure() {
        return signalFailure;
    }

    //returns state of brake failure
    public boolean isBrakeFailure() {
        return brakeFailure;
    }

    //DIMENSION GETTERS
    //returns train height
    public double getHeight() {
        return height;
    }

    //returns train width
    public double getWidth() {
        return width;
    }

    //returns train length
    public double getLength() {
        return length;
    }

    //returns empty train mass
    public double getMass() {
        return mass;
    }

    //returns suggested speed (mph)
    public double getSpeed() {
        return speed;
    }

    //returns authority (yards)
    public double getAuthority() {
        return authority;
    }

    //returns distance traveled within current block (miles)
    public double getBlockDistanceTraveled() {
        return blockDistanceTraveled;
    }

    //current train description getters
    //returns active time*multiplier
    public double getTime(int multiplier) {
        double currTime = System.currentTimeMillis();

        return Math.round((currTime - this.creationTime) / 1000) * multiplier;
    }

    //returns state of next flag variable
    public int getNext() {
        return next;
    }

    //returns total distance traveled
    public double getDistance() {
        return distance;
    }

    //returns power command
    public double getPower() {
        return power;
    }

    //returns speed limit
    public double getSpeedLimit() {
        return speedLimit;
    }

    //returns force
    public double getForce() {
        return force;
    }

    //returns velocity of the train (actual speed mph)
    public double getVelocity() {
        return velocity;
    }

    //returns train acceleration
    public double getAcceleration() {
        return acceleration;
    }

    //returns track grade
    public double getGrade() {
        return grade;
    }

    //returns track elevation
    public double getElevation() {
        return elevation;
    }

    //returns name of the next station on the track
    public String getStation() {
        return station;
    }

    //returns passenger count
    public int getPassengerCount() {
        return passengerCount;
    }

    //returns crew count
    public int getCrewCount() {
        return crewCount;
    }

    //returns state of the left doors
    public boolean isLeftDoors() {
        return leftDoors;
    }

    //returns state of the right doors
    public boolean isRightDoors() {
        return rightDoors;
    }

    //returns temperature
    public int getTemperature() {
        return temperature;
    }

    //returns true if ads are being shown, else false
    public boolean isAdvertisements() {
        return advertisements;
    }

    //returns state of the lights
    public boolean isLights() {
        return lights;
    }

    //returns distance remaining until the next station on the track
    public double getStationAuthority() {
        return stationAuthority;
    }

    /**
     * *****SETTERS******
     */
    //sets block length to l
    public void setBlockLength(double l) {
        blockLength = l;
    }

    //sets next flag variable to n
    public void setNext(int n) {
        next = n;
    }

    //brake and failure setters
    //sets passenger pulled emergency brake to state
    public void setPassengerPulled(boolean state) {
        passengerPulled = state;
    }

    //sets emergency brake true
    public void setEmergencyBrake(boolean state) {
        emergencyBrake = state;
    }

    //sets service brake to true
    public void setServiceBrake(boolean state) {
        serviceBrake = state;
    }

    //sets engine failure to state
    public void setEngineFailure(boolean state) {
        engineFailure = state;
    }

    //sets signal failure to state
    public void setSignalFailure(boolean state) {
        signalFailure = state;
    }

    //sets brake failure to state
    public void setBrakeFailure(boolean state) {
        brakeFailure = state;
    }

    //sets speed limit to s (mph)
    public void setSpeedLimit(double s) {
        speedLimit = s;
    }

    //command value setters
    //sets suggested speed to speed
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    //sets authority
    public void setAuthority(double authority) {
        this.authority = authority;
    }

    //sets distance until next station on track (yards)
    public void setStationAuthority(double sa) {
        this.stationAuthority = sa;
    }

    //other setters
    //sets commanded power
    public void setPower(double power) {
        this.power = power;
    }

    //sets grade
    public void setGrade(double grade) {
        this.grade = grade;
    }

    //sets elevation
    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    //set station name
    public void setStation(String station) {
        this.station = station;
    }

    //set passenger count
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    //set crew count
    public void setCrewCount(int crewCount) {
        this.crewCount = crewCount;
    }

    //set door state
    public void setLeftDoors(boolean state) {
        leftDoors = state;
    }

    //set door state
    public void setRightDoors(boolean state) {
        rightDoors = state;
    }

    //set temp
    public void setTemperature(int temp) {
        temperature = temp;
    }

    //set state of ads being shown
    public void setAdvertisements(boolean state) {
        advertisements = state;
    }

    //set states of lights
    public void setLights(boolean state) {
        lights = state;
    }

    /**
     * *****MEMBER FUNCTIONS******
     */
    //function to update mass.. should be used when passenger/crew count changes
    private void updateMass() {
        this.mass = Math.round(((TRAIN_MASS * TONS_TO_POUNDS)
                + (WEIGHT_PER_PERSON * (this.crewCount + this.passengerCount)))
                * 100.0) / 100.0;
    }

    /*updateVelocity
        Meat and Potatoes function
        Calculates force, acceleration, distance, and velocity based on given
        power
     */
    public void updateVelocity() {
        //update mass just in case
        this.updateMass();

        //speed at time of function call
        double currentVelocity = this.velocity; //mph

        //calculate force
        double forceDown = (this.mass) * GRAVITY
                * (Math.cos(Math.toDegrees(Math.atan(grade / 100.0)))
                * Math.PI / 180);

        if (this.power > ENGINE_POWER) {
            this.power = ENGINE_POWER;
        }

        //braking logic
        this.serviceBrake = ((this.power < 0) && (this.power != -5))
                && (!this.emergencyBrake);
        this.emergencyBrake = this.isEngineFailure()
                || this.isBrakeFailure() || this.isSignalFailure()
                || (this.power == -5) || passengerPulled;

        //force from engine calculation
        double forceFromEng;
        if (currentVelocity == 0) {
            forceFromEng = (this.power * KW_TO_NMS) / 1;
        } else {
            forceFromEng = (this.power * KW_TO_NMS)
                    / (currentVelocity * MPH_TO_MPS);
        }

        //calculate normal force
        double forceNormal = (this.mass) * GRAVITY
                * (Math.sin(Math.toDegrees(Math.atan(grade / 100.0)))
                * Math.PI / 180);
        //calculate friction force
        double forceFriction = forceNormal
                + (COEFFICIENT_OF_FRICTION * forceDown);

        //total force
        this.force = Math.round((forceFromEng - forceFriction) * 100.0)
                / 100.0;

        //calculate acceleration
        //accel = force/mass
        this.acceleration = Math.round((this.force / this.mass) * 100.0)
                / 100.0;
        //if accel > limit, accel = limit
        if (this.acceleration > this.accelerationLimit) {
            this.acceleration = this.accelerationLimit;
        }

        //check if braking
        if (this.emergencyBrake || this.serviceBrake || (this.power == -5)) {
            if (this.serviceBrake && !this.emergencyBrake) {
                this.acceleration += SERVICE_DECELERATION;
            } else {
                this.acceleration += EMERGENCY_DECELERATION;
            }
        }
        //convert acceleration
        this.acceleration = Math.round(this.acceleration * METERS_TO_FEET
                * 100.0) / 100.0;
        //calculate velocity
        this.velocity = (currentVelocity)
                + (this.acceleration / MPH_TO_FPS);

        if (this.velocity <= 0) {
            this.velocity = 0;
            this.acceleration = 0;
        }
        if (this.velocity > SPEED_LIMIT) {
            this.velocity = SPEED_LIMIT;
            this.acceleration = 0;
        }

        //round velocity
        this.velocity = Math.round(this.velocity * 100.0)
                / 100.0;

        //calculate distance... updateVelocity() runs every 1 second, so it is
        //safe to do this
        this.distance = this.distance + (this.velocity / 3600);

        //find distance traveled within current block
        blockDistanceTraveled = this.distance - this.prevDistance;

        //determine if train is on next block, if yes set flag next to 1
        if (this.next == 0 && (blockDistanceTraveled * 1760) >= (blockLength)) {
            //System.out.println("In if, changing next to 1");
            this.next = 1;
            prevDistance += blockDistanceTraveled;
            blockDistanceTraveled = 0;
        }
    }
}

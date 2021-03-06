package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.subsystems.PID.PIDOutputMotor;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Creates TalonSR motor controller objects for
	// the left and right motor controllers.
	private TalonSRX left, right;
	private boolean inverted = false;
	
	//Encoders
	private Encoder leftEncoder, rightEncoder;
	
	//PID Controllers- A loop that controllers motor speed 
	public PIDController rightController;
	public PIDOutputMotor pidOutputRight;
	
	public PIDController leftController;
	public PIDOutputMotor pidOutputLeft;
	
	//Gyro Sensor
	public Gyro gyro;
	
	// Constructor initializes these variables.
	public DriveSubsystem() {
		//Instantiate motors
		left = new TalonSRX(RobotMap.leftDrivePort);
		right = new TalonSRX(RobotMap.rightDrivePort);		
		//Instantiate Encoders
		leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, false);
		rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false);
		
		// sets that the PID controllers are going to get the speeds of the motors
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		
		//Set Encoder distanceFromTower per pulse
		rightEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		
		//Instantiate PID controllers and output objects
		pidOutputRight = new PIDOutputMotor();
		rightController = new PIDController(ConstantsMap.pDrive, ConstantsMap.iDrive, ConstantsMap.dDrive, ConstantsMap.fDrive, rightEncoder, pidOutputRight);

		//Set PID Source Output
		pidOutputLeft = new PIDOutputMotor();
		leftController = new PIDController(ConstantsMap.pDrive, ConstantsMap.iDrive, ConstantsMap.dDrive, ConstantsMap.fDrive, leftEncoder, pidOutputLeft);
		
		//Set Ouput Range for pid outputs
		rightController.setOutputRange(-1, 1);
		leftController.setOutputRange(-1,1);
		
		rightController.setAbsoluteTolerance(0.1);
		leftController.setAbsoluteTolerance(0.1);
		
		//Instantiate gyro
		gyro = new AnalogGyro(RobotMap.gyroPort);
		gyro.reset();
	}
	
	//Gyro methods
	public void resetGyro(){
		gyro.reset();
	}
	
	public double getGyroAngle(){
		return gyro.getAngle();
	}
	
	// Setter methods for each side.
	public void setLeftSpeed(double speed) {
		
		left.set(speed);
		
	}
	
	public void setRightSpeed (double speed) {
		
		right.set(speed);
		
	}
	
	// Getter methods for each side.
	public double getLeftSpeed() {
		
		return left.getSpeed();
		
	}
	
	public double getRightSpeed() {
		
		return right.getSpeed();
		
	}
	
	//Get Encoder Distances
	public double getRightEncoderDistance(){
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance(){
		return leftEncoder.getDistance();
	}
	//Get raw encoder counts
	public int getLeftEncoderRawValue(){
		return leftEncoder.get();
	}
	
	public int getRightEncoderRawValue(){
		return rightEncoder.get();
	}
	//Get Encoder Rates
	public double getRightEncoderRate(){
		return rightEncoder.getRate();
	}
	
	public double getLeftEncoderRate(){
		return leftEncoder.getRate();
	}
	
	//reset encoders
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	//PID Methods
	public void enableRightPIDController(double distance){
		rightController.setSetpoint(distance);
		rightController.enable();
	}
	public void disablePIDControllers(){
		rightController.disable();
		leftController.disable();
	}
	
	public void enableLeftPIDController(double distance){
		leftController.setSetpoint(distance);
		leftController.enable();
	}
	
	public double getRightPIDOutput(){
		return pidOutputRight.getOutput();
	}
	
	public double getLeftPIDOuput(){
		return pidOutputLeft.getOutput();
	}

	//Is the robot within the absolute tolerance of the target distance
	public boolean rightPIDOnTarget(){
		return rightController.onTarget();
	}
	
	public boolean leftPIDOnTarget(){
		return leftController.onTarget();
	}
	
	// Setter and Getter for inverted.
	public boolean isInverted() {
		
		return inverted;
		
	}
	
	public void toggleInversion() {
		
		inverted = !inverted;
		
	}
	
	public void stop() {
		
		this.left.set(0);
		this.right.set(0);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


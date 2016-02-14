package org.usfirst.frc.team2022.robot;

public class ConstantsMap {
	
	//Encoder Constants For Drive Train
	public static final double DRIVE_WHEEL_RADIUS = 2.25;
	public static final int DRIVE_PULSE_PER_ROTATION = 360;
	public static final double DRIVE_GEAR_RATIO = 1/1;
	public static final double DRIVE_ENCODER_PULSE_PER_ROT = DRIVE_PULSE_PER_ROTATION * DRIVE_GEAR_RATIO;
	public static final double DRIVE_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_RADIUS * 2) / DRIVE_ENCODER_PULSE_PER_ROT;
	public static final double DRIVE_ENCODER_MAX_PERIOD = .1;
	public static final double DRIVE_ENCODER_MIN_RATE = 10;
	
	//PID Constants For Drive
	public static double pDrive = 0.4;
	public static double iDrive = 0.02;
	public static double dDrive = 0.2;
	public static double fDrive = 0.5;
	
	//Encoder Constants For Drive Train
	public static final double SHOOTER_WHEEL_RADIUS = 2.25;
	public static final int SHOOTER_PULSE_PER_ROTATION = 360;
	public static final double SHOOTER_GEAR_RATIO = 1/1;
	public static final double SHOOTER_ENCODER_PULSE_PER_ROT = SHOOTER_PULSE_PER_ROTATION * SHOOTER_GEAR_RATIO;
	public static final double SHOOTER_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_RADIUS * 2) / DRIVE_ENCODER_PULSE_PER_ROT;
	public static final double SHOOTER_ENCODER_MAX_PERIOD = .1;
	public static final double SHOOTER_ENCODER_MIN_RATE = 10;
	
	//PID Constants For Shooter
	public static double pShooter = 0.4;
	public static double iShooter = 0.02;
	public static double dShooter = 0.2;
	public static double fShooter = 0.5;
	
	//Robot Dimensions
	public static final double ROBOT_WIDTH = 22;
	
	//Speeds
	public static final double HINGE_SPEED = 0.5;
	public static final double SHOOTER_SPEED = 284.64; //inches per second
	
	//Shooter Positions
	public static final double DOWN_POSITION_ANGLE = -10.5;
	public static final double FLAT_POSITION_ANGLE = 0;
	public static final double UP_POSITION_ANGLE = 74.25;

	
}

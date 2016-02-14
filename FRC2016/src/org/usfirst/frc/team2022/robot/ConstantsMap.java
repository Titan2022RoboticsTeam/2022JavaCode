package org.usfirst.frc.team2022.robot;

public class ConstantsMap {
	
	//Encoder Constants
	public static final double DRIVE_WHEEL_RADIUS = 2.25;
	public static final int PULSE_PER_ROTATION = 360;
	public static final double GEAR_RATIO = 1/1;
	public static final double DRIVE_ENCODER_PULSE_PER_ROT = PULSE_PER_ROTATION * GEAR_RATIO;
	public static final double DRIVE_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_RADIUS * 2) / DRIVE_ENCODER_PULSE_PER_ROT;
	public static final double DRIVE_ENCODER_MAX_PERIOD = .1;
	public static final double DRIVE_ENCODER_MIN_RATE = 10;
	
	//PID Constants
	public static double pC = 0.4;
	public static double iC = 0.02;
	public static double dC = 0.2;
	public static double kC = 0.5;
	
	//Robot Dimensions
	public static final double ROBOT_WIDTH = 22;
	
	//Speeds
	public static final double HINGE_SPEED = 0.5;
	
	//Shooter Positions
	public static final double DOWN_POSITION_ANGLE = -10.5;
	public static final double FLAT_POSITION_ANGLE = 0;
	public static final double UP_POSITION_ANGLE = 74.25;

	
}

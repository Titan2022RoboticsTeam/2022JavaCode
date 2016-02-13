package org.usfirst.frc.team2022.robot;

public class ConstantsMap {
	
	//Encoder Constants
	public static final double driveWheelRadius = 2.25;
	public static final int pulsePerRotation = 360;
	public static final double gearRatio = 1/1;
	public static final double driveEncoderPulsePerRot = pulsePerRotation * gearRatio;
	public static final double driveEncoderDistPerTick = (Math.PI * driveWheelRadius * 2) / driveEncoderPulsePerRot;
	public static final double driveEncoderMaxPeriod = .1;
	public static final double driveEncoderMinRate = 10;
	
	//PID Constants
	public static double pC = 0.4;
	public static double iC = 0.02;
	public static double dC = 0.2;
	public static double kC = 0.5;
	
}

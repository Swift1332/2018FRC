/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1332.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//PWM
	public static int p_elevatorPrimaryStageChannel = 0;
	public static int p_frontLeftDriveChannel = 1;
	public static int p_rearLeftDriveChannel = 2;
	public static int p_frontRightDriveChannel = 3;
	public static int p_rearRightDriveChannel = 4;	
	public static int p_leftArmPickupChannel = 5;
	public static int p_rightArmPickupChannel = 6;
	public static int p_elevatorSecondaryStageChannel = 7;
	
	//DIO
	public static int d_elevatorPrimaryEncoderBChannel = 0;
	public static int d_elevatorPrimaryEncoderAChannel = 1;
	public static int d_elevatorSecondaryEncoderBChannel = 2;
	public static int d_elevatorSecondaryEncoderAChannel = 3;
	public static int d_rightDriveEncoderBChannel = 4;
	public static int d_rightDriveEncoderAChannel = 5;
	public static int d_leftDriveEncoderBChannel = 6;
	public static int d_leftDriveEncoderAChannel = 7;
	
	//Analog	
	public static int a_elevatorPrimaryLowerLimitChannel = 0;
	public static int a_elevatorPrimaryUpperLimitChannel = 1;
	public static int a_elevatorSecondaryLowerLimitChannel = 2;
	public static int a_elevatorSecondaryUpperLimitChannel = 3;
}

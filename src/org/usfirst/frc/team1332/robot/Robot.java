/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1332.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1332.robot.components.ADigitalInput;
import org.usfirst.frc.team1332.robot.subsystems.Pickup;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	
	public static Robot robot;
	
	public Pickup ss_pickup;

	
	Spark sc_frontLeftDrive;
	Spark sc_frontRightDrive;
	Spark sc_rearLeftDrive;
	Spark sc_rearRightDrive;
	Spark sc_primaryElevator;
	VictorSP sc_secondaryElevator;
	VictorSP sc_leftPickup;
	VictorSP sc_rightPickup;
	
	SpeedControllerGroup scg_leftDrive;
	SpeedControllerGroup scg_rightDrive;	
	public SpeedControllerGroup scg_pickup;
	
	DifferentialDrive driveTrain;
	
	Encoder enc_elevatorPrimary;
	Encoder enc_elevatorSecondary;
	Encoder enc_leftDrive;
	Encoder enc_rightDrive;
	
	ADigitalInput l_elevatorPrimaryUpper;
	ADigitalInput l_elevatorPrimaryLower;
	ADigitalInput l_elevatorSecondaryUpper;
	ADigitalInput l_elevatorSecondaryLower;
	

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		robot = this;
		m_oi = new OI();
		
		ss_pickup = new Pickup();
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		
		sc_frontLeftDrive = new Spark(RobotMap.p_frontLeftDriveChannel);
		sc_frontRightDrive = new Spark(RobotMap.p_frontRightDriveChannel);
		sc_rearLeftDrive = new Spark(RobotMap.p_rearLeftDriveChannel);
		sc_rearRightDrive = new Spark(RobotMap.p_rearRightDriveChannel);
		
		scg_leftDrive = new SpeedControllerGroup(sc_frontLeftDrive, sc_rearLeftDrive);
		scg_rightDrive = new SpeedControllerGroup(sc_frontRightDrive, sc_rearRightDrive);
		
		driveTrain = new DifferentialDrive(scg_leftDrive, scg_rightDrive);
		
		sc_primaryElevator = new Spark(RobotMap.p_elevatorPrimaryStageChannel);
		sc_primaryElevator.setInverted(true);
		
		sc_secondaryElevator = new VictorSP(RobotMap.p_elevatorSecondaryStageChannel);
		//sc_secondaryElevator.setInverted(true);
		
		sc_leftPickup = new VictorSP(RobotMap.p_leftArmPickupChannel);
		sc_rightPickup = new VictorSP(RobotMap.p_rightArmPickupChannel);
		
		scg_pickup = new SpeedControllerGroup(sc_leftPickup, sc_rightPickup);
		
		enc_elevatorPrimary = new Encoder(RobotMap.d_elevatorPrimaryEncoderAChannel, RobotMap.d_elevatorPrimaryEncoderBChannel);
		enc_elevatorSecondary = new Encoder(RobotMap.d_elevatorSecondaryEncoderAChannel, RobotMap.d_elevatorSecondaryEncoderBChannel);
		enc_leftDrive = new Encoder(RobotMap.d_leftDriveEncoderAChannel, RobotMap.d_leftDriveEncoderBChannel);
		enc_rightDrive = new Encoder(RobotMap.d_rightDriveEncoderAChannel, RobotMap.d_rightDriveEncoderBChannel);
		
		l_elevatorPrimaryUpper = new ADigitalInput(RobotMap.a_elevatorPrimaryUpperLimitChannel);
		l_elevatorPrimaryLower = new ADigitalInput(RobotMap.a_elevatorPrimaryLowerLimitChannel);
		l_elevatorSecondaryUpper=  new ADigitalInput(RobotMap.a_elevatorSecondaryUpperLimitChannel);
		l_elevatorSecondaryLower =  new ADigitalInput(RobotMap.a_elevatorSecondaryLowerLimitChannel);
	}
	
	public void alwaysPeriodic() {
		SmartDashboard.putNumber("Primary Elevator Encoder", enc_elevatorPrimary.get());
		SmartDashboard.putNumber("Secondary Elevator Encoder", enc_elevatorSecondary.get());
		SmartDashboard.putNumber("Right Drive Encoder", enc_rightDrive.get());
		SmartDashboard.putNumber("Left Drive Encoder", enc_leftDrive.get());
		
		SmartDashboard.putBoolean("Primary Elevator Upper Limit", l_elevatorPrimaryUpper.get());
		SmartDashboard.putBoolean("Primary Elevator Lower Limit", l_elevatorPrimaryLower.get());
		SmartDashboard.putBoolean("Secondary Elevator Upper Limit", l_elevatorSecondaryUpper.get());
		SmartDashboard.putBoolean("Secondary Elevator Lower Limit", l_elevatorSecondaryLower.get());
		/*
		SmartDashboard.putNumber("Primary Elevator Upper Limit V", l_elevatorPrimaryUpper.getValue());
		SmartDashboard.putNumber("Primary Elevator Lower Limit V", l_elevatorPrimaryLower.getValue());
		SmartDashboard.putNumber("Secondary Elevator Upper Limit V", l_elevatorSecondaryUpper.getValue());
		SmartDashboard.putNumber("Secondary Elevator Lower Limit V", l_elevatorSecondaryLower.getValue());
		*/
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		alwaysPeriodic();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		double boostModifier = 0;
		
		if (m_oi.c_joystickPrimary.getRawButton(6))
		{
			System.out.println("BOOST");
			boostModifier = .2;
		}
		
		driveTrain.arcadeDrive(m_oi.c_joystickPrimary.getY() * (.8 + boostModifier), m_oi.c_joystickPrimary.getRawAxis(4) * -1);

		double primaryElevatorInput = m_oi.c_joystickSecondary.getY();
		double secondaryElevatorInput = m_oi.c_joystickSecondary.getRawAxis(3);
		System.out.println("Secondary Stick: " + secondaryElevatorInput);
		
		if (l_elevatorPrimaryUpper.get() || l_elevatorPrimaryLower.get()) 
		{
			if (l_elevatorPrimaryUpper.get() && primaryElevatorInput >= 0)
			{
				sc_primaryElevator.set(primaryElevatorInput);				
			}
			else if (l_elevatorPrimaryLower.get() && primaryElevatorInput <= 0)
			{
				sc_primaryElevator.set(primaryElevatorInput);
			}
			else 
			{
				// hitting a switch
				sc_primaryElevator.set(0);
			}

		}
		else // no limit switches triggered
		{			
			sc_primaryElevator.set(primaryElevatorInput);			
		}
		
		
		if (l_elevatorSecondaryUpper.get() || l_elevatorSecondaryLower.get()) 
		{
			if (l_elevatorSecondaryUpper.get() && secondaryElevatorInput >= 0)
			{
				sc_secondaryElevator.set(secondaryElevatorInput);				
			}
			else if (l_elevatorSecondaryLower.get() && secondaryElevatorInput <= 0)
			{
				sc_secondaryElevator.set(secondaryElevatorInput);
			}
			else 
			{
				// hitting a switch
				sc_secondaryElevator.set(0);
			}

		}
		else // no limit switches triggered
		{			
			sc_secondaryElevator.set(secondaryElevatorInput);			
		}
		
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

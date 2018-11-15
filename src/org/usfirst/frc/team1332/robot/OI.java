/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1332.robot;

import org.usfirst.frc.team1332.robot.commands.EjectCube;
import org.usfirst.frc.team1332.robot.commands.HoldCube;
import org.usfirst.frc.team1332.robot.commands.PickupCube;
import org.usfirst.frc.team1332.robot.commands.StopPickup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());	
	
	
	Joystick c_joystickPrimary;
	public Joystick c_joystickSecondary;
	JoystickButton b_pickupCubeButton;
	JoystickButton b_ejectCubeButton;
	JoystickButton b_holdCubeButton;
	
	public OI(){
		c_joystickPrimary = new Joystick(0);
		c_joystickSecondary = new Joystick(1);
		
		b_pickupCubeButton = new JoystickButton(c_joystickSecondary, 7);
		b_ejectCubeButton = new JoystickButton(c_joystickSecondary, 8);
		b_holdCubeButton = new JoystickButton(c_joystickSecondary, 6);
				
		
		b_pickupCubeButton.whenPressed(new PickupCube());
		b_ejectCubeButton.whenPressed(new EjectCube());
		b_holdCubeButton.whenPressed(new HoldCube());
		
		b_pickupCubeButton.whenReleased(new StopPickup());
		b_ejectCubeButton.whenReleased(new StopPickup());
		b_holdCubeButton.whenReleased(new StopPickup());
		
	}
}

package org.usfirst.frc.team1332.robot.subsystems;

import org.usfirst.frc.team1332.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pickup extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runPickup(double r_speed, double l_speed) {
    	Robot.robot.sc_leftPickup.set(l_speed);
    	Robot.robot.sc_rightPickup.set(r_speed);
    }
    
    public void eject(double speed) {
    	runPickup(speed, speed);    	
    }
    
    public void pickup(double speed) {
    	runPickup(speed, speed * 0.5);    	
    }
    
}


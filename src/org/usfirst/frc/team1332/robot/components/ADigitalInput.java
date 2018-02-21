package org.usfirst.frc.team1332.robot.components;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class ADigitalInput extends AnalogInput {

	public ADigitalInput(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}

	  /**
	   * Get the value from a digital input channel. Retrieve the value of a single digital input
	   * channel from the FPGA.
	   *
	   * @return the status of the digital input
	   */
	  public boolean get() {
	    return this.getValue() > 10;
	  }
	  
	  @Override
	  public void initSendable(SendableBuilder builder) {
	    builder.setSmartDashboardType("ADigital Input");
	    builder.addBooleanProperty("Value", this::get, null);
	  }

}

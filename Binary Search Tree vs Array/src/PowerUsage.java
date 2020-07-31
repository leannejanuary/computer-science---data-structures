/**
 * PowerUsage stores the three values needed from the cleaned_data.csv file
 * in order to allow them to be inserted into data structures as one data item.
 * 
 * @author LeanneJanuary 
 * 
 */

public class PowerUsage {
	// instance variables
	private String dateTime;
	private String power;
	private String voltage;

	/**
	 * Constructor for the PowerUsage class.
	 * @param dateTime The date and time of the power usage
	 * @param power The amount of power used
	 * @param voltage The amount of volts used
	 */
	public PowerUsage (String dateTime, String power, String voltage) {
		this.dateTime = dateTime;
		this.power = power;
		this.voltage = voltage;
	}

	/**
	 * Accessor method: getDateTime
	 * @return current value of dateTime
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * Accessor method: getPower
	 * @return current value of power
	 */
	public String getPower() {
		return this.power;
	}

	/**
	 * Accessor method: getVoltage
	 * @return current value of voltage
	 */
	public String getVoltage() {
		return this.voltage;
	}

	/**
	 * toString method
	 * @return modified String output
	 */
	public String toString() {
		return this.dateTime + " Power: " + this.power + ", Voltage: " + this.voltage;
	}
}




package org.usfirst.frc2830.PeligrosoRobot.util.directions;

public class Step {

	public static final int MOVE = 0;
	public static final int TURN = 1;
	private double speed;
	private int command;
	private double distance;

	public void step(int newCommand, double newDistance, double newSpeed) {
		this.setCommand(newCommand);
		this.setDistance(newDistance);
		this.setSpeed(newSpeed);
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}

	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}

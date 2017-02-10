package org.usfirst.frc2830.PeligrosoRobot.util.directions;

import java.util.Collection;
import java.util.Properties;

public class DrivingDirections {
	
	private Collection<Step> directionList;
	
	public DrivingDirections(Properties props) {
	//	while(props.size() < )
		//directionList = props.values();
	}
	
	public int getNumberOfSteps(){
		return directionList.size();
	}
	
	public Object getStep(int stepToGet){
		return null;
	}

}

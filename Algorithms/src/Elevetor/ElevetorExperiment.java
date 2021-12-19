package Elevetor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ElevetorExperiment {
	
	static int currentLevel = 5;

	public static void main(String[] args) {
		String line;
		String splitBy = ",";
		String[] SLevels = {};
		LinkedList<Integer> levelList = new LinkedList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\blued\\OneDrive - Sheffield Hallam University\\Desktop\\Java\\ADS\\Portfolio\\Project 4\\Elevator-master\\Data Sets\\5.csv"));
			while((line = br.readLine()) != null)
			{
				SLevels = line.split(splitBy);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < SLevels.length; i++)
		{
			levelList.add(Integer.parseInt(SLevels[i]));
		}
		System.out.println("The elevator will go to these floors : "+levelList);
		try {
			FIFO(levelList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}
	
	
	public static void FIFO (LinkedList<Integer> floors) throws InterruptedException {
		
		System.out.println("The current floor is : "+currentLevel);
		long timeStart = System.currentTimeMillis();
		long previousTime = timeStart;
		ArrayList<Integer> floorTimes = new ArrayList<Integer>();
		while(!floors.isEmpty()) {
			int l = floors.getFirst();
			
			while(l>currentLevel) {
				String string = String.format("%s", currentLevel);
				System.out.print(string);
			
					Thread.sleep(1000);
			
					currentLevel++;
			}
			
			while(l<currentLevel) {
				String string = String.format("%s", currentLevel);
				System.out.print(string);
				Thread.sleep(1000);
				currentLevel--;
			}
			long floorTime = System.currentTimeMillis();
			long totalTime = (floorTime - timeStart)/1000;
			System.out.println("\nYou have arrived level "+currentLevel + " in: " + totalTime + " Seconds");
			floorTimes.add((int) totalTime);
			floors.removeFirst();
			Thread.sleep(2000);
		}
		
		long endtime = System.currentTimeMillis();
		double time = (endtime-timeStart)/1000;
		int min = floorTimes.get(0); // initializing with the first time recorded to ensure that this works with any data set
		for(int i = 0; i < floorTimes.size()-1; i++)
		{
			int calculation;
			calculation = floorTimes.get(i+1) - floorTimes.get(i);  // this is done after time is taken so that it doesn't add any delays.
			if(calculation < min)
			{
				min = calculation;
			}
		}
		System.out.println("Duration : "+time+ " seconds");
		System.out.println("Mean: " + time/floorTimes.size());
		System.out.println("Minimum: " + min);
		//logic for this:
		// if output exists, append it, if not create a new file
		// surround it by try catch block
		// TODO bufferedWriter to write to csv
	}

}

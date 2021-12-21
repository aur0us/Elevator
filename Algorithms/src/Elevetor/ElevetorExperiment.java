package Elevetor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ElevetorExperiment {
	static int currentLevel = 0	; // changed to 0 since we'll sort the list so it'll make sense to start from 0

	public static void main(String[] args) {
		String line;
		String splitBy = ",";
		String[] SLevels = {};
		ArrayList<Integer> levelList = new ArrayList<Integer>(); //changed to arraylist as they are faster to access items from. Source: https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\blued\\OneDrive - Sheffield Hallam University\\Desktop\\Java\\ADS\\Portfolio\\Project 4\\Ali's Solution\\Data Sets\\50 samples\\50 (10).csv"));
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
		Collections.sort(levelList);
		System.out.println("The elevator will go to these floors : "+levelList);
		try {
			ASCENDING(levelList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}
	
	
	public static void ASCENDING(ArrayList<Integer> floors) throws InterruptedException {
		double mean = 0;
		System.out.println("The current floor is : "+currentLevel);
		long timeStart = System.currentTimeMillis();
		ArrayList<Integer> floorTimes = new ArrayList<Integer>();
		while(!floors.isEmpty()) {
			int l = floors.get(0);
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
			floors.remove(0);
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
		mean = time/floorTimes.size();
		System.out.println("Duration : "+time+ " seconds");
		System.out.println("Mean: " + mean);
		System.out.println("Minimum: " + min);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\blued\\OneDrive - Sheffield Hallam University\\Desktop\\Java\\ADS\\Portfolio\\Project 4\\Ali's Solution\\Data Sets\\data.csv"),true));
			bw.newLine();
			bw.write(String.valueOf(mean) + ',' + String.valueOf(min) + ',' + String.valueOf(time) + ',' +String.valueOf(floorTimes.size()));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

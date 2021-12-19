package Elevetor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LevelClass {

	public static void main(String[] args) {
		
		ArrayList<Level> lvl = new ArrayList<Level>();
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int curr = 5;
		
		queue.add(2);
		queue.add(7);
		queue.add(6);
		queue.add(9);
		queue.add(1);
		System.out.println("Queue : "+queue);
		for (int i=0; i<queue.size(); i++) {
			int diff = Math.abs(queue.get(i)-curr);
			System.out.println(diff);
			Level l = new Level(diff,queue.get(i));
			lvl.add(l);
		}
		
		printLevel(lvl);
		Collections.sort(lvl,new CompareLevel());
		System.out.println("\nAfter sort : ");
		printLevel(lvl);
	}
	
	
	public static void printLevel (ArrayList<Level> lvl) {
		
		for (int i=0; i<lvl.size(); i++) {
			System.out.print("(diff: ");
			System.out.print(lvl.get(i).dif);
			System.out.print(", level: ");
			System.out.print(lvl.get(i).level);
			System.out.print(")");
		}
	}
	
	
		
}

class Level {
	
	int dif;
	int level;
	
	Level(int diff, int level){
		this.dif=diff;
		this.level=level;
	}
}



package Elevetor;

public class CompareLevel implements java.util.Comparator {
	public int compare(Object a, Object b) 
	{
		if (((Level)a).dif < ((Level)b).dif) return(-1);
		if (((Level)a).dif > ((Level)b).dif) return(1);
		return(0);
	}

}

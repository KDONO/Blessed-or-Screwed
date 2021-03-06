package testing;

import java.util.ArrayList;
import java.util.TreeMap;

import domain.Character;
import domain.Job;
import domain.Unit;
import json.DataStorage;
import logic.UnitController;

public class UnitControllerTester {

public static void main(String[]args)
{
	
	DataStorage data = DataStorage.getInstance();
	data.ParseJsonCharacters();
	data.ParseJsonJobs();	
	
	Character Silas = data.getCharacters().get("Silas");
	Job Cavalier = data.getJobs().get("Cavalier");
	double[] inputStats = {20,20,20,20,20,20,20,20};
	
	TreeMap<Integer, String> ClassHistory = new TreeMap<Integer, String>();
	ClassHistory.put(6, "Cavalier");// level 6
	ClassHistory.put(7, "Songstress");// level 7
	ClassHistory.put(8, "Songstress");// level 8
	ClassHistory.put(9, "Songstress");// level 9
	ClassHistory.put(10, "Cavalier");// level 10

	UnitController unitController = UnitController.getInstance();
	
	unitController.setCurrentChar(Silas);
	unitController.setCurrentJob(Cavalier);
	unitController.setCurrentRoute("Conquest");
	unitController.setClassHistory(ClassHistory);
	/*
	//Testing Reclass
//	unitController.reclass("Dancer", 10);
	
	//TESTING CALCULATIONS
//	unitController.buildLocalUnitSheet(0);
//	unitController.printLocalSheet();
	
	//TESTING CALCULATIONS
	unitController.buildInputUnitSheet(6, inputStats);
	unitController.printInputSheet();
	double[] rating = unitController.getInputRating();
	System.out.println("PRINTING RATING ARRAY");
	System.out.println("ClassHistory size: " + ClassHistory.size());
	for(int i = 0; i<ClassHistory.size(); i++)
	{
	System.out.println("Rating: "+rating[i]);
	}
	//THIS TESTS GETINPUTSTATSPREAD
//	double[] healthOverTime = unitController.getInputStatSpread(0);
	
//	for(int i = 0; i < healthOverTime.length; i++)
//	{
//		System.out.println("HEALTH: "+healthOverTime[i]);
//	}*/
}
	


/*
Silas should look like this:
BASE
22 - 17 = 5
11 - 6 = 5
0 - 0 = 0
9 - 5 = 4
8 - 5 = 3 
7 - 3 = 4
10 - 5 = 5
5 - 3 = 2
GROWTHS
40 + 10 = 50
45 + 15 = 60
5 + 0 = 5
50 + 10 = 60
40 + 10 = 50
40 + 15 = 55
40 + 10 = 50
25 + 5 = 30
MAXs
22 + 1 = 23
15 + 0 = 15
21 + 2 = 24
20 + 0 = 20
24 - 1 = 25
22 + 0 = 22
21 - 1 = 20
 */
}

//TODO:
//Need to format decimals and check for max stats!! :O

package logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import domain.Unit;
import json.DataStorage;
import domain.Character;
import domain.ChildCharacter;
import domain.Job;

public class UnitController {

	private static UnitController instance = null;	// DataStorage singleton instance	
	DataStorage data = DataStorage.getInstance(); //This is the data
	ArrayList<Unit> localUnitSheet = new ArrayList<Unit>(); //this unitSheet is calculated to compare the input one against.
	ArrayList<Unit> inputUnitSheet = new ArrayList<Unit>(); //this is unitSheet that users will input. This is compared against localUnitSheet.
	
	//THIS IS WHERE THE CURRENT CHARACTER AND JOB ARE DEFINED AS PER THE GUI.
	public Character currentChar;
	public Job currentJob;
	public String currentRoute;
	public ArrayList<String> classHistory; //the class history, this is shared between the localUnitSheet and inputUnitSheet.
	public Unit fixedParent;
	public Unit variedParent;
	public double[] fixedParentInputStats;
	public double[] variedParentInputStats;
	public int startLevel;

	// prevents instantiation
	private UnitController() {
		
	}
	
	// establish singleton pattern - only one one instance of UnitController to exist in the project
	public static UnitController getInstance() {
		if(instance == null) {
			instance = new UnitController();
		}
		return instance;
	}

	//BUILDS A LOCALUNITSHEET
	public void buildLocalUnitSheet()
	{		
		Unit localUnit;
		if(currentChar instanceof ChildCharacter)
		{
			localUnit = new Unit((ChildCharacter)currentChar, currentJob, currentRoute, fixedParentInputStats, fixedParent, variedParentInputStats, variedParent, startLevel);
		}
		else {
			localUnit = new Unit(currentChar, currentJob, currentRoute);
		}
		addBaseClassMods(localUnit);
		localUnitSheet.clear();
		buildSheet(localUnit, classHistory, localUnitSheet, 0);
	}
	
	//BUILDS A INPUTUNITSHEET
	public void buildInputUnitSheet(int inputLevel, double[] inputStats)
	{
		ArrayList<String> inputClassHistory = new ArrayList();
		int baseLevel;
		if(currentChar.getIsChild()) {
			baseLevel = startLevel;
		}
		else {
			baseLevel = currentChar.getBaseStats().getStats(currentRoute, 0);
		}
		int levelMod = inputLevel - baseLevel;
		
		for(int i = 0; i< classHistory.size() - levelMod; i++)
		{
			inputClassHistory.add(classHistory.get(i + levelMod));
		}
		
		Unit inputUnit;
		if(currentChar instanceof ChildCharacter)
		{
			inputUnit = new Unit((ChildCharacter)currentChar, currentJob, currentRoute, fixedParentInputStats, fixedParent, variedParentInputStats, variedParent, startLevel);
		}
		else {
			inputUnit = new Unit(currentChar, currentJob, currentRoute);
		}

		inputUnit.setLevel(inputLevel);
		inputUnit.setBaseStats(inputStats);		
		inputUnitSheet.clear();
		buildSheet(inputUnit, inputClassHistory, inputUnitSheet, 0);
	}

	
//=================================================================THIS IS THE MATH ZONE. BEWARE
	
	//RECURSION WHAT :O
	public void buildSheet(Unit unit, ArrayList<String> inputClassHistory, ArrayList<Unit> inputSheet, int i)
	{
		Unit newUnit = (Unit)deepClone(unit); //this is the deep clone. Very important!!
		
		if(i == inputClassHistory.size()) //ends recursion
		{
			System.out.println("Complete"); 
		}
		else if(i == 0) //adds an initial base unit if list is empty
		{
			inputSheet.add(newUnit);
			i++;
			buildSheet(newUnit, inputClassHistory, inputSheet, i);
		}
		else //this does the fun stuff!
		{
			// do we need to reclass
			checkJob(newUnit, inputClassHistory, i);
			// calculate the average stat for the correct level
			CalculateAverageStats(newUnit, (newUnit.getLevel()+1));
			inputSheet.add(newUnit);
			// increase the level of the unit by 1 so we can map out each level
			newUnit.setLevel(newUnit.getLevel()+1);
			i++;
			buildSheet(newUnit, inputClassHistory, inputSheet, i);
		}
	}
	
	//This adds the base class mods
	public void addBaseClassMods(Unit unit)
	{
		for(int i = 0; i<unit.getBaseStats().length;i++)
		{
			double[] baseStats = unit.getBaseStats();
			int[] Mods = data.getJobs().get(unit.getMyJob().getName()).getBaseStats();
			
			baseStats[i] = baseStats[i] + Mods[i];
			
			unit.setBaseStats(baseStats);
		}
	}

	//calculates averagestats for a unit
	public void CalculateAverageStats(Unit inputUnit, int inputLevel)
	{
		int levelDifference = (inputLevel - inputUnit.getLevel());
		
		if(levelDifference < 0)
		{
			levelDifference = 0;
		}
		
		double[] tempBaseStats = inputUnit.getBaseStats();
		
		for(int q = 0; q < tempBaseStats.length; q++)
		{
			System.out.println(tempBaseStats[q]);
		}
		double[] tempGrowths = inputUnit.getGrowths();
		
		for(int i = 0; i< inputUnit.getBaseStats().length; i++)
		{
			tempBaseStats[i] += ((tempGrowths[i]/100) * levelDifference);
		}

	}
	
	//this checks the job, and adjusts base, growths, and maxes accordingly.
	public void checkJob(Unit unit, ArrayList<String> inputClassHistory, int i)
	{
		if(inputClassHistory.get(i).equals(unit.getMyJob().getName()) != true)
		{
			Job newJob = data.getJobs().get(inputClassHistory.get(i));
			Job oldJob = unit.getMyJob();
			
			int[] newStatMods = newJob.getBaseStats();
			int[] oldStatMods = oldJob.getBaseStats();
			
			double[] unitBaseStats = unit.getBaseStats();
			
			for(int j = 0; j < newStatMods.length; j++)
			{
				newStatMods[j]-=oldStatMods[j];
				unitBaseStats[j]+=newStatMods[j];
			}
			unit.reClass(newJob);
		}
	}

	//Code for performing a deep clone
	public static Object deepClone(Object object) 
	{
	    try {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(object);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      ObjectInputStream ois = new ObjectInputStream(bais);
	      return ois.readObject();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	}
	
//===========================================================STUFF FOR GUI AND TESTING
	
	//Method for reclassing, only affects jobHistory
	public void reclass(String newJob, int changeLevel)
	{
		int levelVector = changeLevel-currentChar.getBaseStats().getStats(currentRoute, 0);
		
		// we cannot have a negative level difference
		if(levelVector < 0)
		{
			levelVector = 0;
		}
		
		for(int i = 0; i<(classHistory.size()-levelVector); i++)
		{
			classHistory.set((levelVector+i), newJob);
		}
	}

	//This returns an of stats from the local sheet
	//REFERENCE: 0=HP, 1=STR, 2=MAG, 3=SPD, 4=SKL, 5=LUK, 6=DEF, 7=RES 
	public double[] getLocalStatSpread(int stat)
	{
		double[] output = new double[localUnitSheet.size()];
		
			for(int i = 0; i<localUnitSheet.size();i++)
			{
				double[] tempArray = localUnitSheet.get(i).getBaseStats();
				output[i] = tempArray[stat];
			}	
			
		return output;
	}
	
	public double[] getInputStatSpread(int stat)
	{
		double[] output = new double[inputUnitSheet.size()];
		
			for(int i = 0; i<inputUnitSheet.size();i++)
			{
				double[] tempArray = inputUnitSheet.get(i).getBaseStats();
				output[i] = tempArray[stat];
			}	
			
		return output;
	}
	//PRINTER METHOD FOR TESTING 
	public void printLocalSheet()
	{
		for(int i = 0; i<localUnitSheet.size();i++)
		{
			System.out.println("--PRINTING LOCAL SHEET--");
			System.out.println("--------------------------------------------");
			localUnitSheet.get(i).printUnit();	
		}
		
	}
	
	//PRINTER METHOD FOR TESTING 
	public void printInputSheet()
	{
		for(int i = 0; i<inputUnitSheet.size();i++)
		{
			System.out.println("--PRINTING INPUT SHEET--");
			System.out.println("--------------------------------------------");
			inputUnitSheet.get(i).printUnit();	
		}
		
	}
	
//ALL GETTERS/SETTERS
	public ArrayList<Unit> getLocalUnitSheet() {
		return localUnitSheet;
	}

	public ArrayList<Unit> getInputUnitSheet() {
		return inputUnitSheet;
	}

	public Character getCurrentChar() {
		return currentChar;
	}

	public void setCurrentChar(Character currentChar) {
		this.currentChar = currentChar;
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(String currentRoute) {
		this.currentRoute = currentRoute;
	}

	public ArrayList<String> getClassHistory() {
		return classHistory;
	}

	public String[] getClassArray(int baseLevel) {
		String[] newClassHistory = new String[classHistory.size()];
			for(int i = 0; i< classHistory.size();i++)
			{
				newClassHistory[i] = "Lvl "+(baseLevel+i)+". "+classHistory.get(i);
			}
		return newClassHistory;
	}
	public void setClassHistory(ArrayList<String> classHistory) {
		this.classHistory = classHistory;
	}
	
	public void setFixedParent(Unit fixedParent) {
		this.fixedParent = fixedParent;
	}

	public void setVariedParent(Unit variedParent) {
		this.variedParent = variedParent;
	}

	public void setFixedParentInputStats(double[] fixedParentInputStats) {
		this.fixedParentInputStats = fixedParentInputStats;
	}

	public void setVariedParentInputStats(double[] variedParentInputStats) {
		this.variedParentInputStats = variedParentInputStats;
	}
	
	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}
}


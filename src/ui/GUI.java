package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import json.DataStorage;
import logic.UnitController;

public class GUI extends JFrame{

//ALL VARIABLE DECLARATIONS
	
		//InputPanel
		JLabel inputChar;
		JLabel inputRoute;
		JLabel inputLevel;
		JLabel inputHP;
		JLabel inputStr;
		JLabel inputMag;
		JLabel inputSkl;
		JLabel inputSpd;
		JLabel inputLuk;
		JLabel inputDef;
		JLabel inputRes;
	
		JComboBox inputCharBox;
		JComboBox inputRouteBox;
		JComboBox inputLevelBox;

		JTextField inputHPField;
		JTextField inputStrField;
		JTextField inputMagField;
		JTextField inputSklField;
		JTextField inputSpdField;
		JTextField inputLukField;
		JTextField inputDefField;
		JTextField inputResField;

//ResultPanel
		JLabel resultHP;
		JLabel resultStr;
		JLabel resultMag;
		JLabel resultSkl;
		JLabel resultSpd;
		JLabel resultLuk;
		JLabel resultDef;
		JLabel resultRes;
		
		JTextField resultLevelField;
		JTextField resultHPField;
		JTextField resultStrField;
		JTextField resultMagField;
		JTextField resultSklField;
		JTextField resultSpdField;
		JTextField resultLukField;
		JTextField resultDefField;
		JTextField resultResField;
		
		JTextField resultLevelDifference;
		JTextField resultHPDifference;
		JTextField resultStrDifference;
		JTextField resultMagDifference;
		JTextField resultSklDifference;
		JTextField resultSpdDifference;
		JTextField resultLukDifference;
		JTextField resultDefDifference;
		JTextField resultResDifference;
		
//GraphPanel
		JLabel graphLevel;
		JLabel graphStat;
		JComboBox graphLevelBox;
		JComboBox graphStatBox;
		JButton calculateButton;
		JButton optionsButton;
		
//Option Window
		JDialog optionPane;
		JButton reclassButton;
		JButton promoteButton;
		JButton confirmButton;
		JButton eternalSealButton;
		JList jobHistory;
	//reclassWindow
		JDialog reclassPane;
		JLabel reclassLevel;
		JLabel reclassClass;
		JComboBox reclassLevelBox;
		JList reclassClasses;
		JButton reclassConfirm;
		JButton reclassCancel;
	//promoteWindow
	//EternalSeal window mayyybe?
		
//OTHER STUFF
static String[] routes = {"Conquest", "Birthright", "Revelations"};

static String[] conquestCharacters = {"Avatar", "Silas", "Azura", "Felicia", "Jacob", "Kaze", "Mozu", "Shura", "Izana",
		"Elise", "Arthur", "Effie", "Odin", "Niles", "Nyx", "Camilla", "Selena", "Beruka", "Laslow",
		"Best Girl", "Benny", "Charlotte", "Leo", "Keaton", "Xander", "Flora"};

static String[] birthrightCharacters = {"Avatar", "Silas", "Azura", "Felicia", "Jacob", "Kaze", "Mozu", "Shura", "Izana",
		"Rinkah", "Sakura", "Hana", "Subaki", "Saizo", "Orochi", "Hinoka", "Azama", "Setsuna",
		"Hayato", "Oboro", "Hinata", "NOHRIAN SCUM!", "Kagero", "Reina", "Kaden", "Ryoma", "Scarlet", "Yukimura"};

static String[] revelationsCharacters= {"Avatar", "Silas", "Azura", "Felicia", "Jacob", "Kaze", "Mozu", "Shura", "Izana",
		"Rinkah", "Sakura", "Hana", "Subaki", "Saizo", "Orochi", "Hinoka", "Azama", "Setsuna",
		"Hayato", "Oboro", "Hinata", "NORHIAN SCUM!", "Kagero", "Reina", "Kaden", "Ryoma", "Scarlet", "Yukimura",
		"Elise", "Arthur", "Effie", "Odin", "Niles", "Nyx", "Camilla", "Selena", "Beruka", "Laslow",
		"Best Girl", "Benny", "Charlotte", "Leo", "Keaton", "Xander", "Flora", "Fuga"};

static String[] jobs = {"Songstress"};
		
public static void main(String[]args)
{
	new GUI();
}

public GUI()
{	
	UnitController unitController = UnitController.getInstance();
	DataStorage data = DataStorage.getInstance();
	data.ParseJsonCharacters();
	data.ParseJsonJobs();
	
	//Main Panel
	JPanel mainPanel = new JPanel();
	
	//Input Panel 1 (Contains Character, Job, and Route modifiers)
	JPanel inputPanel1 = new JPanel();
	
		inputRoute = new JLabel("Route: ");
		inputRouteBox = new JComboBox(routes);
		ComboBoxHandler ComboBoxHandler = new ComboBoxHandler();
		inputRouteBox.addActionListener(ComboBoxHandler);
			inputPanel1.add(inputRoute);
			inputPanel1.add(inputRouteBox);
		
		inputChar = new JLabel("Char: ");
		inputCharBox = new JComboBox(conquestCharacters);
		CharBoxHandler CharBoxHandler = new CharBoxHandler();
		inputCharBox.addActionListener(CharBoxHandler);
			inputPanel1.add(inputChar);
			inputPanel1.add(inputCharBox);
								
		inputLevel = new JLabel("Level: ");
		inputLevelBox = new JComboBox();
		LevelBoxHandler LevelBoxHandler = new LevelBoxHandler();
		inputLevelBox.addActionListener(LevelBoxHandler);
			inputPanel1.add(inputLevel);
			inputPanel1.add(inputLevelBox);

	//Input Panel 2 (Contains all the stat mods)
	JPanel inputPanel2 = new JPanel();
	inputPanel2.setLayout(new GridLayout(4,4));
	
		inputHP = new JLabel("HP: ");
		inputHPField = new JTextField(" ");
			inputPanel2.add(inputHP);
			inputPanel2.add(inputHPField);	
	
		inputSpd = new JLabel("Spd: ");
		inputSpdField = new JTextField(" ");
			inputPanel2.add(inputSpd);
			inputPanel2.add(inputSpdField);		
			
		inputStr = new JLabel("Str: ");
		inputStrField = new JTextField(" ");
			inputPanel2.add(inputStr);
			inputPanel2.add(inputStrField);	
			
		inputLuk = new JLabel("Luk: ");
		inputLukField = new JTextField(" ");
			inputPanel2.add(inputLuk);
			inputPanel2.add(inputLukField);				
			
		inputMag = new JLabel("Mag: ");
		inputMagField = new JTextField(" ");
			inputPanel2.add(inputMag);
			inputPanel2.add(inputMagField);
			
		inputDef = new JLabel("Def: ");
		inputDefField = new JTextField(" ");
			inputPanel2.add(inputDef);
			inputPanel2.add(inputDefField);			
			
		inputSkl = new JLabel("Skl: ");
		inputSklField = new JTextField(" ");
			inputPanel2.add(inputSkl);
			inputPanel2.add(inputSklField);	
			
		inputRes = new JLabel("Res: ");
		inputResField = new JTextField(" ");
			inputPanel2.add(inputRes);
			inputPanel2.add(inputResField);	
			
	//Result Panel
	JPanel resultPanel = new JPanel();
	resultPanel.setLayout(new GridLayout(4,4));
	
		resultHP = new JLabel("HP: ");
		resultHPField = new JTextField(" ", 2);
		resultHPDifference = new JTextField(" ", 2);
			resultPanel.add(resultHP);
			resultPanel.add(resultHPField);	
			resultPanel.add(resultHPDifference);
		
		resultSpd = new JLabel("Spd: ");
		resultSpdField = new JTextField(" ", 2);
		resultSpdDifference = new JTextField(" ", 2);
			resultPanel.add(resultSpd);
			resultPanel.add(resultSpdField);	
			resultPanel.add(resultSpdDifference);
		
		resultStr = new JLabel("Str: ");
		resultStrField = new JTextField(" ", 2);
		resultStrDifference = new JTextField(" ", 2);
			resultPanel.add(resultStr);
			resultPanel.add(resultStrField);	
			resultPanel.add(resultStrDifference);

		resultLuk = new JLabel("Luk: ");
		resultLukField = new JTextField(" ", 2);
		resultLukDifference = new JTextField(" ", 2);
			resultPanel.add(resultLuk);
			resultPanel.add(resultLukField);	
			resultPanel.add(resultLukDifference);			
		
		resultMag = new JLabel("Mag: ");
		resultMagField = new JTextField(" ", 2);
		resultMagDifference = new JTextField(" ", 2);
			resultPanel.add(resultMag);
			resultPanel.add(resultMagField);	
			resultPanel.add(resultMagDifference);			
		
		resultDef = new JLabel("Def: ");
		resultDefField = new JTextField(" ", 2);
		resultDefDifference = new JTextField(" ", 2);
			resultPanel.add(resultDef);
			resultPanel.add(resultDefField);	
			resultPanel.add(resultDefDifference);				

		resultSkl= new JLabel("Skl: ");
		resultSklField = new JTextField(" ", 2);
		resultSklDifference = new JTextField(" ", 2);
			resultPanel.add(resultSkl);
			resultPanel.add(resultSklField);	
			resultPanel.add(resultSklDifference);	
			
		resultRes= new JLabel("Res: ");
		resultResField = new JTextField(" ", 2);
		resultResDifference = new JTextField(" ", 2);
			resultPanel.add(resultRes);
			resultPanel.add(resultResField);	
			resultPanel.add(resultResDifference);	
	
	//GRAPH PANEL 1
	JPanel graphPanel = new JPanel();
		graphStat = new JLabel("Stat: ");
		String[] statArray = {"HP", "Str", "Mag", "Skl", "Spd", "Luk", "Def", "Res"};
		graphStatBox = new JComboBox(statArray);
			graphPanel.add(graphStat);
			graphPanel.add(graphStatBox);
		//note level array is temporary
		graphLevel= new JLabel("Level: ");
		graphLevelBox = new JComboBox();
			graphPanel.add(graphLevel);
			graphPanel.add(graphLevelBox);
	
	//GRAPH PANEL 2
	JPanel graphPanel2 = new JPanel();
	Border graphBorder2 = BorderFactory.createTitledBorder("Graph");
	graphPanel2.setBorder(graphBorder2);
	
	//Organize minipanels
	
	JPanel mainInputPanel = new JPanel();
	
	Border inputBorder = BorderFactory.createTitledBorder("Input");
		mainInputPanel.setLayout(new BoxLayout(mainInputPanel, BoxLayout.PAGE_AXIS));
		mainInputPanel.setBorder(inputBorder);
			mainInputPanel.add(inputPanel1);
			mainInputPanel.add(inputPanel2);
	Border resultBorder = BorderFactory.createTitledBorder("Results");
		resultPanel.setBorder(resultBorder);

	JPanel leftSide = new JPanel();
	leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.PAGE_AXIS));
		leftSide.add(mainInputPanel);
	JPanel optionsButtonPanel = new JPanel();
		optionsButton = new JButton("Character Options");
		OpenOptionButtonHandler OptionButtonHandler = new OpenOptionButtonHandler();
		optionsButton.addActionListener(OptionButtonHandler);
			optionsButtonPanel.add(optionsButton);
		leftSide.add(optionsButtonPanel);
		leftSide.add(resultPanel);
	
	JPanel rightSide = new JPanel();
	rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.PAGE_AXIS));
	Border rightBorder= BorderFactory.createTitledBorder("Graph Results");
		rightSide.setBorder(rightBorder);
		rightSide.add(graphPanel);
		rightSide.add(graphPanel2);
	
//CALCULATE BUTTON		
	calculateButton = new JButton("Calculate!");
	CalculateButtonHandler CalculateButtonHandler = new CalculateButtonHandler();
	calculateButton.addActionListener(CalculateButtonHandler);
		rightSide.add(calculateButton);
		
	mainPanel.add(leftSide);
	mainPanel.add(rightSide);
	
	//Main Window
	this.setSize(650,325);
	this.setTitle("Blessed or Screwed!");
	this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.add(mainPanel);
	this.setVisible(true);

//CHARACTER OPTIONS DIALOG
	optionPane = new JDialog(this, true);
	
	//ListPanel
	JPanel listPanel = new JPanel();
	Border listBorder = BorderFactory.createTitledBorder("Job History");
	listPanel.setBorder(listBorder);

//Initializes Job History
		jobHistory = new JList();
		jobHistory.setSize(new Dimension(250,250));
		jobHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
		listPanel.add(new JScrollPane(jobHistory));

	//ModifierPanel
	JPanel modPanel = new JPanel();
	Border modBorder = BorderFactory.createTitledBorder("Options");
		modPanel.setBorder(modBorder);
	modPanel.setLayout(new BoxLayout(modPanel, BoxLayout.PAGE_AXIS));
	reclassButton = new JButton("Reclass");
		ReclassOptionButtonHandler ReclassOptionButtonHandler = new ReclassOptionButtonHandler();
		reclassButton.addActionListener(ReclassOptionButtonHandler);
			modPanel.add(reclassButton);	
	promoteButton = new JButton("Promote");
		PromoteOptionButtonHandler PromoteOptionButtonHandler = new PromoteOptionButtonHandler();
		promoteButton.addActionListener(PromoteOptionButtonHandler);
			modPanel.add(promoteButton);
	eternalSealButton = new JButton("EternalSeal");
		EternalSealButtonHandler EternalSealButtonHandler = new EternalSealButtonHandler();
		eternalSealButton.addActionListener(EternalSealButtonHandler);
			modPanel.add(eternalSealButton);
	confirmButton = new JButton("Confirm");
		CloseOptionButtonHandler CloseOptionButtonHandler = new CloseOptionButtonHandler();
		confirmButton.addActionListener(CloseOptionButtonHandler);
			modPanel.add(confirmButton);
	
//RECLASS PANEL
	reclassPane  = new JDialog(optionPane, true);
	JPanel reClassPanel = new JPanel();
	
	JPanel ReClassLevelPanel = new JPanel();
		reclassLevel = new JLabel("Level:");
		reclassLevelBox = new JComboBox();
		ReClassLevelPanel.add(reclassLevel);
		ReClassLevelPanel.add(reclassLevelBox);
	JPanel ReClassClassPanel = new JPanel();
		reclassClass = new JLabel("Class: ");
		//TEMPORARY, THE CHRACTER OPTION BOX SHOULD HANDLE THIS SOMEHOW...
		Object[] reclassJobs= jobs;
		reclassClasses = new JList(reclassJobs);
		reclassClasses.setSelectedIndex(0);
		ReClassClassPanel.add(reclassClass);
		ReClassClassPanel.add(new JScrollPane(reclassClasses));
	JPanel ReClassButtonPanel = new JPanel();
		reclassConfirm = new JButton("Confirm");
		ReClassConfirmButtonHandler ReClassConfirmButtonHandler = new ReClassConfirmButtonHandler();
		reclassConfirm.addActionListener(ReClassConfirmButtonHandler);
		ReClassButtonPanel.add(reclassConfirm);
		reclassCancel = new JButton("Cancel");
		ReClassCancelButtonHandler ReClassCancelButtonHandler = new ReClassCancelButtonHandler();
		reclassCancel.addActionListener(ReClassCancelButtonHandler);
		ReClassButtonPanel.add(reclassCancel);
		
	reClassPanel.add(ReClassLevelPanel);
	reClassPanel.add(ReClassClassPanel);
	reClassPanel.add(ReClassButtonPanel);
	reClassPanel.setLayout(new BoxLayout(reClassPanel, BoxLayout.PAGE_AXIS));
	reclassPane.add(reClassPanel);
	reclassPane.setTitle("Reclass");
	reclassPane.setResizable(true);
	reclassPane.setSize(300,300);
	reclassPane.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

	//Add all Components
	optionPane.setLayout(new FlowLayout());
	optionPane.add(listPanel);
	optionPane.add(modPanel);
	
	//Window
	optionPane.setTitle("Character Options");
	optionPane.setResizable(true);
	optionPane.setSize(600,300);
	optionPane.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //not sure if this is right, will check when testing
	
	//SETS DEFAULT UNIT TO SILAS... FOR DEBUGGING FOR NOW...
	inputCharBox.setSelectedIndex(1);

}

//ALL THE BUTTON HANDLERS

//OPTIONWINDOW HANDLERS
	//OPEN OPTIONS WINDOW BUTTON
	public class OpenOptionButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			optionPane.setVisible(true);
		}
		
	}
	//RECLASS OPTIONS WINDOW BUTTON
	public class ReclassOptionButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			reclassPane.setVisible(true);
		}
		
	}
		//Reclass Confirm
			public class ReClassConfirmButtonHandler implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					UnitController unitcontroller = UnitController.getInstance();
					int newLevel = Integer.parseInt(reclassLevelBox.getSelectedItem().toString()) ;
					String newJob = reclassClasses.getSelectedValue().toString();

					unitcontroller.reclass(newJob, newLevel);
					
					Object[] listData = unitcontroller.getClassArray();
					jobHistory.setListData(listData);
					
					reclassLevelBox.setBackground(Color.WHITE);
					reclassPane.dispose();
				}
			}
		//Reclass Close
			public class ReClassCancelButtonHandler implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					reclassPane.dispose();
				}
			}
	//PROMOTE OPTIONS WINDOW BUTTON
	public class PromoteOptionButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		}
		
	}
	//ETERNAL SEAL OPTIONS WINDOW BUTTON
	public class EternalSealButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		}
		
	}
	//CLOSE OPTIONS WINDOW BUTTON
	public class CloseOptionButtonHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		optionPane.dispose();
	}
	
}

//MAIN WINDOW HANDLERS
	//CALCULATE BUTTON
	public class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int HP = 0;
			int Str = 0;
			int Mag = 0;
			int Skl = 0;
			int Spd = 0;
			int Luk = 0;
			int Def = 0;
			int Res = 0;
			
			UnitController unitcontroller = UnitController.getInstance();
			
			try
			{
				HP = Integer.parseInt(inputHPField.getText());
				Str = Integer.parseInt(inputStrField.getText());
				Mag = Integer.parseInt(inputMagField.getText());
				Skl = Integer.parseInt(inputSklField.getText());
				Spd = Integer.parseInt(inputSpdField.getText());
				Luk = Integer.parseInt(inputLukField.getText());
				Def = Integer.parseInt(inputDefField.getText());
				Res = Integer.parseInt(inputResField.getText());
			}
			catch(NumberFormatException f)
			{
				JOptionPane.showMessageDialog(GUI.this, "Please enter a number for the stats", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			int inputLevel = Integer.parseInt(inputLevelBox.getSelectedItem().toString()) ;
			double[]inputStats = {HP, Str, Mag, Skl, Spd, Luk, Def,Res};
			
			unitcontroller.buildInputUnitSheet(inputLevel, inputStats);
			unitcontroller.buildLocalUnitSheet();
			
			unitcontroller.printLocalSheet();
			System.out.println("==================================");
			unitcontroller.printInputSheet();
		}
		
	}
	//THIS HANDLER CHANGES THE LEVEL IN UNITCONTROLLER
	public class LevelBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			graphLevelBox.setModel(inputLevelBox.getModel());
		}
	}
	//THIS HANDLER CHANGES THE CHARACTERS IN THE CHARACTER COMBO BOX BASED ON WHATS IN THE ROUTE BOX.
	public class ComboBoxHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(inputRouteBox.getSelectedItem() == "Conquest")
			{
			    inputCharBox.setModel(new DefaultComboBoxModel(conquestCharacters));
			}
			else if(inputRouteBox.getSelectedItem() == "Birthright")
			{
			    inputCharBox.setModel(new DefaultComboBoxModel(birthrightCharacters));
			}
			if(inputRouteBox.getSelectedItem() == "Revelations")
			{
			    inputCharBox.setModel(new DefaultComboBoxModel(revelationsCharacters));
			}
		}
	}
	//THIS HANDLER SHOULD SET THE INTERNAL CHARACTER 
	public class CharBoxHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		DataStorage data = DataStorage.getInstance();		
		UnitController unitcontroller = UnitController.getInstance();
		
		//Storing the Character, Job, Route, and BaseLevel
		domain.Character tempChar = data.getCharacters().get(inputCharBox.getSelectedItem().toString());
 		domain.Job tempJob = data.getJobs().get(tempChar.getBaseClass());
		String tempRoute = inputRouteBox.getSelectedItem().toString();
		int tempLevel = tempChar.getBaseStats().getStats(tempRoute, 0);
		
		//Making the class history
		ArrayList<String> tempClassHistory = new ArrayList();
		int levelMod = 0;
		for(int i = tempLevel; i<=20; i++)
		{
			String input = tempChar.getBaseClass();
			tempClassHistory.add(input);
			levelMod++;
		}
				
		//Update UnitController
		unitcontroller.setCurrentChar(tempChar);
		unitcontroller.setCurrentJob(tempJob);
		unitcontroller.setCurrentRoute(tempRoute);
		unitcontroller.setClassHistory(tempClassHistory);
		
		//Sets the stat boxes
		inputHPField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 1));
		inputStrField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 2));
		inputMagField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 3));
		inputSklField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 4));
		inputSpdField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 5));
		inputLukField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 6));
		inputDefField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 7));
		inputResField.setText(""+tempChar.getBaseStats().getStats(tempRoute, 8));
		
		//This code will set the level fields and possible classes in the character option windows
		String[] possibleLevels = new String[(20 - tempLevel + 1)];	
		for(int i = 0; i<=(20 - tempLevel); i++)
		{
			possibleLevels[i] = (i+tempLevel+"");
		}
		inputLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
		graphLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
		reclassLevelBox.setModel(new DefaultComboBoxModel(possibleLevels));
		
		Object[] listData = unitcontroller.getClassArray();
		jobHistory.setListData(listData);

		//Debug print to console
		System.out.println("Character: "+unitcontroller.getCurrentChar().getName());
		System.out.println("Base Class: "+unitcontroller.getCurrentJob().getName());
		System.out.println("Base Level: "+ unitcontroller.getCurrentChar().getBaseStats().getStats(unitcontroller.getCurrentRoute(),0));
		System.out.println("Route: "+unitcontroller.getCurrentRoute());
		for(int i = 0; i<tempClassHistory.size();i++)
		{
			System.out.println(tempClassHistory.get(i));
		}			
	}
}
}

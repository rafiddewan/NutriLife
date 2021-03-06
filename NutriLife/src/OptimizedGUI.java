import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import java.awt.*;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This class is the overall skeleton of our program
 * This class contains the graphical user interface of our program
 * @author Rafid, Matthew
 */
public class OptimizedGUI implements ActionListener {

	/**
	 * Initialize GUI
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		OptimizedGUI opGUI = new OptimizedGUI();
		opGUI.initialize();
	}
	
	//Frame of the program
	static JFrame frame; 

	/*These variables collect the data from the setFoodOptions method for the user .txt files*/
	int currentFoodGroup;
	int currentFoodID;
	int currentFoodOptionString;
	int currentNumFood;
	String currentFood;
	String currentCalories;
	String currentGramsPerServings;
	
	//These variables are used later on the program to identify indexes for arrays and loops
	static final short indexFood = FileReader.getNumberOfLines("FoodInfoList.txt");
	static final byte indexFoodGroup = 4;

	// the current username is used as a string to set each user's .txt files separately
	public static String currentUsername = "";

	/*Strings that are used for the naming convention of each user's text file with the date*/
	public static String dailyFruitConsumed = currentUsername + FileReader.getDate() + "Fruit&VegetableConsumed.txt";
	public static String dailyGrainConsumed = currentUsername + FileReader.getDate() + "GrainConsumed.txt";
	public static String dailyMilkConsumed = currentUsername + FileReader.getDate() + "Milk&AlternativesConsumed.txt";
	public static String dailyMeatConsumed = currentUsername + FileReader.getDate() + "Meat&AlernativesConsumed.txt";

	/* 
	 * JButtons 
	 */
	/*Buttons that will ALWAYS be on top of the screen when pressed*/
	JButton HomeButton = new JButton("Home");
	JButton FoodButton = new JButton("Foods");
	JButton GraphButton = new JButton("Graphs");
	JButton ProfileButton = new JButton(new ImageIcon("Icons/Profile Menu Button.png"));
	JButton LogoutButton = new JButton(new ImageIcon("Icons/Logout Menu Button.png"));
	
	/*Home Menu Buttons*/
	JButton home_FV_Button = new JButton(new ImageIcon("Icons/Fruits & Vegetables Home Page.png"));
	JButton home_ME_A_Button = new JButton(new ImageIcon("Icons/Meat & Alternatives Home Page.png"));
	JButton home_MI_A_Button = new JButton(new ImageIcon("Icons/Milk & Alternatives Home Page.png"));
	JButton home_G_BUtton = new JButton(new ImageIcon("Icons/Grains Home Page.png")); 
	
	/*Login and Registration Screen Buttons*/
	JButton loginButton = new JButton("Login"); 
	JButton registerButton = new JButton("Register"); 
	JButton finishRegistrationButton = new JButton("Finish"); 
	JButton backButton = new JButton("Back");
	
	/* Food Menu Buttons*/
	JButton food_FV_Button = new JButton(new ImageIcon("Icons/Fruits and Vegetables Food Menu.jpg")); 
	JButton food_G_Button = new JButton(new ImageIcon("Icons/Grains Food Menu.jpg"));
	JButton food_MI_A_Button = new JButton(new ImageIcon("Icons/Milk and Alternatives Food Menu.jpg"));
	JButton food_ME_A_Button = new JButton(new ImageIcon("Icons/Meat And Alternatives Food Menu.jpg")); 
	
	/* Foods */
	static JButton[][] food = new JButton[indexFoodGroup][FileReader.getNumberOfLines("FoodInfoList.txt")];
	
	/* Food Options */
	static JButton[] foodOptions = new JButton[3];

	/* Number of foods in each food group */
	final short FV = FileReader.getNumberOfLines("FruitsAndVegetables.txt");
	final short ME_A = (short) (FV + FileReader.getNumberOfLines("MeatAndAlternatives.txt"));
	final short MI_A = (short) (ME_A + FileReader.getNumberOfLines("MilkAndAlternatives.txt"));
	final short G = (short) (MI_A + FileReader.getNumberOfLines("Grains.txt"));

	/*
	 * JLabels
	 */
	
	/* Login Screen JLabels */
	JLabel welcome = new JLabel("Welcome To NutriLife!");
	JLabel login_username = new JLabel("Username");
	JLabel login_password = new JLabel("Password");
	
	/*Register Screen JLabels*/
	JLabel register_confirmPassword = new JLabel("Confirm Password");
	JLabel register_gender = new JLabel("Enter your gender");
	JLabel register_age = new JLabel("Enter your age");
	JLabel register_name = new JLabel("Enter your name");
	
	/*Login/Register errors*/
	JLabel login_typeUsername = new JLabel("Please enter a username.");
	JLabel login_typePass = new JLabel("Please enter a password.");
	JLabel login_reTypePass = new JLabel("Please give the correct password.");
	JLabel enterAge = new JLabel("Please enter your age.");
	JLabel enterGender = new JLabel("Please enter your gender.");
	JLabel enterName = new JLabel("Please enter your name.");
	JLabel enterUsername = new JLabel("Please enter a username.");
	JLabel enterPassword = new JLabel("Please enter a password.");
	JLabel enterPasswordAgain = new JLabel("Passwords are not correct!");
	
	/*Home Menu JLabels*/
	//Array of JLabels for the text in the Home Menu
	//Contains the information for all the foods
	JLabel[][][] FoodArray = new JLabel[4][3][100];
	//JLabels for the food group pages in the Home Menu
	JLabel foodChoice = new JLabel("");
	JLabel homeFood = new JLabel("Food");
	JLabel homeGrams = new JLabel("Grams");
	JLabel homeCalories = new JLabel("Calories");
	JLabel homeTotal = new JLabel("Total");
	JLabel homeTotalGrams = new JLabel();
	JLabel homeTotalCalories = new JLabel();
		
	/*JTextFields*/
	//Login - Username
	JTextField login_userID = new JTextField(20);
	//Login - Password
	JPasswordField login_passID = new JPasswordField(20);
	//Register - Username
	JTextField register_userID = new JTextField(20);
	//Register Password
	JPasswordField register_passID = new JPasswordField(20);
	//Register - Confirm password
	JPasswordField register_confirm_passID = new JPasswordField(20);
	//Register - Name
	JTextField register_nameID = new JTextField(20);

	/*
	 * JCombo Box
	 */
	JComboBox<String> list_age = new JComboBox<>(age());// User age
	JComboBox<String> list_gender = new JComboBox<>(gender());// User gender

	/*
	 * JLayeredPanes (Layers)
	 */
	
	/*LOGIN SCREEN*/
	//Login and Registration Screen
	JLayeredPane Login_Register_Menu = new JLayeredPane();
	
	/*HOME SCREEN*/
	//Home Menu
	JLayeredPane Home_Menu = new JLayeredPane();
	//Info panel
	JLayeredPane textArea = new JLayeredPane();
	
	/* Fruits and Vegetables */
	//Fruits and Vegetables - Home Menu/Info
	static JLayeredPane layeredPane_Home_FV = new JLayeredPane(); 
	//Fruits And Vegetables - Home Menu/Food
	JLayeredPane fv_home_food = new JLayeredPane(); 
	//Fruits And Vegetables - Home Menu/Grams
	JLayeredPane fv_home_grams = new JLayeredPane();
	//Fruits And Vegetables - Home Menu/Calories
	JLayeredPane fv_home_calories = new JLayeredPane();
	
	/* Meat and Alternatives */
	//Meat and Alternatives - Home Menu/Info
	static JLayeredPane layeredPane_Home_ME_A = new JLayeredPane();
	//Meat and Alternatives - Home Menu/Food
	JLayeredPane me_a_home_food = new JLayeredPane();
	//Meat and Alternatives - Home Menu/Grams
	JLayeredPane me_a_home_grams = new JLayeredPane();
	//Meat and Alternatives - Home Menu/Calories
	JLayeredPane me_a_home_calories = new JLayeredPane();
	
	/* Milk and Alternatives */
	//Milk and Alternatives - Home Menu/Info
	static JLayeredPane layeredPane_Home_MI_A = new JLayeredPane();
	//Milk and Alternatives - Home Menu/Food
	JLayeredPane mi_a_home_food = new JLayeredPane();
	//Milk and Alternatives - Home Menu/Grams
	JLayeredPane mi_a_home_grams = new JLayeredPane();
	//Milk and Alternatives - Home Menu/Calories
	JLayeredPane mi_a_home_calories = new JLayeredPane();
	
	/* Grains */
	//Grains - Home Menu/Info
	static JLayeredPane layeredPane_Home_G = new JLayeredPane();
	//Grains - Home Menu/Food
	JLayeredPane g_home_food = new JLayeredPane();
	//Grains - Home Menu/Grams
	JLayeredPane g_home_grams = new JLayeredPane();
	//Grains - Home Menu/Calories
	JLayeredPane g_home_calories = new JLayeredPane();
	
	/*FOOD SCREEN*/
	JLayeredPane Food_Menu = new JLayeredPane();//Food Menu
	/* Food Group Layers */
	// Fruits and Vegetables layer - Food Menu
	static JLayeredPane layeredPane_Food_FV = new JLayeredPane();
	// Grains layer - Food Menu
	static JLayeredPane layeredPane_Food_G = new JLayeredPane();
	// Milk and Alternatives layer - Food Menu
	static JLayeredPane layeredPane_Food_MI_A = new JLayeredPane();
	// Meat and Alternatives layer - Food Menu
	static JLayeredPane layeredPane_Food_ME_A = new JLayeredPane();
	// Options to chose once click on food
	JLayeredPane choices = new JLayeredPane();
	
	/*GRAPHS SCREEN*/
	//Graphs Menu
	JLayeredPane Graph_Menu = new JLayeredPane();	
	
	/*PROFILE SCREEN*/
	//Profile Menu
	JLayeredPane Profile_Menu = new JLayeredPane();

	/* 
	 * JScrollPanes
	 */
	
	/*FOOD SCREEN*/
	//Fruits and Vegetables - Food Menu
	static JScrollPane food_FVScroll = new JScrollPane
			(layeredPane_Food_FV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	// Meat and Alternatives - Food Menu
	static JScrollPane food_ME_AScroll = new JScrollPane
			(layeredPane_Food_ME_A, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	//Milk and Alternatives - Food Menu																							
	static JScrollPane food_MI_AScroll = new JScrollPane
			(layeredPane_Food_MI_A,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	//Grains - Food Menu
	static JScrollPane food_GScroll = new JScrollPane
			(layeredPane_Food_G, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	/*HOME SCREEN*/
	// Fruits and Vegetables - Home Menu
	static JScrollPane home_FVScroll = new JScrollPane(layeredPane_Home_FV);
	//Meat and Alternatives - Home Menu
	static JScrollPane home_ME_AScroll = new JScrollPane(layeredPane_Home_ME_A);
	//Milk and Alternatives - Home Menu
	static JScrollPane home_MI_AScroll = new JScrollPane(layeredPane_Home_MI_A);
	//Grains - Home Menu
	static JScrollPane home_GScroll = new JScrollPane(layeredPane_Home_G);
	
	/*GRAPHS SCREEN*/
	//Charts
	static JPanel charts = new JPanel();
	//Graphs - Graphs Menu
	static JScrollPane graphs = new JScrollPane(charts);

	/**
	 * Initialize the contents of the frame.
	 * @return void
	 */
	public void initialize() {

		//Sets the contents of the frame
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//Parent JlayeredPane that all the other JLayeredPanes are added onto
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(new Color(0, 0, 0));
		layeredPane.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);

		/////////////////////////////////// Login/Registration
		/////////////////////////////////// Layer//////////////////////////////////////////////////////////////

		//Login/Register Screen
		Login_Register_Menu.setForeground(Color.WHITE);
		Login_Register_Menu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Login_Register_Menu.setBackground(Color.WHITE);
		Login_Register_Menu.repaint();
		Login_Register_Menu.setBounds(10, 90, 375, 460);
		Login_Register_Menu.setVisible(true);
		layeredPane.add(Login_Register_Menu);
		
		//Welcome JLabel
		welcome.setFont(new Font("Consolas", Font.BOLD, 30));
		welcome.setBounds(10, 0, 375, 150);
		welcome.setVisible(true);
		Login_Register_Menu.add(welcome);

		/*Login screen*/
		//Username Label - Login
		login_username.setFont(new Font("Georgia", Font.BOLD, 15));
		login_username.setBounds(10, 0, 375, 280);
		login_username.setVisible(true);
		Login_Register_Menu.add(login_username);
		//Username Input - Login
		login_userID.setText("");
		login_userID.setBounds(10, 150, 300, 30);
		login_userID.setVisible(true);
		Login_Register_Menu.add(login_userID);
		//Password Label - Login
		login_password.setFont(new Font("Georgia", Font.BOLD, 15));
		login_password.setBounds(10, 50, 375, 330);
		login_password.setVisible(true);
		Login_Register_Menu.add(login_password);
		//Password Input - Login
		login_userID.setText("");
		login_passID.setBounds(10, 230, 300, 30);
		login_passID.setEchoChar('*');
		login_passID.setVisible(true);
		Login_Register_Menu.add(login_passID);
		//Login Button - Login
		loginButton.setBounds(130, 290, 90, 50);
		loginButton.addActionListener(this);
		loginButton.setVisible(true);
		Login_Register_Menu.add(loginButton);
		//Login Username error - Login
		login_typeUsername.setFont(new Font("Georgia", Font.BOLD, 10));
		login_typeUsername.setForeground(Color.RED);
		login_typeUsername.setBounds(100, 0, 375, 280);
		login_typeUsername.setVisible(false);
		Login_Register_Menu.add(login_typeUsername);
		//Login blank password error - Login
		login_typePass.setFont(new Font("Georgia", Font.BOLD, 10));
		login_typePass.setForeground(Color.RED);
		login_typePass.setBounds(100, 50, 375, 330);
		login_typePass.setVisible(false);
		Login_Register_Menu.add(login_typePass);
		//Login Password error - Login
		login_reTypePass.setFont(new Font("Georgia", Font.BOLD, 10));
		login_reTypePass.setForeground(Color.RED);
		login_reTypePass.setBounds(100, 50, 375, 330);
		login_reTypePass.setVisible(false);
		Login_Register_Menu.add(login_reTypePass);
		//Register Button - Register
		registerButton.setBounds(120, 345, 110, 50);
		registerButton.addActionListener(this);
		registerButton.setVisible(true);
		Login_Register_Menu.add(registerButton);

		
		/*Register Screen*/
		//Back Button - Register
		backButton.setBounds(10, 10, 100, 40);
		backButton.addActionListener(this);
		backButton.setVisible(false);
		Login_Register_Menu.add(backButton);
		//Name Label - Register
		register_name.setFont(new Font("Georgia", Font.BOLD, 15));
		register_name.setBounds(10, 0, 375, 200);
		register_name.setVisible(false);
		Login_Register_Menu.add(register_name);
		//Register Name error - Register
		enterName.setFont(new Font("Georgia", Font.BOLD, 10));
		enterName.setForeground(Color.RED);
		enterName.setBounds(160, 0, 375, 200);
		enterName.setVisible(false);
		Login_Register_Menu.add(enterName);
		//Name Input - Register
		register_nameID.setBounds(10, 110, 300, 30);
		register_nameID.setVisible(false);
		Login_Register_Menu.add(register_nameID);
		//Gender Label - Register
		register_gender.setFont(new Font("Georgia", Font.BOLD, 15));
		register_gender.setBounds(10, 30, 375, 243);
		register_gender.setVisible(false);
		Login_Register_Menu.add(register_gender);
		//Register Gender error - Register
		enterGender.setFont(new Font("Georgia", Font.BOLD, 10));
		enterGender.setForeground(Color.RED);
		enterGender.setBounds(100, 10, 375, 330);
		enterGender.setVisible(false);
		Login_Register_Menu.add(enterGender);
		//Gender Input - Register
		list_gender.setBounds(10, 160, 90, 30);
		list_gender.setVisible(false);
		Login_Register_Menu.add(list_gender);
		//Age Label - Register
		register_age.setFont(new Font("Georgia", Font.BOLD, 15));
		register_age.setBounds(10, 70, 375, 270);
		register_age.setVisible(false);
		Login_Register_Menu.add(register_age);
		//Register Age error - Register
		enterAge.setFont(new Font("Georgia", Font.BOLD, 10));
		enterAge.setForeground(Color.RED);
		enterAge.setBounds(75, 65, 375, 330);
		enterAge.setVisible(false);
		Login_Register_Menu.add(enterAge);
		//Age Input - Register
		list_age.setBounds(10, 215, 65, 30);
		list_age.setVisible(false);
		Login_Register_Menu.add(list_age);
		//Register Username error - Register
		enterUsername.setFont(new Font("Georgia", Font.BOLD, 10));
		enterUsername.setForeground(Color.RED);
		enterUsername.setBounds(160, 70, 375, 370);
		enterUsername.setVisible(false);
		Login_Register_Menu.add(enterUsername);
		//Username Input - Register
		register_userID.setBounds(10, 265, 300, 30);
		register_userID.setVisible(false);
		Login_Register_Menu.add(register_userID);		
		//Register Password error - Register
		enterPassword.setFont(new Font("Georgia", Font.BOLD, 10));
		enterPassword.setForeground(Color.RED);
		enterPassword.setBounds(160, 120, 375, 370);
		enterPassword.setVisible(false);
		Login_Register_Menu.add(enterPassword);
		//Password Input - Register
		register_passID.setBounds(10, 315, 300, 30);
		register_passID.setEchoChar('*');
		register_passID.setVisible(false);
		Login_Register_Menu.add(register_passID);
		//Confirm Password Label - Register
		register_confirmPassword.setFont(new Font("Georgia", Font.BOLD, 15));
		register_confirmPassword.setBounds(10, 170, 375, 370);
		register_confirmPassword.setVisible(false);
		Login_Register_Menu.add(register_confirmPassword);
		//Register  Confirm Password error - Register
		enterPasswordAgain.setFont(new Font("Georgia", Font.BOLD, 10));
		enterPasswordAgain.setForeground(Color.RED);
		enterPasswordAgain.setBounds(160, 190, 395, 330);
		enterPasswordAgain.setVisible(false);
		Login_Register_Menu.add(enterPasswordAgain);
		//Confirm Password Input - Register
		register_confirm_passID.setBounds(10, 365, 300, 30);
		register_confirm_passID.setEchoChar('*');
		register_confirm_passID.setVisible(false);
		Login_Register_Menu.add(register_confirm_passID);
		//Finish Registration Button - Register
		finishRegistrationButton.setBounds(120, 400, 110, 50);
		finishRegistrationButton.addActionListener(this);
		finishRegistrationButton.setVisible(false);
		Login_Register_Menu.add(finishRegistrationButton);
		/////////////////////////////////// Home
		/////////////////////////////////// Layer//////////////////////////////////////////////////////////////

		//Home Menu
		Home_Menu.setForeground(Color.WHITE);
		Home_Menu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Home_Menu.setBackground(Color.WHITE);
		Home_Menu.repaint();
		Home_Menu.setBounds(10, 90, 375, 460);
		Home_Menu.setVisible(false);
		layeredPane.add(Home_Menu);

		// Fruits and Vegetables Button - Home Menu
		home_FV_Button.setBounds(38, 49, 100, 100);
		home_FV_Button.addActionListener(this);
		home_FV_Button.setBorderPainted(false);
		home_FV_Button.addActionListener(this);
		Home_Menu.add(home_FV_Button);

		// Milk and Alternatives Button - Home Menu
		home_MI_A_Button.setBackground(new Color(127, 255, 212));
		home_MI_A_Button.setBounds(10, 189, 100, 100);
		home_MI_A_Button.setBorderPainted(false);
		home_MI_A_Button.addActionListener(this);
		Home_Menu.add(home_MI_A_Button);

		// Meat and Alternatives Button - Home Menu
		home_ME_A_Button.setBackground(new Color(178, 34, 34));
		home_ME_A_Button.setBounds(255, 190, 100, 100);
		home_ME_A_Button.addActionListener(this);
		home_ME_A_Button.setBorderPainted(false);
		Home_Menu.add(home_ME_A_Button);

		// Grains Button - Home Menu
		home_G_BUtton.setBackground(new Color(240, 230, 140));
		home_G_BUtton.setBounds(235, 49, 100, 100);
		home_G_BUtton.addActionListener(this);
		home_G_BUtton.setBorderPainted(false);
		Home_Menu.add(home_G_BUtton);

		/*
		 * Text area Layers
		 */
		//Fruits and Vegetables Layer
		home_FVScroll.setBounds(10, 120, 354, 400);
		layeredPane_Home_FV.setLayout(new GridLayout(1, 3, 0, 0));
		home_FVScroll.setVisible(false);
		layeredPane.add(home_FVScroll);

		//Fruits and Vegetables - Food Column
		fv_home_food.setBounds(10, 120, 354, 400);
		fv_home_food.setLayout(new BoxLayout(fv_home_food, BoxLayout.PAGE_AXIS));
		layeredPane_Home_FV.add(fv_home_food);

		//Fruits and Vegetables - Grams Column
		fv_home_grams.setBounds(10, 120, 354, 400);
		fv_home_grams.setLayout(new BoxLayout(fv_home_grams, BoxLayout.PAGE_AXIS));
		layeredPane_Home_FV.add(fv_home_grams);

		//Fruits and Vegetables - Calories Column
		fv_home_calories.setBounds(10, 120, 354, 400);
		fv_home_calories.setLayout(new BoxLayout(fv_home_calories, BoxLayout.PAGE_AXIS));
		layeredPane_Home_FV.add(fv_home_calories);

		// Meat and Alternatives Layer
		home_ME_AScroll.setBounds(10, 120, 354, 400);
		layeredPane_Home_ME_A.setLayout(new GridLayout(1, 3, 0, 0));
		home_ME_AScroll.setVisible(false);
		layeredPane.add(home_ME_AScroll);

		//Meat and Alternatives - Food Column
		me_a_home_food.setBounds(10, 120, 354, 400);
		me_a_home_food.setLayout(new BoxLayout(me_a_home_food, BoxLayout.PAGE_AXIS));
		layeredPane_Home_ME_A.add(me_a_home_food);

		//Meat and Alternatives - Grams Column
		me_a_home_grams.setBounds(10, 120, 354, 400);
		me_a_home_grams.setLayout(new BoxLayout(me_a_home_grams, BoxLayout.PAGE_AXIS));
		layeredPane_Home_ME_A.add(me_a_home_grams);

		//Meat and Alternatives - Calories Column
		me_a_home_calories.setBounds(10, 120, 354, 400);
		me_a_home_calories.setLayout(new BoxLayout(me_a_home_calories, BoxLayout.PAGE_AXIS));
		layeredPane_Home_ME_A.add(me_a_home_calories);

		//Milk and Alternatives Layer
		home_MI_AScroll.setBounds(10, 120, 354, 400);
		layeredPane_Home_MI_A.setLayout(new GridLayout(1, 3, 0, 0));
		home_MI_AScroll.setVisible(false);
		layeredPane.add(home_MI_AScroll);

		//Milk and Alternatives - Food Column
		mi_a_home_food.setBounds(10, 120, 354, 400);
		mi_a_home_food.setLayout(new BoxLayout(mi_a_home_food, BoxLayout.PAGE_AXIS));
		layeredPane_Home_MI_A.add(mi_a_home_food);

		//Milk and Alternatives - Grams Column
		mi_a_home_grams.setBounds(10, 120, 354, 400);
		mi_a_home_grams.setLayout(new BoxLayout(mi_a_home_grams, BoxLayout.PAGE_AXIS));
		layeredPane_Home_MI_A.add(mi_a_home_grams);

		//Milk and Alternatives - Calories Column
		mi_a_home_calories.setBounds(10, 120, 354, 400);
		mi_a_home_calories.setLayout(new BoxLayout(mi_a_home_calories, BoxLayout.PAGE_AXIS));
		layeredPane_Home_MI_A.add(mi_a_home_calories);

		//Grains Layer
		home_GScroll.setBounds(10, 120, 354, 400);
		layeredPane_Home_G.setLayout(new GridLayout(1, 3, 0, 0));
		home_GScroll.setVisible(false);
		layeredPane.add(home_GScroll);

		//Grains - Food Column
		g_home_food.setBounds(10, 120, 354, 400);
		g_home_food.setLayout(new BoxLayout(g_home_food, BoxLayout.PAGE_AXIS));
		layeredPane_Home_G.add(g_home_food);

		//Grains - Grams Column
		g_home_grams.setBounds(10, 120, 354, 400);
		g_home_grams.setLayout(new BoxLayout(g_home_grams, BoxLayout.PAGE_AXIS));
		layeredPane_Home_G.add(g_home_grams);

		//Grains - Calories Column
		g_home_calories.setBounds(10, 120, 354, 400);
		g_home_calories.setLayout(new BoxLayout(g_home_calories, BoxLayout.PAGE_AXIS));
		layeredPane_Home_G.add(g_home_calories);

		//Initializes all the text files for the user
		FileReader.initializeDailyFileConsumed();

		//Initializes the JLabels for each column
		for (int i = 0; i <= 3; i++) {
			initializeJLabels(i);
		}

		//Food Label
		homeFood.setFont(new Font("Consolas", Font.BOLD, 15));
		homeFood.setBounds(30, 80, 50, 50);
		homeFood.setVisible(false);
		layeredPane.add(homeFood);

		//Grams Label
		homeGrams.setFont(new Font("Consolas", Font.BOLD, 15));
		homeGrams.setBounds(120, 80, 70, 50);
		homeGrams.setVisible(false);
		layeredPane.add(homeGrams);

		//Calories Label
		homeCalories.setFont(new Font("Consolas", Font.BOLD, 15));
		homeCalories.setBounds(225, 80, 70, 50);
		homeCalories.setVisible(false);
		layeredPane.add(homeCalories);

		//Total Label
		homeTotal.setFont(new Font("Consolas", Font.BOLD, 25));
		homeTotal.setBounds(10, 520, 80, 50);
		homeTotal.setVisible(false);
		layeredPane.add(homeTotal);

		//Total Grams Label
		homeTotalGrams.setFont(new Font("Consolas", Font.BOLD, 20));
		homeTotalGrams.setBounds(120, 520, 70, 50);
		homeTotalGrams.setVisible(false);
		homeTotalGrams.setText("");
		layeredPane.add(homeTotalGrams);

		//Total Calories Label
		homeTotalCalories.setFont(new Font("Consolas", Font.BOLD, 20));
		homeTotalCalories.setBounds(225, 520, 70, 50);
		homeTotalCalories.setVisible(false);
		homeTotalCalories.setText("");
		layeredPane.add(homeTotalCalories);

		/////////////////////////// Food
		/////////////////////////// Layer//////////////////////////////////////////////////////////////////////

		//Food Menu
		Food_Menu.setBounds(10, 90, 375, 460);
		Food_Menu.setVisible(false);
		layeredPane.add(Food_Menu);

		//Fruits and Vegetables Menu - Food Menu
		food_FV_Button.setBounds(0, 0, 364, 90);
		food_FV_Button.addActionListener(this);
		Food_Menu.add(food_FV_Button);

		// Grains Button - Food Menu
		food_G_Button.setBackground(new Color(240, 230, 140));
		food_G_Button.setBounds(0, 90, 364, 90);
		food_G_Button.addActionListener(this);
		Food_Menu.add(food_G_Button);

		// Milk and Alternatives Button - Food Menu
		food_MI_A_Button.setBackground(new Color(127, 255, 212));
		food_MI_A_Button.setBounds(0, 180, 364, 90);
		food_MI_A_Button.addActionListener(this);
		Food_Menu.add(food_MI_A_Button);

		// Meat and Alternatives Button - Food Menu
		food_ME_A_Button.setBackground(new Color(178, 34, 34));
		food_ME_A_Button.setBounds(0, 270, 364, 90);
		food_ME_A_Button.addActionListener(this);
		Food_Menu.add(food_ME_A_Button);

		//Fruits and Vegetables Layer
		food_FVScroll.setBounds(10, 90, 370, 460);
		food_FVScroll.setVisible(false);
		layeredPane.add(food_FVScroll);
		layeredPane_Food_FV.setLayout(new GridLayout(FileReader.getNumberOfLines("FruitsAndVegetables.txt"), 1, 0, 20));

		//Meat and Alternatives Layer
		food_ME_AScroll.setBounds(10, 90, 370, 460);
		food_ME_AScroll.setVisible(false);
		layeredPane.add(food_ME_AScroll);
		layeredPane_Food_ME_A.setLayout(new GridLayout(FileReader.getNumberOfLines("MeatAndAlternatives.txt"), 1, 0, 20));

		//Milk and Alternatives Layer
		food_MI_AScroll.setBounds(10, 90, 370, 460);
		food_MI_AScroll.setVisible(false);
		layeredPane.add(food_MI_AScroll);
		layeredPane_Food_MI_A.setLayout(new GridLayout(FileReader.getNumberOfLines("MilkAndAlternatives.txt"), 1, 0, 20));

		//Grains Layer
		food_GScroll.setBounds(10, 90, 370, 460);
		food_GScroll.setVisible(false);
		layeredPane.add(food_GScroll);
		layeredPane_Food_G.setLayout(new GridLayout(FileReader.getNumberOfLines("Grains.txt"), 1, 0, 20));

		//Make the buttons for the layers for each food group
		for (byte i = 0; i < indexFoodGroup; i++) {
			makeButtons(i); //Creates foods for each layer at once
		}
		
		//Choice Label
		foodChoice.setFont(new Font("Ubuntu", Font.BOLD, 28));
		foodChoice.setBounds(15, 70, 250, 100);
		foodChoice.setVisible(false);
		layeredPane.add(foodChoice);

		//Choices layer
		choices.setBounds(10, 140, 364, 460);
		choices.setVisible(false);
		choices.setLayout(new GridLayout(8, 2, 10, 10));
		layeredPane.add(choices);

		//Makes the food options for all the foods
		makeFoodOptions();

		///////////////////////// Graphs
		///////////////////////// Layer/////////////////////////////////////////////////////////////////////

		// Graph Layer
		Graph_Menu.setBounds(10, 90, 364, 470);
		Graph_Menu.setVisible(false);
		layeredPane.add(Graph_Menu);

		graphs.setBounds(0, 10, 364, 460);
		charts.setLayout(new BoxLayout(charts, BoxLayout.Y_AXIS));//charts are ordered in a box layout

		//If the username is the correct username it will initialize the graphs for that user
		if (checkUsername() == true)
			initializeGraphs();

		//Add graphs to the graph menu
		Graph_Menu.add(graphs);

		///////////////////////// Profile
		///////////////////////// Layer/////////////////////////////////////////////////////////////////////
		//Profile Menu
		Profile_Menu.setBounds(10, 90, 364, 470);
		Profile_Menu.setVisible(false);
		layeredPane.add(Profile_Menu);

		//////////////////////////////////////// CONSTANT
		//////////////////////////////////////// BUTTONS///////////////////////////////////////////////////
		//Logout Button
		LogoutButton.setBackground(Color.WHITE);
		LogoutButton.setFont(new Font("Calibri", Font.PLAIN, 28));
		LogoutButton.setBounds(10, 05, 50, 50);
		LogoutButton.setContentAreaFilled(false); 
		LogoutButton.setVisible(false);
		layeredPane.add(LogoutButton);

		//Home Button
		HomeButton.setBackground(Color.BLUE);
		HomeButton.setBounds(13, 65, 120, 23);
		HomeButton.addActionListener(this);
		HomeButton.setVisible(false);
		layeredPane.add(HomeButton);

		//Food Button
		FoodButton.setBackground(Color.BLUE);
		FoodButton.setBounds(133, 65, 120, 23);
		FoodButton.addActionListener(this);
		FoodButton.setVisible(false);
		layeredPane.add(FoodButton);

		//Graph Button
		GraphButton.setBackground(Color.BLUE);
		GraphButton.setBounds(253, 65, 120, 23);
		GraphButton.addActionListener(this);
		GraphButton.setVisible(false);
		layeredPane.add(GraphButton);

		//Profile Button
		ProfileButton.setBackground(Color.WHITE);
		ProfileButton.setBounds(310, 05, 50, 50);
		ProfileButton.addActionListener(this);
		ProfileButton.setVisible(false);
		layeredPane.add(ProfileButton);

		//Title
		JLabel lblNutrilife = new JLabel(new ImageIcon("Icons/logo.png"));
		lblNutrilife.setBounds(120, 10, 160, 50);
		layeredPane.add(lblNutrilife);

	}

	/***************************
	 * ACTION LISTENER METHOD
	 *****************************************************/
	/**
	 * Action listener of the class
	 * @param ActionEvent e
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * REGISTER/LOGIN
		 */
		// Login Button
		if (e.getSource() == loginButton) {

			//Will be used to see if the inputed username is valid
			String username = "";				
			//Will be used to see if the inputed password is valid
			String password = "";

			//Variable to see if user had inputed information into textFields
			boolean textFieldsFilled = true; 

			//Must enter characters to login, cannot do it without characters
			if (login_userID.getText().equals("")) {
				textFieldsFilled = false;
				login_typeUsername.setVisible(true);
			} else {
				username = login_userID.getText(); 
				login_typeUsername.setVisible(false);
			}

			if (login_userID.getText().equals("")) {
				textFieldsFilled = false;
				login_typePass.setVisible(true);
			} else {
				password = String.valueOf(login_passID.getPassword()); 
				login_typePass.setVisible(false);
			}

			if (textFieldsFilled == true) {
				//Checks validity of password and username so that they can enter the app
				if (FileReader.checkPassword(FileReader.getInfo(username, 2), (password))) {

					login_typeUsername.setVisible(false);
					login_reTypePass.setVisible(false);
					login_typePass.setVisible(false);
					setCurrentUsername(username);

					enterApp();

				} 
				else {
					login_reTypePass.setVisible(true);
				}
			}else{
				login_reTypePass.setVisible(false);
			}
		}
		// Register Button
		if (e.getSource() == registerButton) {
			setVisibilityLoginScreen(false);
			setVisibilityRegisterScreen(true);
			login_typeUsername.setVisible(false);
			login_reTypePass.setVisible(false);
			login_typePass.setVisible(false);
		}
		
		//Back Button
		if (e.getSource() == backButton) {
			setVisibilityRegisterScreen(false);
			setVisibilityLoginScreen(true);
			enterName.setVisible(false);
			enterAge.setVisible(false);
			enterGender.setVisible(false);
			enterUsername.setVisible(false);
			enterPassword.setVisible(false);
			enterPasswordAgain.setVisible(false);
			list_age.setSelectedIndex(0);
			list_gender.setSelectedIndex(0);
		}

		if (e.getSource() == finishRegistrationButton) {
			//variables used to help check if user passed valid information to create an account
			String name = "blankname";
			String age = "";
			String gender = "";
			String username = "blankusername";
			String password = "";
			String passwordCheck = "";

			//Variable to see if user had inputed information into application and left nothing blank
			boolean textFieldsFilled = true;

			//Must enter characters to register, cannot do it without entering information
			if (register_nameID.getText().equals("")) {
				textFieldsFilled = false;
				enterName.setVisible(true);
			} else {
				name = register_nameID.getText();
				enterName.setVisible(false);
			}

			if (((String) list_age.getSelectedItem()).equals("0")) {
				textFieldsFilled = false;
				enterAge.setVisible(true);
			} else {
				age = (String) list_age.getSelectedItem();
				enterAge.setVisible(false);
			}

			if (((String) list_gender.getSelectedItem()).equals("")) {
				textFieldsFilled = false;
				enterGender.setVisible(true);
			} else {
				gender = (String) list_gender.getSelectedItem();
				enterGender.setVisible(false);
			}

			if (register_userID.getText().equals("")) {
				textFieldsFilled = false;
				enterUsername.setVisible(true);
			} else {
				username = register_userID.getText();
				enterUsername.setVisible(false);
			}

			if (String.valueOf(register_passID.getPassword()).equals("")) {
				textFieldsFilled = false;
				enterPassword.setVisible(true);
			} else {
				password = String.valueOf(register_passID.getPassword());
				enterPassword.setVisible(false);
			}

			if (String.valueOf(register_confirm_passID.getPassword()).equals("")) {
				textFieldsFilled = false;
				enterPasswordAgain.setText("Please confirm your password");
				enterPasswordAgain.setVisible(true);
			} else {
				passwordCheck = String.valueOf(register_confirm_passID.getPassword());
				enterPasswordAgain.setVisible(false);
			}
			if (!password.equals(passwordCheck)) {
				enterPasswordAgain.setText("Passwords aren't correct!");
				enterPasswordAgain.setVisible(true);
			}
			//Checks if all the information if user puts in is true
			if (textFieldsFilled == true) {
				//Checks of passwords are correct so user is eligible to create an account and enter the app
				if (password.equals(passwordCheck)) {
					enterName.setVisible(false);
					enterAge.setVisible(false);
					enterGender.setVisible(false);
					enterUsername.setVisible(false);
					enterPassword.setVisible(false);
					enterPasswordAgain.setVisible(false);
					FileReader.initializeInfo(username, name, password, age, gender);

					setCurrentUsername(username);

					enterApp();
					

					list_age.setSelectedIndex(0);
					list_gender.setSelectedIndex(0);
				}
				else{
					enterPasswordAgain.setText("Passwords aren't correct!");
					enterPasswordAgain.setVisible(true);
				}
			}
		}

		/*
		 * HOME
		 */
		// Fruits and Vegetables
		if (e.getSource() == home_FV_Button) {
			displayFood(0);
		}

		// Milk And Alternatives
		if (e.getSource() == home_MI_A_Button) {
			displayFood(2);

		}
		// Meat and Alternatives
		if (e.getSource() == home_ME_A_Button) {
			displayFood(3);

		}
		// Grains
		if (e.getSource() == home_G_BUtton) {
			displayFood(1);
		}
		
		/*
		 * FOOD MENU LAYER
		 */
		// Fruits and Vegetables (Menu)
		if (e.getSource() == food_FV_Button) {
			food_FVScroll.setVisible(true);
			layeredPane_Food_FV.setVisible(true);
			Food_Menu.setVisible(false);
		}
		// Grains (Menu)
		if (e.getSource() == food_G_Button) {
			food_GScroll.setVisible(true);
			layeredPane_Food_G.setVisible(true);
			Food_Menu.setVisible(false);
		}
		// Milk and Alternatives (Menu)
		if (e.getSource() == food_MI_A_Button) {
			food_MI_AScroll.setVisible(true);
			layeredPane_Food_MI_A.setVisible(true);
			Food_Menu.setVisible(false);
		}
		// Meat and Alternatives (Menu)
		if (e.getSource() == food_ME_A_Button) {
			food_ME_AScroll.setVisible(true);
			layeredPane_Food_ME_A.setVisible(true);
			Food_Menu.setVisible(false);
		}
		/*Creates the choices for clicking on a specific food of their choice*/
		for (int i = 0; i < indexFoodGroup; i++) {
			for (int j = 0; j < indexFood; j++) {
				if (e.getSource() == food[i][j]) {
					setVisibilityFoodGroups(false);
					choices.setVisible(true);
					setFoodOptions(i, j);
					foodChoice.setVisible(true);
				}
			}
		}
		/*Create actions when the foodOptions had been clicked*/
		for (int i = 0; i < foodOptions.length; i++) {
			if (e.getSource() == foodOptions[i]) {
				if (i == 0) {
					FileReader.setFoodServings(currentFoodGroup, currentFood, "" + (currentFoodOptionString / 3),
							"" + FileReader.calorieCounter((currentFoodOptionString / 3),
									Integer.parseInt(currentCalories)),
							"" + FileReader.servingCounter((currentFoodOptionString / 3),
									Integer.parseInt(currentGramsPerServings)));

				} else if (i == 1) {
					FileReader.setFoodServings(currentFoodGroup, currentFood, "" + (currentFoodOptionString / 3) * 2,
							"" + FileReader.calorieCounter((currentFoodOptionString / 3) * 2,
									Integer.parseInt(currentCalories)),
							"" + FileReader.servingCounter((currentFoodOptionString / 3) * 2,
									Integer.parseInt(currentGramsPerServings))

					);

				} else if (i == 2) {
					FileReader.setFoodServings(currentFoodGroup, currentFood, "" + (currentFoodOptionString / 3) * 3,
							"" + FileReader.calorieCounter((currentFoodOptionString / 3) * 3,
									Integer.parseInt(currentCalories)),
							"" + FileReader.servingCounter((currentFoodOptionString / 3) * 3,
									Integer.parseInt(currentGramsPerServings)));

				}
			}
		}
		
		/*
		 * MAIN: Buttons that will always be there
		 */
		// MAIN - Home Button
		if (e.getSource() == HomeButton) {
			displayFood(4);
			Home_Menu.setVisible(true);
			Food_Menu.setVisible(false);
			Graph_Menu.setVisible(false);
			setVisibilityFoodGroups(false);
			choices.setVisible(false);
			foodChoice.setVisible(false);

		}
		// MAIN - Food Button
		if (e.getSource() == FoodButton) {
			displayFood(4);
			Food_Menu.setVisible(true);
			Home_Menu.setVisible(false);
			Graph_Menu.setVisible(false);
			setVisibilityFoodGroups(false);
			choices.setVisible(false);
			foodChoice.setVisible(false);
		}
		// MAIN - Graph Button
		if (e.getSource() == GraphButton) {
			displayFood(4);
			Graph_Menu.setVisible(true);
			Home_Menu.setVisible(false);
			Food_Menu.setVisible(false);
			setVisibilityFoodGroups(false);
			choices.setVisible(false);
			foodChoice.setVisible(false);
		}
		
		if(e.getSource() == LogoutButton){
			displayFood(4);
			Home_Menu.setVisible(false);
			Food_Menu.setVisible(false);
			Graph_Menu.setVisible(false);
			setVisibilityFoodGroups(false);
			setVisibilityConstantButtons(false);
			choices.setVisible(false);
			foodChoice.setVisible(false);
			FileReader.setCurrentUsername("");
			Login_Register_Menu.setVisible(true);
		}
		// MAIN - Profile
		if (e.getSource() == ProfileButton) {
			displayFood(4);
			Profile_Menu.setVisible(true);
		}

	}

	/***************************
	 * LOGIN/REGISTER METHODS
	 ******************************************/
	/**
	 * Lists possible ages to choose from in JComboBox
	 * 
	 * @return String[]
	 */
	public String[] age() {
		String age[] = new String[7];
		age[0] = "0";
		age[1] = "2-3";
		age[2] = "4-8";
		age[3] = "9-13";
		age[4] = "14-18";
		age[5] = "19-50";
		age[6] = "51+";

		return age;
	}

	/**
	 * Lists possible genders to choose from in JComboBox
	 * 
	 * @return gender
	 */
	public String[] gender() {
		String gender[] = new String[] { "", "Male", "Female" };
		return gender;
	}

	/**
	 * Sets the visibility of the login screen
	 * @param visibility
	 * @return void
	 */
	public void setVisibilityLoginScreen(boolean visibillity) {
		loginButton.setVisible(visibillity);
		login_userID.setVisible(visibillity);
		login_passID.setVisible(visibillity);
		registerButton.setVisible(visibillity);
		login_username.setBounds(10, 0, 375, 280);
		login_password.setBounds(10, 50, 375, 330);
		register_userID.setText("");
		register_passID.setText("");
		register_nameID.setText("");
		register_confirm_passID.setText("");

	}

	/**
	 * Sets the visibility of the register screen
	 * @param visibility
	 * @return void
	 */
	public void setVisibilityRegisterScreen(boolean visibillity) {
		register_name.setVisible(visibillity);
		register_nameID.setVisible(visibillity);
		finishRegistrationButton.setVisible(visibillity);
		backButton.setVisible(visibillity);
		list_gender.setVisible(visibillity);
		register_gender.setVisible(visibillity);
		register_age.setVisible(visibillity);
		list_age.setVisible(visibillity);
		register_userID.setVisible(visibillity);
		register_passID.setVisible(visibillity);
		register_confirmPassword.setVisible(visibillity);
		register_confirm_passID.setVisible(visibillity);
		login_username.setBounds(10, 70, 375, 370);
		login_password.setBounds(10, 120, 375, 370);
		login_userID.setText("");
		login_passID.setText("");
	}

	/**
	 * Sets the visibility of the buttons that are at the top of the page when you enter the app
	 * 
	 * These buttons will always be there
	 * 
	 * @param visibillity
	 * @return void
	 */
	public void setVisibilityConstantButtons(boolean visibility) {
		HomeButton.setVisible(visibility);
		ProfileButton.setVisible(visibility);
		FoodButton.setVisible(visibility);
		GraphButton.setVisible(visibility);
		LogoutButton.setVisible(visibility);
	}

	/**
	 * This is a method that is called when the user enters into the app
	 * Is called when log in in, or after registering
	 * @return void
	 */
	public void enterApp() {
		Home_Menu.setVisible(true);
		setVisibilityConstantButtons(true);
		setVisibilityLoginScreen(false);
		setVisibilityRegisterScreen(false);
		login_username.setVisible(false);
		login_password.setVisible(false);
		welcome.setVisible(false);
	}

	/**
	 * Method to reinitialize the files with the name of the user, as user
	 * 
	 * Starts off blank
	 * 
	 * @return void
	 */
	public static void reinitializeFilesConsumed() {
		dailyFruitConsumed = currentUsername + FileReader.getDate() + "Fruit&VegetableConsumed.txt";
		dailyGrainConsumed = currentUsername + FileReader.getDate() + "GrainConsumed.txt";
		dailyMilkConsumed = currentUsername + FileReader.getDate() + "Milk&AlternativesConsumed.txt";
		dailyMeatConsumed = currentUsername + FileReader.getDate() + "Meat&AlernativesConsumed.txt";

		IO.openOutputFile(dailyFruitConsumed);
		IO.closeOutputFile();
		IO.openOutputFile(dailyGrainConsumed);
		IO.closeOutputFile();
		IO.openOutputFile(dailyMilkConsumed);
		IO.closeOutputFile();
		IO.openOutputFile(dailyMeatConsumed);
		IO.closeOutputFile();
	}

	/**
	 * This method takes in the username the user enters when logging in or
	 * registering and sets it as the "current username"
	 * 
	 * use of current username explained above
	 * 
	 * @param username
	 * @return void
	 */
	public static void setCurrentUsername(String username) {
		currentUsername = username;
		FileReader.setCurrentUsername(username);
	}

	/***************************
	 * HOME METHODS
	 ****************************************************/

	/**
	 * This method initializes all the JLabels needed for the individual food
	 * groups in the home page
	 * 
	 * Calculates the number of JLabels needed based on the information it
	 * collects from the txt files
	 * 
	 * @param foodType
	 * @return void
	 */
	public void initializeJLabels(int foodType) {

		FileReader.reinitializeFilesConsumed();

		JLayeredPane panel = new JLayeredPane();
		JLayeredPane panel_1 = new JLayeredPane();
		JLayeredPane panel_2 = new JLayeredPane();
		JLayeredPane panel_3 = new JLayeredPane();

		int lineNum = 1;
		String line = FileReader.getFoodServings(foodType, lineNum);

		String filePath = "";

		// this sets which panels to add the labels to based on the foodType
		switch (foodType) {
		case 0:
			filePath = dailyFruitConsumed;
			panel = layeredPane_Home_FV;
			panel_1 = fv_home_food;
			panel_2 = fv_home_grams;
			panel_3 = fv_home_calories;

			break;
		case 1:
			filePath = dailyGrainConsumed;
			panel = layeredPane_Home_ME_A;
			panel_1 = me_a_home_food;
			panel_2 = me_a_home_grams;
			panel_3 = me_a_home_calories;
			break;
		case 2:
			filePath = dailyMilkConsumed;
			panel = layeredPane_Home_MI_A;
			panel_1 = mi_a_home_food;
			panel_2 = mi_a_home_grams;
			panel_3 = mi_a_home_calories;
			break;
		case 3:
			filePath = dailyMeatConsumed;
			panel = layeredPane_Home_G;
			panel_1 = g_home_food;
			panel_2 = g_home_grams;
			panel_3 = g_home_calories;
			break;
		}

		// this is a for loop to initialize the labels
		for (int i = 0; i < FileReader.getNumberOfLines(filePath); i++) {

			FoodArray[foodType][0][lineNum] = new JLabel(FileReader.getCertainInfo(filePath, lineNum, 1));
			FoodArray[foodType][0][lineNum].setBackground(Color.black);
			FoodArray[foodType][0][lineNum].setVisible(false);

			panel_1.add(FoodArray[foodType][0][lineNum]);

			FoodArray[foodType][1][lineNum] = new JLabel(FileReader.getCertainInfo(filePath, lineNum, 2));
			FoodArray[foodType][1][lineNum].setBackground(Color.black);
			FoodArray[foodType][1][lineNum].setVisible(false);

			panel_2.add(FoodArray[foodType][1][lineNum]);

			FoodArray[foodType][2][lineNum] = new JLabel(FileReader.getCertainInfo(filePath, lineNum, 3));
			FoodArray[foodType][2][lineNum].setBackground(Color.black);
			FoodArray[foodType][2][lineNum].setVisible(false);

			panel_3.add(FoodArray[foodType][2][lineNum]);

			lineNum++;
			line = FileReader.getFoodServings(0, lineNum);
		}

	}

	/**
	 * This is a method that quickly sets all the visibility of the JLabels in a
	 * food group to true or false
	 * 
	 * @param foodType
	 * @param visibility
	 * @return void
	 */
	public void setJLabelsVisibility(int foodType, boolean visibility) {

		int lineNum = 1;

		String line = FileReader.getFoodServings(foodType, lineNum);

		if (line != null) {
			do {

				if (FoodArray[foodType][0][lineNum] == null)
					initializeJLabels(foodType);

				FoodArray[foodType][0][lineNum].setVisible(visibility);

				FoodArray[foodType][1][lineNum].setVisible(visibility);

				FoodArray[foodType][2][lineNum].setVisible(visibility);

				lineNum++;
				line = FileReader.getFoodServings(foodType, lineNum);
			} while (line != null);
		}
	}

	/**
	 * Method to set certain panels to true or false, based on the button
	 * pressed
	 * 
	 * this will set the panels in the home page to true if those buttons are
	 * pressed, or set all 4 to false if any other button is pressed
	 * 
	 * @param foodType
	 */
	public void displayFood(int foodType) {
		Home_Menu.setVisible(false);
		Food_Menu.setVisible(false);
		Graph_Menu.setVisible(false);

		for (int i = 0; i <= 3; i++) {

			setJLabelsVisibility(i, false);
			homeFood.setVisible(false);
			homeGrams.setVisible(false);
			homeCalories.setVisible(false);
			homeTotal.setVisible(false);
			homeTotalCalories.setVisible(false);
			homeTotalGrams.setVisible(false);
			home_FVScroll.setVisible(false);
			home_ME_AScroll.setVisible(false);
			home_MI_AScroll.setVisible(false);
			home_GScroll.setVisible(false);
		}

		if (foodType < 4) {

			setJLabelsVisibility(foodType, true);
			homeFood.setVisible(true);
			homeGrams.setVisible(true);
			homeCalories.setVisible(true);
			homeTotal.setVisible(true);
			homeTotalCalories.setVisible(true);
			homeTotalGrams.setVisible(true);

		}

		reinitializeGraphs();
		
		//Sets the visibility for a specific layer
		switch (foodType) {
		case 0:
			home_FVScroll.setVisible(true);
			homeTotalGrams.setText("" + FileReader.getTotalGrams(foodType));
			homeTotalCalories.setText("" + FileReader.getTotalCalories(foodType));

			break;
		case 1:
			home_ME_AScroll.setVisible(true);
			homeTotalGrams.setText("" + FileReader.getTotalGrams(foodType));
			homeTotalCalories.setText("" + FileReader.getTotalCalories(foodType));

			break;
		case 2:
			home_MI_AScroll.setVisible(true);
			homeTotalGrams.setText("" + FileReader.getTotalGrams(foodType));
			homeTotalCalories.setText("" + FileReader.getTotalCalories(foodType));

			break;

		case 3:
			home_GScroll.setVisible(true);
			homeTotalGrams.setText("" + FileReader.getTotalGrams(foodType));
			homeTotalCalories.setText("" + FileReader.getTotalCalories(foodType));

			break;
		default:
			homeTotalGrams.setText("");
			homeTotalCalories.setText("");

			break;

		}

	}

	/***************************
	 * FOOD METHODS
	 *****************************************************/
	/**
	 * Makes all the food buttons
	 */
	public void makeButtons(byte type) {
		//Finds the food groups selected
		String group = "";
		if (type == 0) {
			group = "FruitsAndVegetables.txt";
		} else if (type == 1) {
			group = "MeatAndAlternatives.txt";
		} else if (type == 2) {
			group = "MilkAndAlternatives.txt";
		} else if (type == 3) {
			group = "Grains.txt";
		}

		//Get the number of foods for a specific food group
		short foodID = FileReader.getNumberOfLines(group);
		//Loop until it creates all the buttons
		for (byte j = 1; j <= foodID; j++) {
			food[type][j] = new JButton(new ImageIcon(FileReader.getLine(group, j)));
			food[type][j].addActionListener(this);
			food[type][j].setContentAreaFilled(false);
			food[type][j].setFocusPainted(false);
			food[type][j].setBorderPainted(false);
			
			//Add the food buttons to the corresponding food group layer
			switch (type) {
			case 0:
				layeredPane_Food_FV.add(food[type][j]);
				break;
			case 1:
				layeredPane_Food_ME_A.add(food[type][j]);
				break;
			case 2:
				layeredPane_Food_MI_A.add(food[type][j]);
				break;
			case 3:
				layeredPane_Food_G.add(food[type][j]);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Creates foodOptions Buttons
	 * 
	 * @return void
	 */
	public void makeFoodOptions() {
		//Create the foodOptions for each food
		for (byte i = 0; i < foodOptions.length; i++) {
			foodOptions[i] = new JButton("");
			foodOptions[i].addActionListener(this);
			choices.add(foodOptions[i]);
		}
	}

	/**
	 * Sets the foodOptions Buttons and sets the title of the label
	 * 
	 * @param foodgroup
	 * @param foodID
	 */
	public void setFoodOptions(int foodgroup, int foodID) {
		//Certain ID of a food group
		currentFoodGroup = foodgroup;
		//Certain ID a food in a food group
		currentFoodID = foodID;
		short numFood = 0;
		//Find which food is pressed
		switch (foodgroup) {
		case 0:
			numFood = (short) foodID;
			break;
		case 1:
			numFood = (short) (FV + foodID);
			break;
		case 2:
			numFood = (short) (ME_A + foodID);
			break;
		case 3:
			numFood = (short) (MI_A + foodID);
			break;
		}
		//The information for the food chosen by the user
		String food = FileReader.getCertainInfo(FileReader.getLine("FoodInfoList.txt", numFood), 1);
		currentCalories = FileReader.getCertainInfo(FileReader.getLine("FoodInfoList.txt", numFood), 3);
		currentGramsPerServings = FileReader.getCertainInfo(FileReader.getLine("FoodInfoList.txt", numFood), 2);

		//Set the amount of food for each serving
		currentFood = food;
		foodChoice.setText(food);
		int foodOption[] = new int[3];
		foodOption[0] = FileReader.getGrams(numFood);
		foodOption[1] = foodOption[0] * 2;
		foodOption[2] = foodOption[0] * 3;

		//Create the food options
		//Sets the text on the food options buttons
		String foodOptionString;
		byte servingNumber = 1;
		String servingNumberString;
		for (byte i = 0; i < foodOption.length; i++) {
			servingNumberString = Integer.toString(servingNumber);
			foodOptionString = Integer.toString(foodOption[i]);
			currentFoodOptionString = Integer.parseInt(foodOptionString);
			if (i == 0) {
				foodOptions[i].setText(
						"Did  you eat " + foodOptionString + " many grams. (" + servingNumberString + " serving)");
			} else {
				foodOptions[i].setText(
						"Did  you eat " + foodOptionString + " many grams. (" + servingNumberString + " servings)");
			}
			servingNumber++;
		}
	}

	/**
	 * Gets the amount of grams for a specific foodGroup
	 * 
	 * @param numFood
	 * @return int
	 */
	public static int getGrams(int numFood) {
		String servings = FileReader.getCertainInfo(FileReader.getLine("FoodInfoList.txt", numFood), 2);
		int grams = Integer.parseInt(servings);
		return grams;
	}

	/**
	 * Make foodGroups invisible
	 * @return void
	 */
	public static void setVisibilityFoodGroups(boolean visibility) {
		layeredPane_Food_FV.setVisible(visibility);
		layeredPane_Food_ME_A.setVisible(visibility);
		layeredPane_Food_MI_A.setVisible(visibility);
		layeredPane_Food_G.setVisible(visibility);

		food_FVScroll.setVisible(visibility);
		food_ME_AScroll.setVisible(visibility);
		food_MI_AScroll.setVisible(visibility);
		food_GScroll.setVisible(visibility);
	}

	/***************************
	 * GRAPH METHODS
	 *****************************************************/

	/**
	 * Initializes all the graphs when the app has started
	 * @return void
	 */
	public static void initializeGraphs() {
		// +1 is for the calories graph

		FileReader.reinitializeFilesConsumed();

		for (byte i = 0; i < indexFoodGroup + 1; i++) {
			charts.add(createChart(i));
		}
		charts.repaint();
	}

	/**
	 * Reitializes all the graphs if a serving has been added or day has passed
	 * @return void
	 */
	public static void reinitializeGraphs() {

		FileReader.reinitializeFilesConsumed();

		charts.removeAll();
		initializeGraphs();

	}

	/**
	 * This is a method that allows charts to be made for the graph layer
	 * 
	 * this method will create different graphs based on the foodType received
	 * 
	 * Universal chart method
	 */
	private static ChartPanel createChart(int foodType) {

		// default settings
		Color color = new Color(1, 1, 1);
		boolean isServing = true;
		String subject = "Servings";
		int recommendedConsumption = 0;

		// if the input is not 0-3, it is not a food group serving
		// this boolean determines the choice of some of the words in the graph,
		// such as "number of servings" instead of "number of calories"
		if (foodType < 0 || foodType > 3)
			isServing = false;

		// default title
		String graphTitle = "";

		// changes settings based on foodgroup
		switch (foodType) {
		case 0:
			graphTitle = "Fruits & Vegetables";
			color = Color.GREEN;
			recommendedConsumption = FileReader.calculateDailyRecommended(0);
			break;
		case 1:
			graphTitle = "Grains ";
			color = Color.ORANGE.darker();
			recommendedConsumption = FileReader.calculateDailyRecommended(1);
			break;
		case 2:
			graphTitle = "Milk & Alternatives";
			color = Color.BLUE.brighter();
			recommendedConsumption = FileReader.calculateDailyRecommended(2);
			break;
		case 3:
			graphTitle = "Meat & Alernatives";
			color = Color.RED.darker();
			recommendedConsumption = FileReader.calculateDailyRecommended(3);
			break;
		default:
			graphTitle = "Calories";
			color = Color.WHITE;
			recommendedConsumption = FileReader.calculateRecommendedCalories();
			subject = "Calories";
			break;

		}

		XYSeries Consumption = new XYSeries("Your Progress");

		// if the input is a foodgroup(0-3), it will add the values it receives
		// from the corresponding food group txt file

		if (isServing == true) {
			for (byte i = 0; i < 7; i++) {
				// this following code adds the points onto the graph
				Consumption.add(i, FileReader.getTotalServings(FileReader.getPastFiles(foodType, i)));
			}
		}

		else {
			// otherwise, will get all the calories from all the foodgroup txt
			// files
			for (byte i = 0; i < 7; i++) {
				Consumption.add(i,
						FileReader.getTotalCalories(FileReader.getPastFiles(0, i))
								+ FileReader.getTotalCalories(FileReader.getPastFiles(1, i))
								+ FileReader.getTotalCalories(FileReader.getPastFiles(2, i))
								+ FileReader.getTotalCalories(FileReader.getPastFiles(3, i)));
			}
		}

		final XYSeries Recommended = new XYSeries("Recommended amount of " + subject);
		for (byte i = 0; i <= 7; i++) {
			// adds the points
			Recommended.add(i, recommendedConsumption);
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(Consumption);
		dataset.addSeries(Recommended);

		// Create JFreeChart
		JFreeChart FVchart = ChartFactory.createXYLineChart(
				//Title of the chart
				graphTitle + "\n Total Number of " + subject + " Consumed vs Days",
				//X-axis label
				"Days", 
				//Y-Axis label
				"Total Number of " + subject, 
				//Add the data (lines) to the chart
				dataset, 
				//Include the legend
				PlotOrientation.VERTICAL, true, 
				//Tooltips
				false,
				//URLS
				false 
		);
		FVchart.setBackgroundPaint(color);
		ChartPanel chart = new ChartPanel(FVchart);
		return chart;
	}

	/**
	 * Checks if the username is valid
	 * @return boolean
	 */
	public static boolean checkUsername() {

		if (currentUsername.equals(""))
			return false;

		else
			return true;
	}

	/************************************
	 * PROFILE METHODS
	 ******************************************/

}

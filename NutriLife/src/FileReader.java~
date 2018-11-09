import java.text.*;

//importation stuff for getting date
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
import java.util.Date;

/**
 * This class contains the overall functionality 
 * Mainly used for the interactions between txt files for users and their accounts
 * @author Matthew, Rafid
 */
public class FileReader extends IO {

	public static String currentUsername = "";

	public static String dailyFruitConsumed = currentUsername + getDate() + "Fruit&VegetableConsumed.txt";
	public static String dailyGrainConsumed = currentUsername + getDate() + "GrainConsumed.txt";
	public static String dailyMilkConsumed = currentUsername + getDate() + "Milk&AlternativesConsumed.txt";
	public static String dailyMeatConsumed = currentUsername + getDate() + "Meat&AlernativesConsumed.txt";

	/**
	 * 
	 */
	public static void reinitializeFilesConsumed() {
		dailyFruitConsumed = currentUsername + getDate() + "Fruit&VegetableConsumed.txt";
		dailyGrainConsumed = currentUsername + getDate() + "GrainConsumed.txt";
		dailyMilkConsumed = currentUsername + getDate() + "Milk&AlternativesConsumed.txt";
		dailyMeatConsumed = currentUsername + getDate() + "Meat&AlernativesConsumed.txt";

		openOutputFile(dailyFruitConsumed);
		closeOutputFile();
		openOutputFile(dailyGrainConsumed);
		closeOutputFile();
		openOutputFile(dailyMilkConsumed);
		closeOutputFile();
		openOutputFile(dailyMeatConsumed);
		closeOutputFile();

		// System.out.println("username:"+currentUsername);
		OptimizedGUI.reinitializeFilesConsumed();

	}

	public static void setCurrentUsername(String username) {
		currentUsername = username;
	}

	/**
	 * This is a method for returning the current date mainly used in other
	 * methods
	 * 
	 * @return
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd"/* "yyyy/MM/dd HH:mm:ss" */);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @return
	 */
	public static String getYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy"/* "yyyy/MM/dd HH:mm:ss" */);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @return
	 */
	public static String getMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MM"/* "yyyy/MM/dd HH:mm:ss" */);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @return
	 */
	public static String getDay() {
		DateFormat dateFormat = new SimpleDateFormat("dd"/* "yyyy/MM/dd HH:mm:ss" */);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	public static int dateConverter(String year, String month, String day) {
		int[] daysInMonth = new int[12];

		// January
		daysInMonth[0] = 31;
		// February
		// this tests for leap years
		if (Integer.parseInt(year) % 4 == 0)
			daysInMonth[1] = 29;
		else
			daysInMonth[1] = 28;
		// March
		daysInMonth[2] = 31;
		// April
		daysInMonth[3] = 30;
		// May
		daysInMonth[4] = 31;
		// June
		daysInMonth[5] = 30;
		// July
		daysInMonth[6] = 31;
		// August
		daysInMonth[7] = 31;
		// September
		daysInMonth[8] = 30;
		// October
		daysInMonth[9] = 31;
		// November
		daysInMonth[10] = 30;
		// December
		daysInMonth[11] = 31;

		int totalDays = 0;
		
		for (int index = 0; Integer.parseInt(month) - 1 > index; index++) {
			totalDays += daysInMonth[index];
		}
		totalDays += Integer.parseInt(day);

	
		return totalDays;

	}

	public static String totalDayConverter(String currentYear, int totalDays) {
		// remember to set it up to add 0s to single digit numbers

		int[] daysInMonth = new int[12];

		// January
		daysInMonth[0] = 31;
		// February
		// this tests for leap years
		if (Integer.parseInt(currentYear) % 4 == 0)
			daysInMonth[1] = 29;
		else
			daysInMonth[1] = 28;
		// March
		daysInMonth[2] = 31;
		// April
		daysInMonth[3] = 30;
		// May
		daysInMonth[4] = 31;
		// June
		daysInMonth[5] = 30;
		// July
		daysInMonth[6] = 31;
		// August
		daysInMonth[7] = 31;
		// September
		daysInMonth[8] = 30;
		// October
		daysInMonth[9] = 31;
		// November
		daysInMonth[10] = 30;
		// December
		daysInMonth[11] = 31;

		int totalMaxDays = daysInMonth[0];
		int year = Integer.parseInt(currentYear);
		int month = 1;
		int day = totalDays;
		int counter = 0;

		if (totalDays <= 0) {
			totalDays += 365;
			year--;

		}
		// else
		// day = totalDays;

		if (totalDays >= totalMaxDays) {
			do {
				day = totalDays - totalMaxDays;
				counter++;
				month++;

				// System.out.println(counter);

				totalMaxDays += daysInMonth[counter];
			} while (totalDays > totalMaxDays);
		}

		String formattedMonth, formattedDay;
		formattedMonth = String.format("%02d", month);
		formattedDay = String.format("%02d", day);

		return year + "_" + formattedMonth + "_" + formattedDay;

	}

	public static int totalDaySubtracter(int totalDays, int daysBack) {

		// still need to add something that allows this to check if it went back
		// a year or not

		int newTotalDay = 0;

		newTotalDay = totalDays - daysBack;
		if (newTotalDay > 0)
			return newTotalDay;
		else
			return newTotalDay + 365;
	}
	/**
	 * 
	 */
	public static void initializeDailyFileConsumed() {

		FileReader.reinitializeFilesConsumed();

		openOutputFile(dailyFruitConsumed);
		closeOutputFile();
		openOutputFile(dailyGrainConsumed);
		closeOutputFile();
		openOutputFile(dailyMilkConsumed);
		closeOutputFile();
		openOutputFile(dailyMeatConsumed);
		closeOutputFile();

	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param age
	 * @param gender
	 */
	public static void initializeInfo(String username, String name, String password, String age, String gender) {
		createOutputFile(username + "_Info.txt");
		println(name);
		println(password);
		println(age);
		println(gender);
		closeOutputFile();

	}

	/**
	 * This is a method that changes information for the user if infoType is 1,
	 * it will change the username if 2, it will change the password if 3, it
	 * will change the age if 4, it will change the gender (don't really need to
	 * use 4)
	 * 
	 * @param info
	 * @param infoType
	 */
	public static void resetInfo(String username, String info, int infoType) {

		getLine(username + "_Info.txt", infoType);

		String name = getInfo(username, 1);
		String password = getInfo(username, 2);
		String age = getInfo(username, 3);
		String gender = getInfo(username, 4);

		switch (infoType) {
		case 1:
			username = info;
			break;
		case 2:
			password = info;
			break;
		case 3:
			age = info;
			break;
		case 4:
			gender = info;
			break;
		default:
			System.out.println("we messed up a number: " + infoType);
			break;
		}

		openOutputFile2(username + "_Info.txt");
		println(name);
		println(password);
		println(age);
		println(gender);
		closeOutputFile();
	}

	/**
	 * This method gets user information from the UserInfo txt file This method
	 * is only used in setInfo
	 * 
	 * @param infoType
	 * @return
	 */
	public static String getInfo(String username, int infoType) {
		/**
		 * infotypes
		 * 
		 * 1=name
		 * 
		 * 2=password
		 * 
		 * 3=age
		 * 
		 * 4=gender
		 */
		return getLine(username + "_Info.txt", infoType);
	}

	/**
	 * this method takes 2 strings and checks if they are equal, if so, it will
	 * return true, if not, it will return false
	 * 
	 * @param password1
	 * @param password2
	 * @return
	 */
	public static boolean checkPassword(String password1, String password2) {

		return password1.equals(password2);

	}

	/**
	 * This method adds foods to the serving txt files by taking certain pieces
	 * of information
	 * 
	 * @param foodType
	 * @param food
	 * @param grams
	 * @param calories
	 */
	public static void setFoodServings(int foodType, String food, String grams, String calories, String servings) {

		// int foodNumber = 0;
		String filePath = "";
		int numFood = 0;

		switch (foodType) {
		case 0:
			filePath = dailyFruitConsumed;

			break;
		case 1:
			filePath = dailyMeatConsumed;

			break;
		case 2:
			filePath = dailyMilkConsumed;

			break;
		case 3:
			filePath = dailyGrainConsumed;

			break;
		}

		try {
			openOutputFile(filePath);
			// System.out.println("can open file");
		} catch (Exception e) {
			// System.out.println("cannot open file");
			createOutputFile(filePath);
			openOutputFile(filePath);

		}

		println(food + "%" + grams + "%" + calories + "%" + servings);
		closeOutputFile();
	}

	/**
	 * This gets a line of txt from the foodserving txt file used when the user
	 * wants to see their food history
	 * 
	 * @param foodType
	 * @param lineNum
	 * @return
	 */
	public static String getFoodServings(int foodType, int lineNum) {

		FileReader.reinitializeFilesConsumed();

		// System.out.println(currentUsername);

		// System.out.println(dailyFruitConsumed);

		String filePath = "";
		switch (foodType) {
		case 0:
			filePath = dailyFruitConsumed;
			break;
		case 1:
			filePath = dailyGrainConsumed;
			break;
		case 2:
			filePath = dailyMilkConsumed;
			break;
		case 3:
			filePath = dailyMeatConsumed;
			break;
		}

		return getLine(filePath, lineNum);

	}

	/**
	 * This method is used in several other methods, used to get a certain line
	 * from a txt file
	 * 
	 * @param filePath
	 * @param lineNum
	 * @return
	 */
	public static String getLine(String filePath, int lineNum) {

		openInputFile(filePath);
		closeInputFile();

		openInputFile(filePath);
		String line = "";
		for (int i = 0; i < lineNum; i++) {
			line = IO.readLine();
		}
		closeInputFile();
		return line;
	}

	/**
	 * This method returns the number of lines from a txt file
	 * 
	 * @param filePath
	 * @return
	 */
	public static short getNumberOfLines(String filePath) {
		short counter = 0;
		IO.openInputFile(filePath);
		if (IO.readLine() == null)
			return 0;

		else {
			IO.closeInputFile();
			IO.openInputFile(filePath);
			while (IO.readLine() != null)
				counter++;

			IO.closeInputFile();

			return counter;
		}
	}

	/**
	 * This method gets a certain piece of information from lines of text in the
	 * txt files
	 * 
	 * Can get food name, grams, or calories
	 * 
	 * The grams and calories are used differently based on what txt file they
	 * are from
	 * 
	 * the serving history txt files use this method to calculate servings and
	 * calories consumed
	 * 
	 * the foodinfo txt file uses this method to get how many grams are in a
	 * serving or how many calories are in 100 grams
	 * 
	 * @param filePath
	 * @param foodRow
	 * @param choice
	 * @return
	 */
	public static String getCertainInfo(String filePath, int foodRow, int choice) {

		int index;
		String line1, line2, line3, line4;
		String food, grams, calories, servings;

		line1 = getLine(filePath, foodRow);
		index = line1.indexOf("%");
		food = line1.substring(0, index);

		line2 = line1.substring(index + 1);
		index = line2.indexOf("%");
		grams = line2.substring(0, index);

		line3 = line2.substring(index + 1);
		index = line3.indexOf("%");
		calories = line3.substring(0, index);

		line4 = line3.substring(index + 1);

		servings = line4;

		switch (choice) {
		case 1:
			return food;

		case 2:
			return grams;

		case 3:
			return calories;
		case 4:
			return servings;

		default:
			return "";
		}
	}

	/**
	 * this method overloads the pervious one and lets you get one of the three
	 * choices from a string
	 * 
	 * @param line
	 * @param choice
	 * @return
	 */
	public static String getCertainInfo(String line, int choice) {

		int index;
		String line1, line2, line3;
		String food, grams, calories;

		line1 = line;
		index = line1.indexOf("%");
		food = line1.substring(0, index);

		line2 = line1.substring(index + 1);
		index = line2.indexOf("%");
		grams = line2.substring(0, index);

		line3 = line2.substring(index + 1);

		calories = line3;
		switch (choice) {
		case 1:
			return food;

		case 2:
			return grams;

		case 3:
			return calories;

		default:
			return "";
		}
	}

	/**
	 * This method gets the amount of calories in a food based on the # of grams
	 * a user has consumed
	 * 
	 * @param gramsConsumed
	 * @param calories
	 * @return
	 */
	public static int calorieCounter(int gramsConsumed, int calories) {
		double ratio = calories / 100.00;

		double a = gramsConsumed * ratio;

		int b = (int) (a);

		return b;
	}

	/**
	 * This method calculates the total calories in a foodgroup
	 * 
	 * @param foodType
	 * @return
	 */
	public static int getTotalCalories(int foodType) {

		FileReader.reinitializeFilesConsumed();

		String filePath = "";
		switch (foodType) {
		case 0:
			filePath = dailyFruitConsumed;
			break;
		case 1:
			filePath = dailyGrainConsumed;
			break;
		case 2:
			filePath = dailyMilkConsumed;
			break;
		case 3:
			filePath = dailyMeatConsumed;
			break;
		}
		int i = 1;
		int totalCalories = 0;

		// System.out.println(getNumberOfLines(filePath));

		for (int j = 0; j < getNumberOfLines(filePath); j++) {

			totalCalories = totalCalories + Integer.parseInt(getCertainInfo(filePath, i, 3));
			i++;
		}
		return totalCalories;
	}

	/**
	 * This is an overloaded method that can calculate the total calories from
	 * any files, used for past consumed food files
	 * 
	 * @param filePath
	 * @return
	 */
	public static int getTotalCalories(String filePath) {

		int i = 1;
		int totalCalories = 0;

		for (int j = 0; j < getNumberOfLines(filePath); j++) {

			totalCalories = totalCalories + Integer.parseInt(getCertainInfo(filePath, i, 3));
			i++;
		}
		return totalCalories;
	}

	/**
	 * This method gets the total servings of one food based on the grams
	 * consumed
	 * 
	 * @param gramsConsumed
	 * @param gramsPerServing
	 * @return
	 */
	public static double servingCounter(int gramsConsumed, int gramsPerServing) {
		double a = (double) gramsConsumed;
		double b = (double) gramsPerServing;
		DecimalFormat df = new DecimalFormat("#.##");
		double numServings = a / b;

		return Double.parseDouble(df.format(numServings));
	}

	/**
	 * This method calculates the total servings of a foodgroup
	 * 
	 * @param foodType
	 * @return
	 */
	public static double getTotalServings(int foodType) {

		FileReader.reinitializeFilesConsumed();

		String filePath = "";
		switch (foodType) {
		case 0:
			filePath = dailyFruitConsumed;
			break;
		case 1:
			filePath = dailyGrainConsumed;
			break;
		case 2:
			filePath = dailyMilkConsumed;
			break;
		case 3:
			filePath = dailyMeatConsumed;
			break;
		}

		double totalServings = 0;
		int i = 1;

		for (int j = 0; j < getNumberOfLines(filePath); j++) {
			totalServings = totalServings + Integer.parseInt(getCertainInfo(filePath, i, 4));
			i++;
		}

		return totalServings;
	}

	/**
	 * this is an overloaded method that allows the program to check the total
	 * servings of a filepath
	 * 
	 * this should only be used for previous consumed textfiles
	 * 
	 * @param filePath
	 * @return
	 */
	public static double getTotalServings(String filePath) {

		double totalServings = 0;
		int i = 1;

		for (int j = 0; j < getNumberOfLines(filePath); j++) {
			totalServings = totalServings + Double.parseDouble(getCertainInfo(filePath, i, 4));
			i++;
		}

		return totalServings;
	}

	/**
	 * this method calculates the total grams the user ate today based on
	 * information it automatically gets from today's txt files
	 * 
	 * @param foodType
	 * @return
	 */
	public static int getTotalGrams(int foodType) {

		FileReader.reinitializeFilesConsumed();

		String filePath = "";
		switch (foodType) {
		case 0:
			filePath = dailyFruitConsumed;
			break;
		case 1:
			filePath = dailyGrainConsumed;
			break;
		case 2:
			filePath = dailyMilkConsumed;
			break;
		case 3:
			filePath = dailyMeatConsumed;
			break;
		}
		int i = 1;
		int totalGrams = 0;

		// System.out.println(getNumberOfLines(filePath));

		for (int j = 0; j < getNumberOfLines(filePath); j++) {

			totalGrams = totalGrams + Integer.parseInt(getCertainInfo(filePath, i, 2));
			i++;
		}
		return totalGrams;
	}

	/**
	 * Overloaded method for getTotalGrams, this allows the user to get the
	 * total number of grams from any file, used when looking at past files
	 * 
	 * @param filePath
	 * @return
	 */
	public static int getTotalGrams(String filePath) {

		int i = 1;
		int totalGrams = 0;

		// System.out.println(getNumberOfLines(filePath));

		for (int j = 0; j < getNumberOfLines(filePath); j++) {

			totalGrams = totalGrams + Integer.parseInt(getCertainInfo(filePath, i, 2));
			i++;
		}
		return totalGrams;
	}

	/**
	 * Graph method
	 * 
	 * This method is used in graphs to get your files from the past 7 days,
	 * specifically your foods consumed
	 * 
	 * @param foodType
	 * @param day
	 * @return
	 */
	public static String getPastFiles(int foodType, int day) {

		String foodTypeFilePath = "";
		switch (foodType) {
		case 0:
			foodTypeFilePath = "Fruit&VegetableConsumed.txt";

			break;
		case 1:
			foodTypeFilePath = "GrainConsumed.txt";

			break;
		case 2:
			foodTypeFilePath = "Milk&AlternativesConsumed.txt";

			break;
		case 3:
			foodTypeFilePath = "Meat&AlernativesConsumed.txt";

			break;
		}

		int currentDay = dateConverter(getYear(), getMonth(), getDay());

		int[] totalDays = new int[7];
		String[] dayFiles = new String[7];

		for (int i = 0; i <= 6; i++) {
			dayFiles[i] = currentUsername + totalDayConverter(getYear(), currentDay - i) + foodTypeFilePath;
			IO.openOutputFile(dayFiles[i]);
			IO.closeOutputFile();
			// System.out.println(dayFiles[i]);
		}

		return dayFiles[6 - day];
	}

	/**
	 * Same overloaded method, but allows the user to pick how many days they
	 * want to go back by (for ex: for one month, you could go back 30 days)
	 * 
	 * current state:untested
	 * 
	 * this could be further modified to get a certain number of days, for ex,
	 * jan 1-5, 2016 by making a method that checks the difference between today
	 * and that day, then making an array for the informations between those
	 * dates
	 * 
	 * @param foodType
	 * @param day
	 * @param numDaysBack
	 * @return
	 */
	public static String getPastFiles(int foodType, int day, int numDaysBack) {

		String foodTypeFilePath = "";
		switch (foodType) {
		case 0:
			foodTypeFilePath = "Fruit&VegetableConsumed.txt";

			break;
		case 1:
			foodTypeFilePath = "GrainConsumed.txt";

			break;
		case 2:
			foodTypeFilePath = "Milk&AlternativesConsumed.txt";

			break;
		case 3:
			foodTypeFilePath = "Meat&AlernativesConsumed.txt";

			break;
		}

		int currentDay = dateConverter(getYear(), getMonth(), getDay());

		int[] totalDays = new int[numDaysBack];
		String[] dayFiles = new String[numDaysBack];

		for (int i = 0; i < numDaysBack; i++) {
			dayFiles[i] = currentUsername + totalDayConverter(getYear(), currentDay - i) + foodTypeFilePath;
			IO.openOutputFile(dayFiles[i]);
			IO.closeOutputFile();
			// System.out.println(dayFiles[i]);
		}

		return dayFiles[6 - day];
	}

	public static int calculateDailyRecommended(int foodType) {

		// NOTE this method will return 0 if the case things are not "2-3" or
		// somethihg
		// if the age is 4 or 6, it will only return 0

		// int age =Integer.parseInt(getInfo(currentUsername, 3));

		String age = getInfo(currentUsername, 3);

		String gender = getInfo(currentUsername, 4);

		// System.out.println(gender);
		// System.out.println(gender);

		// first [] is gender, 0 is female, 1 is male

		// second [] is foodtype, 0 is fruit, 1 is grain, 2 is milk, 3 is meat
		int[][] recommendedServings = new int[2][4];
		int genderNum = 0;

		if (gender.equals("Male")) {
			genderNum = 1;
		} else {
			genderNum = 0;
		}
		switch (age) {
		case "2-3":

			recommendedServings[0][0] = 4;
			recommendedServings[1][0] = 4;

			recommendedServings[0][1] = 3;
			recommendedServings[1][1] = 3;

			recommendedServings[0][2] = 2;
			recommendedServings[1][2] = 2;

			recommendedServings[0][3] = 1;
			recommendedServings[1][3] = 1;

			break;
		case "4-8":

			recommendedServings[0][0] = 5;
			recommendedServings[1][0] = 5;

			recommendedServings[0][1] = 4;
			recommendedServings[1][1] = 4;

			recommendedServings[0][2] = 2;
			recommendedServings[1][2] = 2;

			recommendedServings[0][3] = 1;
			recommendedServings[1][3] = 1;
			break;

		case "9-13":

			recommendedServings[0][0] = 6;
			recommendedServings[1][0] = 6;

			recommendedServings[0][1] = 6;
			recommendedServings[1][1] = 6;

			recommendedServings[0][2] = 4;
			recommendedServings[1][2] = 4;

			recommendedServings[0][3] = 2;
			recommendedServings[1][3] = 2;
			break;

		case "14-18":

			recommendedServings[0][0] = 7;
			recommendedServings[1][0] = 8;

			recommendedServings[0][1] = 6;
			recommendedServings[1][1] = 7;

			recommendedServings[0][2] = 4;
			recommendedServings[1][2] = 4;

			recommendedServings[0][3] = 2;
			recommendedServings[1][3] = 3;

			break;
		case "19-50":

			recommendedServings[0][0] = 8;
			recommendedServings[1][0] = 9;

			recommendedServings[0][1] = 7;
			recommendedServings[1][1] = 8;

			recommendedServings[0][2] = 2;
			recommendedServings[1][2] = 2;

			recommendedServings[0][3] = 2;
			recommendedServings[1][3] = 3;

			break;
		case "51+":

			recommendedServings[0][0] = 7;
			recommendedServings[1][0] = 7;

			recommendedServings[0][1] = 6;
			recommendedServings[1][1] = 7;

			recommendedServings[0][2] = 3;
			recommendedServings[1][2] = 3;

			recommendedServings[0][3] = 2;
			recommendedServings[1][3] = 3;

			break;

		}

		// System.out.println(recommendedServings[genderNum][foodType]);
		return recommendedServings[genderNum][foodType];

	}

	public static int calculateRecommendedCalories() {

		String age = getInfo(currentUsername, 3);

		String gender = getInfo(currentUsername, 4);

		int recommended;

		if (gender.equals("Male")) {
			recommended = 2500;
		} else {
			recommended = 2000;
		}

		return recommended;
	}
	
}



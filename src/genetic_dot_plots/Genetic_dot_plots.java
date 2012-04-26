package genetic_dot_plots;
//Program: Bioinformatics2.jsl
//Course: COSC306
//Description:  This is a program creates a 2-D array of dots and spaces, based off the sequence matches, and is
//				outlined on two sides by the sequences.  The program can either compare two user inputted strings
//				or two strings from a text file.  The program then compares the two strings and complies the 2-D
//				array based off matches or non-matches in the form of a dot plot.
//Author: Josh Moore
//Revised: 2-19-09
//Compiler: Microsoft Visual J#
//Comments: This program should cycle through the options and allow the user to select as many
//options as they want unless the program is terminated by the user.
//Notes: None
//Class: Program
//Description: Defines static variables
//******************************************************************************
public class Genetic_dot_plots
{
	static int[][] rowColumn;
	static String firstSequence;
	static String secondSequence;
	static String graphics;
	static KeyboardInputClass keyboardInput = new KeyboardInputClass();
	static TextFileClass textFile = new TextFileClass(0);

	//Class: main
	//Description: calls menu().
	//*******************************************************************************
	public static void main(String[] args)
	{
		menu(rowColumn, firstSequence, secondSequence);
	}

	//****************************************************************************** 
	//Class: menu
	//Description: creates variables user Input, option
	//Calls: enterSequences(), showResults(), showGraphics()
	//******************************************************************************* 
	public static void menu(int[][] rowColumn, String firstSequence, String secondSequence)
	{
		String userInput = "";
		System.out.println("1. Enter two sequences\n" +
			"2. Enter sequences from text file\n" +
			"3. Show results\n4. Show graphics version");
		System.out.println();
		userInput = (keyboardInput.getKeyboardInput("Please enter a menu option number (Enter 0 to exit)"));
		System.out.println();
		int option = Integer.parseInt(userInput);

		switch (option)
		{
			case 0:
				break;
			default:
				enterSequences(rowColumn, firstSequence, secondSequence, option);
				break;
			case 3:
				showResults(rowColumn, firstSequence, secondSequence);
				break;
			case 4:
				showGraphics(firstSequence, secondSequence);
				break;
		}
	}

	//****************************************************************************** 
	//Class: enterSequences
	//Description: Obtains the two strings to compare, creates and compiles the 2-D array
	//Calls: menu().
	//******************************************************************************* 
	public static void enterSequences(int[][] rowColumn, String firstSequence, String secondSequence, int option)
	{
		if (option == 1)
		{
			firstSequence = (keyboardInput.getKeyboardInput("Please enter the first sequence")).toUpperCase();
			System.out.println();
			secondSequence = (keyboardInput.getKeyboardInput("Please enter the second sequence")).toUpperCase();
			System.out.println();
		}

		if (option == 2)
		{
			textFile.getFileName("Specify the text file to be read");
			System.out.println();
			if (textFile.fileName.length() > 0)
			{
				textFile.getFileContents();
				firstSequence = textFile.text[0];
				secondSequence = textFile.text[1];
			}
		}

		int arrayRows = firstSequence.length();
		int arrayColumns = secondSequence.length();
		rowColumn = new int[arrayRows][arrayColumns];
		char dot = 183;
		char space = 32;

		for (int r = 0; r < arrayRows; r++)
		{
			for (int c = 0; c < arrayColumns; c++)
			{
				if ((secondSequence.charAt(r)) == (firstSequence.charAt(c)))
				{
					rowColumn[r][c] = dot;
				}
				else
				{
					rowColumn[r][c] = space;
				}
			}
		}
		menu(rowColumn, firstSequence, secondSequence);
	}

	//****************************************************************************** 
	//Class: showResults
	//Description: Prints out the 2-D arrays bounded on the west and north sides by the two inputted strings
	//Calls: menu().
	//******************************************************************************* 
	public static void showResults(int[][] rowColumn, String firstSequence, String secondSequence)
	{
		int arrayRows = firstSequence.length();
		int arrayColumns = secondSequence.length();

		System.out.print(" ");
		for (int j = 0; j < arrayRows; j++)
		{
			System.out.print(firstSequence.charAt(j));
		}
		System.out.println();

		for (int r = 0; r < arrayRows; r++)
		{
			System.out.print(secondSequence.charAt(r));
			for (int c = 0; c < arrayColumns; c++)
			{
				System.out.print((char)rowColumn[r][c]);
			}
			System.out.println();
		}
		System.out.println();
		menu(rowColumn, firstSequence, secondSequence);
	}

	//****************************************************************************** 
	//Class: showGraphics
	//Description: uses graphics class to create interface
	//Calls: menu().
	//******************************************************************************* 
	public static void showGraphics(String firstSequence, String secondSequence)
	{
		int arrayRows = firstSequence.length();
		int arrayColumns = secondSequence.length();
		ImageDisplay image = new ImageDisplay(arrayRows, arrayColumns, 50, "2-D Array");
		menu(rowColumn, firstSequence, secondSequence);
	}
	//****************************************************************************** 
}
//****************************************************************************** 
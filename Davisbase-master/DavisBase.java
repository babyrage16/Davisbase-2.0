import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DavisBase {

	static String prompt = "davisql> ";
	static String version = "v1.00";
	static boolean isExit = false;

	public static int pageSize = 512;

	static Scanner scan = new Scanner(System.in).useDelimiter(";");
/**
 * 
 * @param args
 */

	public static void main(String[] args) {
		Init.init();

		splashScreen();

		String cmmd = ""; 

		while(!isExit) {
			cmmd = scan.next().replace("\n", " ").replace("\r", "").trim().toLowerCase();
			parseUserCommand(cmmd);
		}
		System.out.println("Exiting the sql...");

	}

	
	public static void splashScreen() {
		System.out.println(line("-",80));
		System.out.println("Welcome to DavisBase");
		System.out.println("DavisBase Version " + version);
		System.out.println("\nType \"help;\" to display supported commands.");
		System.out.println(line("-",90));
	}
/**
 * 
 * @param s
 * @param n
 * @return
 */
	public static String line(String s,int n) {
		String ac = "";
		for(int i=0;i<n;i++) {
			ac += s;
		}
		return ac;
	}

	public static void help() {
		System.out.println(line("*",90));
		System.out.println("Below commands are supported");
		System.out.println("All commands below are case insensitive");
		System.out.println();
		System.out.println("\tSHOW TABLES;                                                 Use to display all the tables in the database.");
		System.out.println("\tCREATE TABLE table_name (<column_name datatype>);            Use to create a new table in the database.");
		System.out.println("\tINSERT INTO table_name VALUES (value1,value2,..);            Use to insert a new record into the table.");

		System.out.println("\tSELECT * FROM table_name;                                    Displays all records in the table.");
		System.out.println("\tSELECT * FROM table_name WHERE column_name operator value;   Displays records from the table where the given condition is satisfied.");
		System.out.println("\tDROP TABLE table_name;                                       Removes all the table data and its schema.");
		System.out.println("\tVERSION;                                                     Show the program version.");
		System.out.println("\tHELP;                                                        Show this help information.");
		System.out.println("\tEXIT;                                                        Exit DavisBase.");
		System.out.println();
		System.out.println();
		System.out.println(line("*",80));
	}

	/**
	 * 
	 * @param userCommand
	 */
	public static void parseUserCommand (String userCommand) {

		ArrayList<String> commandTokens = new ArrayList<String>(Arrays.asList(userCommand.split(" ")));

		switch (commandTokens.get(0)) {

		case "show":
			System.out.println("Calling the method to process the command (SHOW)");
			Parser.parseShowTable();
			break;

		case "create":
			System.out.println(" Calling the method to process the command (CREATE");
			Parser.parseCreateTable(userCommand);
			break;

		case "insert":
			System.out.println(" Calling the method to process the command (INSERT)");
			Parser.parseInsert(userCommand);
			break;

		case "delete":
			System.out.println(" Calling the method to process the command (DELETE)");
			Parser.parseDelete(userCommand);
			break;	

		case "update":
			System.out.println(" Calling the method to process the command (UPDATE)");
			Parser.parseUpdateTable(userCommand);
			break;

		case "select":
			System.out.println("STUB: Calling the method to process the command (SELECT)");
			Parser.parseSelect(userCommand);
			break;

		case "drop":
			System.out.println(" Calling the method to process the command (DROP)");
			Parser.parseDropTable(userCommand);
			break;	

		case "help":
			help();
			break;

		case "version":
			System.out.println("DavisBase Version " + version);
			
			break;

		case "exit":
			isExit=true;
			break;

		case "quit":
			isExit=true;
			break;

		default:
			System.out.println("I didn't understand the command: \"" + userCommand + "\"");
			System.out.println();
			break;
		}
	} 
}




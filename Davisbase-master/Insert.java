public class Insert {
	public static void parseinsertCommand(String insertCommand) {

		
		
		System.out.println("Command:\"" + insertCommand + "\"");

		
		String[] command = insertCommand.split(" ");
		
		String table_name = command[2];
		
		String[] temp = insertCommand.split("values");

		String temp_arr = temp[1].trim();
		
		
		String[] values_to_insert = temp_arr.substring(1, temp_arr.length() - 1).split(",");
		
		for (int j = 0; j < values_to_insert.length; j++)
			values_to_insert[j] = values_to_insert[j].trim();
		
		if (!DavisbaseUtility.tablePresent(table_name)) {
			System.out.println("Enter valid table name");
		} else {
			CreateTable.tableInsert(table_name, values_to_insert);
		}

	}

}

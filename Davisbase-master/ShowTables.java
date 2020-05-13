import java.io.RandomAccessFile;

public class ShowTables {

	

	public static void select(String name, String[] cols, String[] cmp) {
		try {

			RandomAccessFile file = new RandomAccessFile("data/" + name + ".tbl", "rw");
			
			String[] colNames = Table.getColumnNames(name);
			
			String[] dTypes = Table.getDataType(name);

			Payload payload = new Payload();

			Table.filter(file, cmp, colNames, dTypes, payload);
			payload.display(cols);
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void showTables() {
		System.out.println("Executing SHOW TABLES command");

		
		String name = "davisbase_tables";
		
		String[] cols = { "table_name" };
		
		String[] cmp = new String[0];
		select(name, cols, cmp);
	}

}

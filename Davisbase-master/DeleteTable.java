import java.io.RandomAccessFile;

public class DeleteTable {
	/**
	 * 
	 * @param deleteString
	 */
	public static void parseDeleteString(String deleteString) {
		System.out.println("DELETE");
		System.out.println("Parsing the string:\"" + deleteString + "\"");

		String[] tokens=deleteString.split(" ");
		String table = tokens[3];
		String[] temp = deleteString.split("where");
		String cmpTemp = temp[1];
		
		
		String[] comp = Parser.parserEquation(cmpTemp);
		
		if(!DavisbaseUtility.tablePresent(table)) {
			System.out.println("Table " + table + " does not exist");
		} else {
			delete(table, comp);
		}
	}
	/**
	 * 
	 * @param table
	 * @param comp
	 */
	public static void delete(String table, String[] comp){
		try{

			int key = Integer.parseInt(comp[2]);

			RandomAccessFile deleteTableFile = new RandomAccessFile("data/" + table + ".tbl", "rw");
			
			int countPage = Table.pages(deleteTableFile);
			int deletePage = 0;
			
			int currentPage = 1;
			while(currentPage <= countPage) {
				if(Page.hasKey(deleteTableFile, currentPage, key) && 
						Page.getPageType(deleteTableFile, currentPage) == 0x0D){
					deletePage = currentPage;
					break;
				}
				currentPage = currentPage++;
			}

			if(deletePage == 0) {
				System.out.println("The given key value does not exist");
				return;
			}

			short[] cellAddress = Page.getCellArray(deleteTableFile, deletePage);
			int k = 0;
			for(int i = 0; i < cellAddress.length; i++) {
				long loc = Page.getCellLoc(deleteTableFile, deletePage, i);
				String[] vals = Table.retrieveValues(deleteTableFile, loc);
				int x = Integer.parseInt(vals[0]);
				if(x != key) {
					Page.setCellOffset(deleteTableFile, deletePage, k, cellAddress[i]);
					k++;
				}
			}
			Page.setCellNumber(deleteTableFile, deletePage, (byte)k);

		}catch(Exception e) {
			System.out.println(e);
		}

	}

}

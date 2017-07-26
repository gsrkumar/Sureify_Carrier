package services;


import java.io.File;
import java.util.Map;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;



public class WriteResults {

	public static void write(String filename,Map<String,String> resultmap){
		try {
			// Load the file.
			File file = new File(filename);
			final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);

			// Set value.
			for(Map.Entry entry:resultmap.entrySet()){
				//System.out.print(entry.getKey() + " : " + entry.getValue());
				sheet.getCellAt((String)entry.getKey()).setValue((String)entry.getValue());
				//sheet.getCellAt("B2").setBackgroundColor(arg0);
			}
			// Save to file and open it.
			File outputFile = new File(filename);
			File o = sheet.getSpreadSheet().saveAs(outputFile);
			// OOUtils.open(o);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
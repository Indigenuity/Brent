package datatransfer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVImporter {
	
	public static void importCompanies() throws IOException {
		Reader in = new FileReader("companies.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
		for (CSVRecord record : records) {
			System.out.println("company : " + record.get("Company"));
		}
	}

}

package Machin

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class ReadCSV {
 
  public static void main(String[] args) {
 
	ReadCVS obj = new ReadCVS();
	obj.run();
 
  }
 
  public void run() {
 
	String csv1File = "mtp.csv";
	String csv2File = "paris.csv";

	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ";";
	String fin, debut;
 
	try {
 
		br = new BufferedReader(new FileReader(csv1File));
		while ((line = br.readLine()) != null) {
 
		        // use comma as separator
			String[] tournage = line.split(cvsSplitBy);
 
			System.out.println("Tournage [film= " + tournage[1] 
                                 + " , lieu=" + tournage[2] + " , ville = Montpellier, date début=" + tournage[3] +", date fin="+tournage[4]+"]");
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	try {
 
		br = new BufferedReader(new FileReader(csv2File));
		while ((line = br.readLine()) != null) {
 
		        // use comma as separator
			String[] tournage = line.split(cvsSplitBy);

			System.out.println("Tournage [film= " + tournage[0] 
                                 + " , lieu=" + tournage[5]+" "+tournage[6] + " , ville = Paris, date début=" + tournage[2].replace("-", "/") +", date fin="+tournage[3].replace("-", "/")+"]");
 
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	System.out.println("Done");
  }
 
}
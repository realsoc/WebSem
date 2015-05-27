package dataCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
 
public class TournageCSVCreator {

	private static final String CSV_DIR ="src/dataCreator/CSV/";
	private static final String CSV_TARGET = "tournages.csv";
	private static final String CSV_SPLIT_BY = ";";
	private static final String ROW_TITLE = "titre";
	private static final String ROW_PLACE = "lieu";
	private static final String ROW_DATE_BEGIN = "date_debut";
	private static final String ROW_DATE_END = "date_fin";


	private static boolean newFile = true;
	private static FileWriter fstream = null;
	private static BufferedWriter bw = null;
	private String city;
	private Map<String,Integer> titlesIndex;

  	public static void main(String[] args) {
  		int i, nmbCSV;
  		nmbCSV = args.length;
  		TournageCSVCreator creator = new TournageCSVCreator();
  		creator.fileInit();
  		for(i=0; i< nmbCSV; i++){
  			try{
  				creator.setCity(args[i].toLowerCase());
  				creator.init();
  				creator.run();
  			}catch (IOException e){
  				System.out.println("Error while parsing CSV for"+creator.getCity());
  				System.out.println(creator.getCity()+" ignored...");
  			}
  		}
  		creator.closeBufferWriter();
  		System.out.println("Done");
 	}
 	public void setCity(String nCity){
 		this.city = nCity;
 	}
 	public String getCity(){
 		return this.city;
 	}
 	public void fileInit(){
 		if(this.newFile){
 			File f;
 			boolean exist;
 			f = new File(CSV_DIR+CSV_TARGET);
 			exist = f.exists();
 			try{
 				this.fstream = new FileWriter(CSV_DIR+CSV_TARGET, true);
 				this.bw = new BufferedWriter(fstream);
 				if(!exist){
 					this.bw.write("ville;"+ROW_TITLE+CSV_SPLIT_BY+ROW_PLACE+CSV_SPLIT_BY+ROW_DATE_BEGIN+CSV_SPLIT_BY+ROW_DATE_END);
					this.bw.newLine();
 					this.newFile = false;
 				}
 			}catch(IOException e){
 				e.printStackTrace();
 			}


 		}
 	}
 	public void closeBufferWriter(){
 		try{
 			this.bw.close();
 		}catch(IOException e){
 			e.printStackTrace();
 		}
 	}
 	public void init() throws IOException {
 		int i, j=4;
 		Map<String, Integer> titlesIndex = new HashMap<String, Integer>();
 		BufferedReader toto = null;
 		try {
 			toto = new BufferedReader(new FileReader(CSV_DIR+getCity().toLowerCase()+".csv"));
			String line = toto.readLine();
			String[] rows = line.split(CSV_SPLIT_BY);
			for(i = 0; i <rows.length ; i++){
				switch(rows[i].toLowerCase()){
					case ROW_TITLE:
						titlesIndex.put(ROW_TITLE, i);
						j--;
						break;
					case ROW_PLACE:
						titlesIndex.put(ROW_PLACE, i);
						j--;
						break;
					case ROW_DATE_END:
						titlesIndex.put(ROW_DATE_END, i);
						j--;
						break;
					case ROW_DATE_BEGIN:
						titlesIndex.put(ROW_DATE_BEGIN, i);
						j--;
						break;
					default:
						break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(toto != null) {
				try {
					toto.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(j != 0){
			throw new IOException("toto");
		}
		setTitlesIndex(titlesIndex);
 	}
 	public void setTitlesIndex(Map<String,Integer> nTitlesIndex){
 		this.titlesIndex = nTitlesIndex;
 	}
  	public void run() {		
 		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(CSV_DIR+"/"+getCity().toLowerCase()+".csv"));
			br.readLine(); // lis la ligne de titre

			while ((line = br.readLine()) != null) {
				String[] tournage = line.split(CSV_SPLIT_BY);
				this.bw.write(this.getCity()+";"+tournage[titlesIndex.get(ROW_TITLE)]+";"+tournage[titlesIndex.get(ROW_PLACE)]
					+";"+tournage[titlesIndex.get(ROW_DATE_BEGIN)]+";"+tournage[titlesIndex.get(ROW_DATE_END)]);
				this.bw.newLine();
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

  	} 
	
}
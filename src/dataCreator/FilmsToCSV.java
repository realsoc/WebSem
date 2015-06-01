package dataCreator;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
//import java.io.BufferedReader;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

public class FilmsToCSV{
	private static boolean newFile = true;
	private static FileWriter fstream = null;
	private static BufferedWriter bw = null;
	private static final String CSV_DIR ="src/dataCreator/CSV/";
	private static final String CSV_TARGET = "films.csv";
	private static final String CSV_SPLIT_BY = ";";

	private static final String ROW_TITLE = "title";
	private static final String ROW_YEAR = "year";
	private static final String ROW_GENRES = "genres";
	private static final String ROW_REALISATORS = "realisators";
	private static final String ROW_ACTORS = "actors";
	private static final String ROW_SYNOPSIS = "synopsis";
	private static final String ROW_LANGUAGE = "language";
	private static final String ROW_COUNTRY = "country";
	private static final String ROW_RATE = "rate";
	private static final String ROW_FOUND = "found";
	private static final String ROW_DURATION = "duration";

	public static void writeFilmsToCSV(ArrayList<Film> films) {
		initWriter();
		for(Film currentFilm : films){
			try{
				FilmsToCSV.bw.write(currentFilm.getTitle()+CSV_SPLIT_BY+currentFilm.getYear()+CSV_SPLIT_BY+arrayToString(currentFilm.getGenres())+CSV_SPLIT_BY+arrayToString(currentFilm.getRealisators())+CSV_SPLIT_BY+
					arrayToString(currentFilm.getActors())+CSV_SPLIT_BY+currentFilm.getSynopsis()+CSV_SPLIT_BY+currentFilm.getLanguage()+CSV_SPLIT_BY+currentFilm.getCountry()+CSV_SPLIT_BY+
					currentFilm.getRate()+CSV_SPLIT_BY+currentFilm.getDuration()+CSV_SPLIT_BY+currentFilm.getFound());
				FilmsToCSV.bw.newLine();
			}
			catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}		
  	} 
  	public static void initWriter(){
  		if(FilmsToCSV.newFile){
 			File f;
 			boolean exist;
 			f = new File(CSV_DIR+CSV_TARGET);
 			exist = f.exists();
 			try{
 				FilmsToCSV.fstream = new FileWriter(CSV_DIR+CSV_TARGET, true);
 				FilmsToCSV.bw = new BufferedWriter(fstream);
 				if(!exist){
 					FilmsToCSV.bw.write(ROW_TITLE+CSV_SPLIT_BY+ROW_YEAR+CSV_SPLIT_BY+ROW_GENRES+CSV_SPLIT_BY+ROW_REALISATORS
 						+CSV_SPLIT_BY+ROW_ACTORS+CSV_SPLIT_BY+ROW_SYNOPSIS+CSV_SPLIT_BY+ROW_LANGUAGE
 						+CSV_SPLIT_BY+ROW_COUNTRY+CSV_SPLIT_BY+ROW_RATE+CSV_SPLIT_BY+ROW_DURATION+CSV_SPLIT_BY+ROW_FOUND);
					FilmsToCSV.bw.newLine();
 					FilmsToCSV.newFile = false;
 				}
 			}catch(IOException e){
 				e.printStackTrace();
 			}
 		}
 	}
 	public static void main(String[] args){
 		System.out.println(ROW_TITLE+CSV_SPLIT_BY+ROW_YEAR+CSV_SPLIT_BY+ROW_GENRES+CSV_SPLIT_BY+ROW_REALISATORS
 						+CSV_SPLIT_BY+ROW_ACTORS+CSV_SPLIT_BY+ROW_SYNOPSIS+CSV_SPLIT_BY+ROW_LANGUAGE
 						+CSV_SPLIT_BY+ROW_COUNTRY+CSV_SPLIT_BY+ROW_RATE+CSV_SPLIT_BY+ROW_DURATION+CSV_SPLIT_BY+ROW_FOUND);
 	}
 	public static String arrayToString(ArrayList<String> array){
 		String ret ="null";
 		if(array != null){
 			ret = "";
 			for(String el : array){
 				ret += el+",";
 			}
 		}
 		return ret;
 	}

}
package dataCreator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;





class Main{
	private static final String CSV_DIR ="src/dataCreator/CSV/";
	private static final String CSV_SOURCE = "tournages.csv";
	private static final String CSV_FILMS = "films.csv";
	private static final String CSV_SPLIT_BY = ";";
	private static ArrayList<Film> films;
	private static ArrayList<Tournage> tournages;
	private static int lastLine = 1;
/*
	public static void main(String[] args) {
			films = new ArrayList<Film>();
			tournages = new ArrayList<Tournage>();
		try{
			BufferedReader toto = new BufferedReader(new FileReader(CSV_DIR+CSV_SOURCE));
	  		int index;
	  		Film nFilm;
	  		Tournage nTournage;
	  		String line = toto.readLine();
	  		String[] tournageParsed;
	  		while ((line = toto.readLine()) != null) {
				tournageParsed = line.split(CSV_SPLIT_BY);
				if((index = indexOfFilm(tournageParsed[1])) == -1){
					nFilm = new Film(tournageParsed[1]);
					index = films.size();
					films.add(nFilm);
				}
				try{
					nFilm = films.get(index);
					nFilm.addTournage();
					nTournage = new Tournage(nFilm,tournageParsed[0], tournageParsed[3], tournageParsed[4], tournageParsed[2]);
					nTournage.setNumber(nFilm.getNumberOfTournage());
					tournages.add(nTournage);
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Error in CSV : "+tournageParsed[1]+" line "+line);
				}
				lastLine++;
			}
			FilmsToCSV.initWriter();
			FilmsToCSV.writeFilmsToCSV(films);
			/*for(Film film : films){
				System.out.println(film);
			}

			System.out.println(films.size());*/
		/*}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
 	}*/
 	public static void main(String[] args){
 		films = new ArrayList<Film>();
 		tournages = new ArrayList<Tournage>();
 		try{
			BufferedReader toto = new BufferedReader(new FileReader(CSV_DIR+CSV_FILMS));
	  		int index;
	  		Tournage nTournage;
	  		String line = toto.readLine();
	  		String[] filmParsed;
	  		while ((line = toto.readLine()) != null) {
				filmParsed = line.split(CSV_SPLIT_BY);	
				System.out.println(filmParsed[0]);	
				films.add(new Film(filmParsed[0],filmParsed[1], filmParsed[2], filmParsed[3], filmParsed[4],filmParsed[5],filmParsed[6],filmParsed[7],filmParsed[8], filmParsed[9], filmParsed[10]));
			}
			/*for(Film film : films){
				System.out.println(film);
			}

			System.out.println(films.size());*/
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			BufferedReader titi = new BufferedReader(new FileReader(CSV_DIR+CSV_SOURCE));
	  		int index;
	  		int blou = 0;
	  		Tournage nTournage;
	  		Film nFilm;
	  		String lone = titi.readLine();
	  		String[] tournageParsed;
	  		while ((lone = titi.readLine()) != null) {
	  			tournageParsed = lone.split(CSV_SPLIT_BY);
	  			try{
	  				blou++;
		  			nFilm = films.get(indexOfFilm(tournageParsed[1]));
		  			nFilm.addTournage();
					nTournage = new Tournage(nFilm,tournageParsed[0], tournageParsed[2],tournageParsed[3],tournageParsed[4]);
					nTournage.setNumber(nFilm.getNumberOfTournage());
					tournages.add(nTournage);
				}catch(ArrayIndexOutOfBoundsException e){
					//e.printStackTrace();
					//System.out.println(blou);
				}
			}
			
			OntologyPopulater onPop = new OntologyPopulater();
			onPop.addFilms(films);
			onPop.addTournages(tournages);
			onPop.saveToFile();
			/*for(Film film : films){
				System.out.println(film);
			}

			System.out.println(films.size());*/
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
 	}
 	public static int indexOfFilm(String title){
 		int ret = -1;
 		Film currentFilm;
 		int i;
 		for(i=0;i<films.size();i++){
 			currentFilm = films.get(i);
 			if(currentFilm.hasTitle(title)){
 				ret = i;
 				break;
 			}
 		}
 		return ret;
 	}/**/
}

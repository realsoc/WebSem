package dataCreator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;


import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import java.io.File;



class Main{
	private static final String CSV_DIR ="src/dataCreator/CSV/";
	private static final String CSV_SOURCE = "tournages.csv";
	private static final String CSV_SPLIT_BY = ";";
	private static ArrayList<Film> films;
	private static ArrayList<Tournage> tournages;
	private static int lastLine = 1;

	public static void main(String[] args) {
		try{
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			IRI iri = IRI.create("http://www.dcs.bbk.ac.uk/~michael/sw/slides/pizza.owl");
			OWLOntology pizzaOntology = manager.loadOntologyFromOntologyDocument(iri);
			System.out.println("Loaded ontology: " + pizzaOntology);
			File file = new File("/tmp/local.owl");
			manager.saveOntology(pizzaOntology, IRI.create(file.toURI()));
		}catch(OWLOntologyCreationException e){
			e.printStackTrace();
		}catch(OWLOntologyStorageException e){
			e.printStackTrace();
		}
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
					nTournage = new Tournage(films.get(index),tournageParsed[0], tournageParsed[3], tournageParsed[4], tournageParsed[2]);
					tournages.add(nTournage);
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Error in CSV : "+tournageParsed[1]+" line "+line);
				}
				lastLine++;
			}
			for(Film film : films){
				System.out.println(film);
			}
			System.out.println(films.size());
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
 	}
}
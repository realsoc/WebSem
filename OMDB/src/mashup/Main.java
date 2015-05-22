package mashup;

import java.io.File;

import org.semanticweb.owlapi.*;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class Main {
	
	public static void main(String args[]) throws OWLOntologyCreationException
   {
     //OMDBProxy omdbProxy = new OMDBProxy();
     //System.out.println(omdbProxy.getMovieInfos("the artist").get("imdbRating"));
     // Get hold of an ontology manager
	OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	// Load an ontology from the Web
	IRI iri = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl");
	OWLOntology pizzaOntology = manager.loadOntologyFromOntologyDocument(iri);
	System.out.println("Loaded ontology: " + pizzaOntology);
   }



}

package dataCreator;


import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.text.WordUtils;
import java.util.ArrayList;
import java.security.*;

import java.io.File;

public class OntologyPopulater {
	private static final String DURE_MINUTE = "OWLDataProperty_b9847ab2_79ca_4de1_b8ca_b4f5fbdefb0c";
	private static final String A_ACTEUR = "OWLObjectProperty_1bc18fca_20d8_42ce_bfe3_052694f49f56";
	private static final String A_LANGUAGE = "OWLObjectProperty_7667e93a_71e3_4e45_8cf5_3f73b22dbd93";
	private static final String A_ORIGINE = "OWLObjectProperty_e901b167_e487_4ddf_b17f_b680ba4a88fb";
	private static final String A_FILM = "OWLObjectProperty_154b4a84_d27c_44a5_9718_083e749dea60";
	private static final String A_TYPE = "OWLObjectProperty_50deed6e_6715_4bc1_8c44_ccb165251b4e";
	private static final String A_JOUE_DANS = "OWLObjectProperty_4d4acc34_54e2_49ef_bda1_414e2f188239";
	private static final String A_POUR_GENRE = "OWLObjectProperty_0b4da05e_5cba_4ed9_9245_88e352bade3c";
	private static final String A_POUR_REAL = "OWLObjectProperty_c8e430da_3bee_4e3e_a4d1_a97f539060ea";
	private static final String A_REAL = "OWLObjectProperty_42aa91d7_9cf6_42f1_ac02_688fcd4fb1e2";
	private static final String A_NOTE = "OWLDataProperty_8819a1bb_e58e_4248_8b57_06d580d9eed1";
	private static final String TOURNE_RUE = "OWLObjectProperty_200ba544_4092_461b_99fd_14cf1eb2f970";
	private static final String TOURNE_VILLE = "OWLObjectProperty_804133dc_4dcb_4f9c_a499_2fc8a9738b63";
	private static final String FOUND_OMDB = "OWLDataProperty_285859ec_d95c_4587_b393_d02bf1047011";
	private static final String A_COMMENCE = "OWLDataProperty_fb787a53_e56f_42b1_b8e4_38493c53e977";
	private static final String A_FINI = "OWLDataProperty_ef4a96cc_1ba9_4ab8_9e50_0feba4e90aad";
	private static final String EST_SORTIE = "OWLDataProperty_7c889767_6e96_4976_a2c7_b1895ae0c13e";
	private static final String A_NUMERO = "OWLDataProperty_abedcd89_074d_4f6f_af64_1ef938475df8";
	private static final String A_NOMBRE_TOURNAGE = "OWLDataProperty_0b45e466_bd36_4ea8_97cb_cd69ed9f88f3";

	


	private OWLOntologyManager manager;
	private IRI localOntologyIri;
	private static final String remoteOntologyUrl = "/tmp/tp2.owl";
	private static final String suffix = "Local";
	private OWLOntology mRemoteOntology;
	private IRI remoteOntologyIri;
	private OWLOntology mLocalOntology;
	private File fileOut;
	private File fileIn;
	private OWLDataFactory factory;
	private PrefixManager remotePrefix;
	private PrefixManager localPrefix;

	public OntologyPopulater(){
			
		try{

			fileIn = new File(remoteOntologyUrl); //modifier et mettre le bon chemin
			//parsage du fichier pour obtenir un objet onto de type OWLOntology 
			
	

			manager = OWLManager.createOWLOntologyManager();
			factory = manager.getOWLDataFactory();
			mRemoteOntology = manager.loadOntologyFromOntologyDocument(fileIn);		
			remoteOntologyIri = mRemoteOntology.getOntologyID().getOntologyIRI();	
			localOntologyIri = IRI.create(remoteOntologyIri.toString()+suffix);
			remotePrefix = new DefaultPrefixManager(remoteOntologyIri.toString()+"#");
			localPrefix = new DefaultPrefixManager(localOntologyIri.toString()+"#");
			// création de l'ontologie locale
			mLocalOntology = manager.createOntology(new OWLOntologyID(localOntologyIri));
			// création du fichier de sortie de l'ontologie locale
			fileOut = new File("/tmp/tp2Local.owl");
		
			//récupération de l'ensemble des données contenues dans les ontologies chargées
			factory = manager.getOWLDataFactory();
			//import de l'ontologie onto dans l'ontologie mLocalOntology de façon à faire évoluer les deux indépedamment
			OWLImportsDeclaration importDeclaraton = factory.getOWLImportsDeclaration(remoteOntologyIri);
			manager.applyChange(new AddImport(mLocalOntology, importDeclaraton));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void saveToFile(){
		try{
			manager.saveOntology(mLocalOntology, IRI.create(fileOut.toURI()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//onto = remote 
	//mLocalOntology = local*/
	public void addThing(String toAdd){


		OWLClass Thing = factory.getOWLClass(IRI.create("http://www.w3.org/2002/07/owl#Thing"));
		/*
		 * exemple de création d'une instance dans l'ontologie ontoPeup
		 */
		OWLNamedIndividual newThing = factory.getOWLNamedIndividual(":"+hashMe(toAdd),localPrefix);
		
		/*
		 * exemple d'assertion indiquant que l'instance nvfilm est de type Film
		 */
		OWLClassAssertionAxiom classAssertion =
		factory.getOWLClassAssertionAxiom(Thing, newThing);
		manager.addAxiom(mLocalOntology, classAssertion); //on enregistre l'assertion dans l'ontologie ontoPeup
		
		/*
		 * exemple d'ajout de label à l'instance nvfilm
		 */
		OWLAnnotation lab = factory.getOWLAnnotation(factory.getRDFSLabel(),factory.getOWLLiteral(toAdd,"fr"));
		OWLAnnotationAssertionAxiom labassertion= factory.getOWLAnnotationAssertionAxiom(newThing.getIRI(), lab);
		manager.addAxiom(mLocalOntology, labassertion);

		/*
		 * exemple de création d'une instance dans l'ontologie local
		 */
		
		
		/*
		 * exemple d'assertion indiquant que l'instance nvmFilm est de type mFilm
		 */
		
		
		/*
		 * exemple d'ajout de label à l'instance nvmFilm
		 */
		
		
		
		/*
		 * exemple d'ajout d'une dataproperty à l'instance nvmFilm
		 *
		OWLDatatype integerDatatype = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());//recuration du type INTEGER
		OWLLiteral literal3 = factory.getOWLLiteral("33", integerDatatype); //creation du literal 33
			
		OWLDataProperty proNbEntree=factory.getOWLDataProperty(":aPourDuree",remotePrefix);// récupération de la dataproperty définie dans onto
		OWLDataPropertyAssertionAxiom dataassersion = factory.getOWLDataPropertyAssertionAxiom(proNbEntree, nvmFilm, literal3);// assertion du lien entre l instance, la propriété et le litéral
		manager.addAxiom(mLocalOntology, dataassersion);	//stockage de l'assertion dans mLocalOntology
		
		
		/*
		 * exemple d'ajout d'une objectproperty à l'instance nvmFilm
		 */
			/*
		OWLNamedIndividual nvreal = factory.getOWLNamedIndividual(":NVREAL",localPrefix); //création d'une instance nvreal

		OWLAnnotation labreal = factory.getOWLAnnotation(factory.getRDFSLabel(),factory.getOWLLiteral("mon nouveau real","fr"));
		OWLAnnotationAssertionAxiom labassertionreal= factory.getOWLAnnotationAssertionAxiom(nvreal.getIRI(), labreal);
		manager.addAxiom(mLocalOntology, labassertionreal);
		
		OWLObjectProperty propReal =factory.getOWLObjectProperty(":aReal",remotePrefix);//recupération de l objectproperty dans onto
		OWLObjectPropertyAssertionAxiom objectassersion = factory.getOWLObjectPropertyAssertionAxiom(propReal, nvmFilm, nvreal); //assertion du lien
		manager.addAxiom(mLocalOntology, objectassersion); //ajout de l'assertion dans mLocalOntology
		*/
	}
	public void addFilms(ArrayList<Film> films){
		for(Film film : films){
			addFilm(film);
		}
	}
	public void addFilm(Film film){
		addThing(film.getTitle());
		addThing(film.getCountry());
		linkObjectWithObject(film.getTitle(), A_ORIGINE, film.getCountry());
		addThing(film.getLanguage());
		linkObjectWithObject(film.getTitle(), A_LANGUAGE, film.getLanguage());
		for(String el : film.getGenres()){
			addThing(el);
			linkObjectWithObject(film.getTitle(), A_POUR_GENRE, el);
		}
		for(String el : film.getRealisators()){
			addThing(el);
			linkObjectWithObject(film.getTitle(),A_POUR_REAL, el);
		}
		for(String el : film.getActors()){
			addThing(el);
			linkObjectWithObject(film.getTitle(), A_ACTEUR, el);
		}	
		if(film.getDuration() !=0)
		linkObjectWithInt(film.getTitle(),DURE_MINUTE,film.getDuration());
		if(film.getYear() !=0)
		linkObjectWithInt(film.getTitle(),EST_SORTIE,film.getYear());
		if(film.getRate() !=0F)
		linkObjectWithFloat(film.getTitle(),A_NOTE,film.getRate());
		linkObjectWithInt(film.getTitle(),A_NOMBRE_TOURNAGE,film.getNumberOfTournage());
		//linkObjectWithString(film.getTitle(),,film.getSynopsis());
		//linkObjectWithBoolean(film.getTitle(),"",film.getFound());
	}
	public void addTournages(ArrayList<Tournage> tournages){
		for(Tournage tournage : tournages){
			if(tournage.getFilm()!= null && tournage.getNTournage() != 0)
				addTournage(tournage);
		}
	}
	public void addTournage(Tournage tournage){
		addThing(tournage.toString());
		addThing(tournage.getPlace());
		linkObjectWithObject(tournage.toString(), TOURNE_RUE, tournage.getPlace());
		linkObjectWithObject(tournage.toString(), A_FILM, tournage.getFilm().getTitle());
		addThing(tournage.getCity());
		linkObjectWithObject(tournage.toString(), TOURNE_VILLE, tournage.getCity());
		linkObjectWithString(tournage.toString(),A_COMMENCE, tournage.getBegin());
		linkObjectWithString(tournage.toString(),A_FINI, tournage.getEnd());
		linkObjectWithInt(tournage.toString(), A_NUMERO, tournage.getNTournage());
		//linkObjectWithString(film.getTitle(),,film.getSynopsis());
		//linkObjectWithBoolean(film.getTitle(),"",film.getFound());
	}
	public void linkObjectWithInt(String object, String link, int data){
		OWLNamedIndividual ob = factory.getOWLNamedIndividual(":"+hashMe(object),localPrefix);
		OWLDatatype integerDatatype = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());//recuration du type INTEGER
		OWLLiteral lit = factory.getOWLLiteral(Integer.toString(data), integerDatatype);
		OWLDataProperty dataLink=factory.getOWLDataProperty(":"+link,remotePrefix);// récupération de la dataproperty définie dans onto
		OWLDataPropertyAssertionAxiom dataassersion = factory.getOWLDataPropertyAssertionAxiom(dataLink, ob, lit);// assertion du lien entre l instance, la propriété et le litéral
		manager.addAxiom(mLocalOntology, dataassersion);

	}
	public void linkObjectWithFloat(String object, String link, float data){
		OWLNamedIndividual ob = factory.getOWLNamedIndividual(":"+hashMe(object),localPrefix);
		OWLDatatype floatDataType = factory.getOWLDatatype(OWL2Datatype.XSD_FLOAT.getIRI());//recuration du type INTEGER
		OWLLiteral lit = factory.getOWLLiteral(Float.toString(data), floatDataType);
		OWLDataProperty dataLink=factory.getOWLDataProperty(":"+link,remotePrefix);// récupération de la dataproperty définie dans onto
		OWLDataPropertyAssertionAxiom dataassersion = factory.getOWLDataPropertyAssertionAxiom(dataLink, ob, lit);// assertion du lien entre l instance, la propriété et le litéral
		manager.addAxiom(mLocalOntology, dataassersion);

	}
	public void linkObjectWithString(String object, String link, String data ){
		OWLNamedIndividual ob = factory.getOWLNamedIndividual(":"+hashMe(object),localPrefix);

	}
	public void linkObjectWithObject(String object1, String link, String object2){
		OWLNamedIndividual ob1 = factory.getOWLNamedIndividual(":"+hashMe(object1),localPrefix);
		OWLNamedIndividual ob2 = factory.getOWLNamedIndividual(":"+hashMe(object2),localPrefix);
		OWLObjectProperty prop =factory.getOWLObjectProperty(":"+link,remotePrefix);//recupération de l objectproperty dans onto
		OWLObjectPropertyAssertionAxiom objectassersion = factory.getOWLObjectPropertyAssertionAxiom(prop, ob1, ob2); //assertion du lien
		manager.addAxiom(mLocalOntology, objectassersion); 

	}
	public void linkObjectWithBoolean(String object, String link, boolean data){
		OWLNamedIndividual ob = factory.getOWLNamedIndividual(":"+hashMe(object),localPrefix);
	}

	public String hashMe(String toHash){
		String toto = null;
		try{
			byte[] bytesOfMessage = toHash.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			toto =  byteArrayToHex(thedigest);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return toto;
	}
	public static String byteArrayToHex(byte[] a) {
   		StringBuilder sb = new StringBuilder(a.length * 2);
   		for(byte b: a)
      		sb.append(String.format("%02x", b & 0xff));
   		return sb.toString();
	}
}

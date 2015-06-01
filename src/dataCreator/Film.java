package dataCreator;

import java.util.HashMap;


import org.apache.commons.lang3.text.WordUtils;
import java.util.ArrayList;

public class Film{
	private static int nbFound =0;
	private int nTournage = 0;
	private String title;
	private int year;
	private ArrayList<String> genres;
	private ArrayList<String> realisators;
	private ArrayList<String> actors;
	private String synopsis;
	private String language;
	private String country;
	private float rate;
	private boolean found = false;
	private String id;
	private int duration;

	public Film(String title){
		this.title = title;
		this.retrieveInfo();
	}
	public Film(String title, String year, String genres,String realisators, String actors, String synopsis, String language, String country, String rate, String duration, String found){
		this.title = title;
		this.genres = splitString(genres);
		this.year = Integer.valueOf(year);
		this.realisators = splitString(realisators);
		this.actors = splitString(actors);
		this.synopsis = synopsis;
		this.language = language;
		this.country = country;
		this.rate = Float.valueOf(rate);
		this.duration = Integer.valueOf(duration);
		this.found = Boolean.valueOf(found);

	}

	public String getTitle(){
		return this.title;
	}
	public int getYear(){
		return this.year;
	}
	public ArrayList<String> getGenres(){
		return this.genres;
	}
	public ArrayList<String>  getRealisators(){
		return this.realisators;
	}
	public ArrayList<String>  getActors(){
		return this.actors;
	}
	public String getSynopsis(){
		return this.synopsis;
	}
	public String getLanguage(){
		return this.language;
	}
	public String getCountry(){
		return this.country;
	}
	public float getRate(){
		return this.rate;
	}
	public int getDuration(){
		return this.duration;
	}
	public boolean getFound(){
		return this.found;
	}
	public void retrieveInfo(){
		System.out.println("Found : "+nbFound);
		OMDBProxy proxy = new OMDBProxy();
		HashMap<String, String> ret = proxy.getMovieInfos(this.title);
		if (!Boolean.valueOf(ret.get("Response"))){
			//System.out.println("Title incorrect : "+getTitle());
		}else{
			found = true; 
			//System.out.println("Film trouvé : "+getTitle());
			nbFound++;
			genres = splitString(ret.get("Genres"));
			realisators = splitString(ret.get("Writer"));
			actors = splitString(ret.get("Actors"));
			synopsis = ret.get("Plot");
			language = ret.get("Language");
			country = ret.get("Country");
			try{
				year = Integer.parseInt(ret.get("Year"));
			}catch(NumberFormatException e){
				//e.printStackTrace();
			}
			try{
				String[] runtime = ret.get("Runtime").split(" ");
				duration = Integer.parseInt(runtime[0]);
				System.out.println(duration);
			}catch(NumberFormatException e){
				//e.printStackTrace();
			}
			try{
				rate = Float.parseFloat(ret.get("imdbRating"));
			}catch(NumberFormatException e){
				//e.printStackTrace();
			}			
		}
	}
	public ArrayList<String> splitString(String stringList){
		ArrayList<String> ret = new ArrayList<String>();
		if(stringList != null){
			String[] table = stringList.split(",");
			for(String el : table){
				ret.add(WordUtils.capitalize(el.trim().toLowerCase()));
			}
		}
		return ret;
	}
	public boolean hasTitle(String title){
		return this.title.equals(title);
	}
	public void run(){

	}
	public String toString(){
		return "Title :"+title+" year :"+year+" genres :"+genres+" realisators :"+realisators+" actors :"+actors+" synopsis :"+synopsis+" language :"+language+" country :"+country+" rate  :"+rate;
	}
	public static void main(String[] args){
		Film film = new Film(args[0]);
	}
	public int getNumberOfTournage(){
		return nTournage;
	}
	public void addTournage(){
		nTournage++;
	}
}
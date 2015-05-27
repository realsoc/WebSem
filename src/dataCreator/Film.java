package dataCreator;

import java.util.HashMap;

public class Film{
	private static int nbFound =0;
	private String title;
	private int year;
	private String[] genres;
	private String[] realisators;
	private String[] actors;
	private String synopsis;
	private String language;
	private String country;
	private float rate;
	private boolean found = false;

	public Film(String title){
		this.title = title;
		this.retrieveInfo();
	}
	public String getTitle(){
		return this.title;
	}
	public void retrieveInfo(){
		System.out.println("Found : "+nbFound);
		OMDBProxy proxy = new OMDBProxy();
		HashMap<String, String> ret = proxy.getMovieInfos(this.title);
		if (!Boolean.valueOf(ret.get("Response"))){
			System.out.println("Title incorrect : "+getTitle());
		}else{
			found = true; 
			System.out.println("Film trouvé : "+getTitle());
			nbFound++;
			genres = ret.get("Genre").split(",");
			realisators = ret.get("Writer").split(",");
			actors = ret.get("Actors").split(",");
			synopsis = ret.get("Plot");
			language = ret.get("Language");
			country = ret.get("Country");
			try{
				year = Integer.parseInt(ret.get("Year"));
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			try{
				rate = Float.parseFloat(ret.get("imdbRating"));
			}catch(NumberFormatException e){
				e.printStackTrace();
			}			
		}
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
}
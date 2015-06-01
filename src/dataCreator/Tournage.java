package dataCreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.security.*;
import java.io.UnsupportedEncodingException;

public class Tournage{
	private static final String DELIMITER_DATE = "/";
	private int nTournage = 0;
	private Film film;
	private String city;
	private String begin;
	private String end;
	private String place;
	private String id;

	public Film getFilm(){
		return film;
	}
	public int getNTournage(){
		return nTournage;
	}
	public String getBegin(){
		return begin;
	}
	public String getEnd(){
		return end;
	}	
	public String getCity(){
		return city;
	}
	public String getPlace(){
		return place;
	}

	public Tournage(Film mFilm, String city, String date_debut, String date_fin, String place){
		this.city = city;
		begin = date_debut;
		end = date_fin;
		this.place = place;
		this.film = film;

	}
	public String toString(){
		return "Tournage du film "+film.getTitle()+" numéro "+nTournage;
	}
	public void setNumber(int number){
		this.nTournage = number;
	}
}
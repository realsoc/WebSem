package dataCreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Tournage{
	private static final String DELIMITER_DATE = "/";
	private Film film;
	private String city;
	private Date begin;
	private Date end;
	private String place;

	public Tournage(Film mFilm, String city, String date_debut, String date_fin, String place){
		begin = new Date();
		end = new Date();
		this.city = city;
		try{
			begin = new SimpleDateFormat("dd/MM/yyyy").parse(date_debut);
			end = new SimpleDateFormat("dd/MM/yyyy").parse(date_fin);
			this.place = place;
			this.film = film;
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
	public String toString(){
		return "Tournage à "+this.place+" "+this.city+" pour le film "+film.getTitle();
	}
}
package Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer movie_id;
	private String movie_name;
	private String movie_detail;
	private Date movie_date;
	private int movie_price;
	private int movie_status;
	private int type_movie;
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_detail() {
		return movie_detail;
	}
	public void setMovie_detail(String movie_detail) {
		this.movie_detail = movie_detail;
	}
	public Date getMovie_date() {
		return movie_date;
	}
	public void setMovie_date(Date movie_date) {
		this.movie_date = movie_date;
	}
	public int getMovie_price() {
		return movie_price;
	}
	public void setMovie_price(int movie_price) {
		this.movie_price = movie_price;
	}
	public int getMovie_status() {
		return movie_status;
	}
	public void setMovie_status(int movie_status) {
		this.movie_status = movie_status;
	}
	public int getType_movie() {
		return type_movie;
	}
	public void setType_movie(int type_movie) {
		this.type_movie = type_movie;
	}
	
	
}

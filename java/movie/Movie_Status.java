package movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie_Status {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int movie_status;
	private String movie_name;
	public int getMovie_status() {
		return movie_status;
	}
	public void setMovie_status(int movie_status) {
		this.movie_status = movie_status;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
}

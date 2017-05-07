package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

	@Query(value = "SELECT * FROM movie ORDER BY movie_id DESC LIMIT 8", nativeQuery = true)
	List<Movie> getNewMovie();

	@Query(value = "SELECT * FROM movie m, type t WHERE m.type_id = t.type_id ORDER BY movie_id DESC LIMIT ?1,10", nativeQuery = true)
	List<Movie> getMovielmit(Integer page);

	@Query(value = "SELECT * FROM movie WHERE movie_name LIKE ?1% ORDER BY movie_id DESC", nativeQuery = true)
	List<Movie> searchMovie(String movie_name);

	@Query(value = "SELECT * FROM movie LIMIT ?1,20", nativeQuery = true)
	List<Movie> getMovieAlllmit(Integer page);
	
	@Query(value = "SELECT * FROM movie WHERE type_id = ?1 LIMIT ?2,20", nativeQuery = true)
	List<Movie> getMovieTypelmit(Integer type_id,Integer page);
	
	@Query(value = "SELECT * FROM movie m, type t WHERE m.movie_id = ? ", nativeQuery = true)
	List<Movie> getMovieEdit(Integer movie_id);
	//List<Movie> findByMovie_id(int movie_id);
	
	@Query(value = "SELECT * FROM movie m, type t WHERE m.type_id = ? ", nativeQuery = true)
	List<Movie> getMovieSearch(Integer movie_id);
	
	@Query(value = "SELECT * FROM order_movie,movie WHERE order_movie.user_id = ?1 AND order_movie.movie_id = movie.movie_id LIMIT ?2,20", nativeQuery = true)
	List<Movie> getMovieMy(Integer user_id,Integer page);
	
	 

}

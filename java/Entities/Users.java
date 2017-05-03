package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	private String username;
	private String password;
	private String fullname;
	private String address;
	private String email;
	private String tel;
	private String status;
	private int money;
	private int user_have_movie;
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getUser_have_movie() {
		return user_have_movie;
	}

	public void setUser_have_movie(int user_have_movie) {
		this.user_have_movie = user_have_movie;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*@OneToMany
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	public Users(String username, String password,String email,String fullname,String address,String tel,String status,int money,Movie movie) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.money = money;
		this.status = status;
		this.movie = movie;
	}
	public Users() { }*/
	
}

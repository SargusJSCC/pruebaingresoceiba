package co.com.ceiba.mobile.pruebadeingreso.modal;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user", indices = @Index(value = {"id"},unique = true))
public class Users {

	@PrimaryKey(autoGenerate = true)
	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("phone")
	private String phone;

	@SerializedName("email")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Users(int id, String name, String email, String phone){
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}








}

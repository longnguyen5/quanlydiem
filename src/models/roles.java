package models;

public class roles {

	private int id;
	private String role;

	public roles() {

	}

	public roles(int id, String role) {

		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

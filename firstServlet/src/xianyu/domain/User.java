//创建一个User类封装用户信息
package xianyu.domain;
public class User {
	private int id; // 用户编号
	private String username;
	private String password;
	private String gender; // 性别
	private String email;
	private String role;
	private String map;//所属地域
	
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return this.role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
}

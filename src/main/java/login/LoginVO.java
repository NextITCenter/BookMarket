package login;

public class LoginVO {
	private String email;
	private String password;
	public LoginVO() {
	}
	public LoginVO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVO [email=" + email + ", password=" + password + "]";
	}
}

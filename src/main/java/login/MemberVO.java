package login;

import java.time.LocalDate;

public class MemberVO {
	private String id;
	private String email;
	private String name;
	private LocalDate registerDate;
	private LocalDate modifiedDate;
	public MemberVO() {
	}
	public MemberVO(String id, String email, String name, LocalDate registerDate, LocalDate modifiedDate) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.registerDate = registerDate;
		this.modifiedDate = modifiedDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", email=" + email + ", name=" + name + ", registerDate=" + registerDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}
	
}

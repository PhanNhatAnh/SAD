package n3.web.model;

public class fbAcc {

	private String id;
	private String email;
	private String first_name;
	private String gender;
	private String last_name;
	private String link;
	private String locale;
	private String name;
	private String timezone;
	private String updated_time;
	private String verified;
	public fbAcc() {
		// TODO Auto-generated constructor stub
	}
	public fbAcc(String id,String email,String first_name,
			String gender,String last_name,String link) {
		this.id=id;
		this.email=email;
		this.first_name=first_name;
		this.gender=gender;
		this.last_name=last_name;
		this.link=link;
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String toString() {
		return "id;" +id
				+"email;" + email
				+"first_name;" + first_name
				+"gender;"+gender
				+"last_name;"+last_name
				+"link;"+link;
	}
}

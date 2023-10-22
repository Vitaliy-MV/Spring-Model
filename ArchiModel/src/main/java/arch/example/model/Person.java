package arch.example.model;

class Person {
	
    long id;
    private String login;
    private String password;
    private String position;
    private String role;
    
    public Person () {}
    public Person (long id, String login, String password, 
    					String position, String role) {
    	 	this.id = id;
    	    this.login=login;
    	    this.password=password;
    	    this.position=position;
    	    this.role=role;
    }
    public long getId() {
    	return id;
    }
    public void setId (long id) {
    	this.id=id;
    }
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}

package arch.example.model;
import java.util.ArrayList;
import java.util.List;

class DAO {
	private List <Person> people;
	{
		people = new ArrayList<Person>();
		people.add(new Person (1, "admin","password","1","ADMIN"));
		people.add(new Person (2, "moderator","password","2","MODERATOR"));
		people.add(new Person (3, "user","password","3","USER"));
	}
	
	public List<Person> list(){
		return people;
	}
	 
	public Person findByUsername(String userName) {
		return people.stream().filter(person -> 
		person.getLogin().equals(userName)).findFirst().orElse(null);
	}
	
	public void save(Person person) {
		people.add(person);
	}
	
}

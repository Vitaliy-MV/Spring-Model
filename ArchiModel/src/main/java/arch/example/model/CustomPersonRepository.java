package arch.example.model;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomPersonRepository {
	Person findByUsername(String username);
	void save (Person user);
}
// This interface can be deleted

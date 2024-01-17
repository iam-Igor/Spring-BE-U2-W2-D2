package ygorgarofalo.SpringBeU2W2D2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;

@Repository
public interface AuthorsDAO extends JpaRepository<Author, Long> {


    boolean existsByEmail(String email);

}

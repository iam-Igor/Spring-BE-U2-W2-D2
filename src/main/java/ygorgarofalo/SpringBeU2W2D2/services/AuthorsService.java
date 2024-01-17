package ygorgarofalo.SpringBeU2W2D2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;
import ygorgarofalo.SpringBeU2W2D2.exceptions.NotFoundException;
import ygorgarofalo.SpringBeU2W2D2.repositories.AuthorsDAO;

@Service
public class AuthorsService {

    @Autowired
    AuthorsDAO authorsDAO;


    public Page<Author> getAuthors(int page, int size, String order) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        return authorsDAO.findAll(pageable);
    }


    public Author findById(long id) {

        return authorsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Author save(Author author) {

        author.setAvatar(author.getName(), author.getSurname());
        author.setBirthDate();
        return authorsDAO.save(author);

    }


    public Author findByIdAndUpdate(long id, Author body) {

        Author found = this.findById(id);

        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setAvatar(body.getName(), body.getSurname());
        authorsDAO.save(found);
        return found;

    }


    public void findByIdAndDelete(long id) {

        Author found = this.findById(id);

        authorsDAO.delete(found);
    }

}

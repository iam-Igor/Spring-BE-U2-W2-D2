package ygorgarofalo.SpringBeU2W2D2.services;

import org.springframework.stereotype.Service;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorsService {

    private List<Author> authorList = new ArrayList<>();


    public List<Author> getAuthors() {
        return this.authorList;
    }


    public Author findById(int id) {
        Author found = null;

        for (Author author : authorList) {
            if (author.getId() == id) {
                found = author;
            } else {
                return found;
            }
        }
        return found;
    }


    public Author save(Author author) {

        this.authorList.add(author);
        return author;

    }


    public Author findByIdAndUpdate(int id, Author body) {
        Author found = null;
        for (Author author : authorList) {
            if (body.getId() == id) {
                found = author;
                found.setName(author.getName());
                found.setSurname(author.getSurname());
                found.setEmail(author.getEmail());
            } else {
                return found;
            }
        }
        return found;


    }

}

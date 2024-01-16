package ygorgarofalo.SpringBeU2W2D2.services;

import org.springframework.stereotype.Service;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;

import java.util.ArrayList;
import java.util.Iterator;
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
        for (Author author : authorList) {
            if (author.getId() == id) {
                author.setId(id);
                author.setName(body.getName());
                author.setSurname(body.getSurname());
                author.setEmail(body.getEmail());
                author.setAvatar(body.getAvatar());
                return author;
            }
        }
        return null;
    }


    public void findByIdAndDelete(int id) {

        Iterator<Author> authorIterator = this.authorList.iterator();

        while (authorIterator.hasNext()) {
            Author actualAuthor = authorIterator.next();

            if (actualAuthor.getId() == id) {
                authorIterator.remove();
            }
        }
    }

}

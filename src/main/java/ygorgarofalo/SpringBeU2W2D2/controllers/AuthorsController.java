package ygorgarofalo.SpringBeU2W2D2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;
import ygorgarofalo.SpringBeU2W2D2.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    //GET su una lista di autori:
    @GetMapping
    public List<Author> getAuthors() {
        return authorsService.getAuthors();
    }


    //GET su un autore in particolare
    @GetMapping("/{id}")
    public Author findById(@PathVariable int id) {
        return authorsService.findById(id);
    }

    //POST di un Autor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author) {
        return authorsService.save(author);
    }

    //PUT su un autore
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable int id, @RequestBody Author author) {

        return authorsService.findByIdAndUpdate(id, author);
    }


}

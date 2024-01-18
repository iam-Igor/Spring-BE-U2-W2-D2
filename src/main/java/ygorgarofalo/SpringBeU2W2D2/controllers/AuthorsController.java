package ygorgarofalo.SpringBeU2W2D2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;
import ygorgarofalo.SpringBeU2W2D2.payloadTemplates.AuthorPayloadDTO;
import ygorgarofalo.SpringBeU2W2D2.responses.AuthorResponseDTO;
import ygorgarofalo.SpringBeU2W2D2.services.AuthorsService;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    //GET su una lista di autori:
    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String order) {
        return authorsService.getAuthors(page, size, order);
    }


    //GET su un autore in particolare
    @GetMapping("/{id}")
    public Author findById(@PathVariable long id) {
        return authorsService.findById(id);
    }

    //POST di un Autor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponseDTO saveAuthor(@RequestBody AuthorPayloadDTO author) {
        Author newAuthor = authorsService.save(author);

        return new AuthorResponseDTO(newAuthor.getId());
    }

    //PUT su un autore
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable long id, @RequestBody Author author) {

        return authorsService.findByIdAndUpdate(id, author);
    }


    // Delete di un autore
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable long id) {
        authorsService.findByIdAndDelete(id);
    }

}

package ygorgarofalo.SpringBeU2W2D2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ygorgarofalo.SpringBeU2W2D2.config.MailSender;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;
import ygorgarofalo.SpringBeU2W2D2.exceptions.BadRequestExc;
import ygorgarofalo.SpringBeU2W2D2.exceptions.NotFoundException;
import ygorgarofalo.SpringBeU2W2D2.payloadTemplates.AuthorPayloadDTO;
import ygorgarofalo.SpringBeU2W2D2.repositories.AuthorsDAO;

import java.io.IOException;

@Service
public class AuthorsService {

    @Autowired
    AuthorsDAO authorsDAO;

    @Autowired
    private MailSender mailSender;


    @Autowired
    private Cloudinary cloudinary;

    public Page<Author> getAuthors(int page, int size, String order) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        return authorsDAO.findAll(pageable);
    }


    public Author findById(long id) {

        return authorsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Author save(AuthorPayloadDTO author) {

        Author newAuthor = new Author(author
                .name(), author.surname(), author.email());

        if (authorsDAO.existsByEmail(author.email())) {
            throw new BadRequestExc("L'email " + author.email() + " è gia presente nel sistema.");
        } else {
            newAuthor.setBirthDate();
            mailSender.sendMail();

            return authorsDAO.save(newAuthor);
        }


    }


    public Author findByIdAndUpdate(long id, Author body) {

        Author found = this.findById(id);

        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        authorsDAO.save(found);
        return found;

    }


    public void findByIdAndDelete(long id) {

        Author found = this.findById(id);

        authorsDAO.delete(found);
    }


    public String uploadImage(MultipartFile file, long userId) throws IOException {

        Author found = this.findById(userId);

        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        found.setAvatar(url);

        authorsDAO.save(found);

        return url;
    }

}

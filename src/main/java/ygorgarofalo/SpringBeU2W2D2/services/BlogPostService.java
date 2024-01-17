package ygorgarofalo.SpringBeU2W2D2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ygorgarofalo.SpringBeU2W2D2.entities.Author;
import ygorgarofalo.SpringBeU2W2D2.entities.BlogPost;
import ygorgarofalo.SpringBeU2W2D2.exceptions.NotFoundException;
import ygorgarofalo.SpringBeU2W2D2.repositories.AuthorsDAO;
import ygorgarofalo.SpringBeU2W2D2.repositories.BlogPostsDAO;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostsDAO blogPostsDAO;

    @Autowired
    private AuthorsDAO authorsDAO;


    public Page<BlogPost> getAllBlogPosts(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return blogPostsDAO.findAll(pageable);
    }


    public BlogPost saveBlogPost(long authorId, BlogPost body) {

        Author found = authorsDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));


        body.setAuthor(found);

        blogPostsDAO.save(body);

        return body;

    }


    public BlogPost findById(long id) {
        return blogPostsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }


    public BlogPost findByIdAndUpdate(long id, BlogPost body) {

        BlogPost found = this.findById(id);

        found.setCategory(body.getCategory());
        found.setTitle(body.getTitle());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());
        found.setCoverImg(body.getCoverImg());
        blogPostsDAO.save(found);
        return found;

    }


    public void findByIdAndDelete(long id) {

        BlogPost found = this.findById(id);

        blogPostsDAO.delete(found);

    }


    public Page<BlogPost> getBlogPostsByCategory(String category, int page, int size, String order) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        return blogPostsDAO.findByCategory(category, pageable);
    }

}

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
import ygorgarofalo.SpringBeU2W2D2.payloadTemplates.BlogPostPayloadDTO;
import ygorgarofalo.SpringBeU2W2D2.payloadTemplates.BlogPostUpdateDTO;
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


    public BlogPost saveBlogPost(BlogPostPayloadDTO body) {

        Author found = authorsDAO.findById(body.authorId()).orElseThrow(() -> new NotFoundException(body.authorId()));

        BlogPost newBlogPost = new BlogPost(body.category(), body.title(), body.imgUrl(), body.content(), body.readingTime());
        newBlogPost.setAuthor(found);

        return blogPostsDAO.save(newBlogPost);


    }


    public BlogPost findById(long id) {
        return blogPostsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }


    public BlogPost findByIdAndUpdate(long id, BlogPostUpdateDTO body) {

        BlogPost found = this.findById(id);

        if (body.authorId() > 0) {
            Author AuthFound = authorsDAO.findById(body.authorId()).orElseThrow(() -> new NotFoundException(body.authorId()));
            found.setAuthor(AuthFound);
        }

        found.setCategory(body.category());
        found.setTitle(body.title());
        found.setContent(body.content());
        found.setReadingTime(body.readingTime());
        found.setCoverImg(body.imgUrl());
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

package ygorgarofalo.SpringBeU2W2D2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.SpringBeU2W2D2.entities.BlogPost;
import ygorgarofalo.SpringBeU2W2D2.services.BlogPostService;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;


    // GET su una lista di blogPosts
    @GetMapping
    public Page<BlogPost> getBlogPosts(@RequestParam(required = false) String category,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String order) {

        if (category != null) {
            return blogPostService.getBlogPostsByCategory(category, page, size, order);
        } else {
            return blogPostService.getAllBlogPosts(page, size, order);
        }
    }


    //POST di un BlogPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestParam long authorId, @RequestBody BlogPost body) {
        return blogPostService.saveBlogPost(authorId, body);
    }

    //GET di un Blog Post tramite id

    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable long id) {

        return blogPostService.findById(id);
    }


    //PUT di un blog Post

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable long id, @RequestBody BlogPost body) {
        return blogPostService.findByIdAndUpdate(id, body);
    }


    //DELETE di un blog Post
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable long id) {
        blogPostService.findByIdAndDelete(id);
    }


}

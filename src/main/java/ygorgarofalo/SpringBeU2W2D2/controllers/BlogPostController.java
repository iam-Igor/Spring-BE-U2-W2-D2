package ygorgarofalo.SpringBeU2W2D2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.SpringBeU2W2D2.entities.BlogPost;
import ygorgarofalo.SpringBeU2W2D2.services.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;


    // GET su una lista di blogPosts
    @GetMapping
    public List<BlogPost> getBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }


    //POST di un BlogPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody BlogPost body) {
        return blogPostService.saveBlogPost(body);
    }

    //GET di un Blog Post tramite id

    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable int id) {

        return blogPostService.findById(id);
    }


    //PUT di un blog Post

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable int id, @RequestBody BlogPost body) {
        return blogPostService.findByIdAndUpdate(id, body);
    }


    //DELETE di un blog Post
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable int id) {
        blogPostService.findByIdAndDelete(id);
    }


    //GET di una lista di blog filtrata per categoria scelta
    @GetMapping("/category/{category}")
    public List<BlogPost> getListByCategory(@PathVariable String category) {
        return blogPostService.getBlogPostsByCategory(category);
    }
}

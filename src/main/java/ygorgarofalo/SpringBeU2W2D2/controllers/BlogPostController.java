package ygorgarofalo.SpringBeU2W2D2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.SpringBeU2W2D2.entities.BlogPost;
import ygorgarofalo.SpringBeU2W2D2.payloadTemplates.BlogPostPayloadDTO;
import ygorgarofalo.SpringBeU2W2D2.payloadTemplates.BlogPostUpdateDTO;
import ygorgarofalo.SpringBeU2W2D2.responses.BlogPostResponseDTO;
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
    public BlogPostResponseDTO saveBlogPost(@RequestBody BlogPostPayloadDTO body) {

        BlogPost newBlogpost = blogPostService.saveBlogPost(body);

        return new BlogPostResponseDTO(newBlogpost.getId());
    }

    //GET di un Blog Post tramite id
    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable long id) {

        return blogPostService.findById(id);
    }


    //PUT di un blog Post
    @PutMapping("/{id}")
    public BlogPostResponseDTO updatePost(@PathVariable long id, @RequestBody BlogPostUpdateDTO body) {

        BlogPost updatedBlogPost = blogPostService.findByIdAndUpdate(id, body);

        return new BlogPostResponseDTO(id);
    }


    //DELETE di un blog Post
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable long id) {
        blogPostService.findByIdAndDelete(id);
    }


}

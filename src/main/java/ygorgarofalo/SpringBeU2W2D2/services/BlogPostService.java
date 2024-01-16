package ygorgarofalo.SpringBeU2W2D2.services;

import org.springframework.stereotype.Service;
import ygorgarofalo.SpringBeU2W2D2.entities.BlogPost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BlogPostService {

    public List<BlogPost> blogPostList = new ArrayList<>();


    public List<BlogPost> getAllBlogPosts() {
        return this.blogPostList;
    }


    public BlogPost saveBlogPost(BlogPost body) {
        blogPostList.add(body);
        return body;
    }


    public BlogPost findById(int id) {

        BlogPost found = null;

        for (BlogPost post : blogPostList) {
            if (post.getId() == id) {
                found = post;
            } else {
                return found;
            }
        }
        return found;
    }


    public BlogPost findByIdAndUpdate(int id, BlogPost body) {
        for (BlogPost blog : blogPostList) {
            if (blog.getId() == id) {
                blog.setId(id);
                blog.setCategory(body.getCategory());
                blog.setTitle(body.getTitle());
                blog.setContent(body.getContent());
                blog.setReadingTime(body.getReadingTime());
                blog.setCoverImg(body.getCoverImg());
                return blog;
            }
        }
        return null;
    }


    public void findByIdAndDelete(int id) {
        Iterator<BlogPost> blogPostIterator = this.blogPostList.iterator();

        while (blogPostIterator.hasNext()) {
            BlogPost actualAuthor = blogPostIterator.next();

            if (actualAuthor.getId() == id) {
                blogPostIterator.remove();
            }
        }
    }

}

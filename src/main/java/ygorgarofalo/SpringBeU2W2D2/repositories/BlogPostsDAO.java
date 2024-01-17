package ygorgarofalo.SpringBeU2W2D2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ygorgarofalo.SpringBeU2W2D2.entities.BlogPost;

@Repository
public interface BlogPostsDAO extends JpaRepository<BlogPost, Long> {


    Page<BlogPost> findByCategory(String category, Pageable pageable);

}

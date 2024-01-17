package ygorgarofalo.SpringBeU2W2D2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue
    private long id;

    private String category;

    private String title;

    private String coverImg;

    private String content;

    private double readingTime;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;


    public BlogPost(String category, String title, String coverImg, String content, double readingTime) {
        this.category = category;
        this.title = title;
        this.coverImg = coverImg;
        this.content = content;
        this.readingTime = readingTime;
    }
}

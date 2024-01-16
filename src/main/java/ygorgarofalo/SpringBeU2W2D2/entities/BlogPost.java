package ygorgarofalo.SpringBeU2W2D2.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class BlogPost {

    private int id;

    private String category;

    private String title;

    private String coverImg;

    private String content;

    private double readingTime;


    public BlogPost(String category, String title, String coverImg, String content, double readingTime) {
        Random rndm = new Random();
        this.id = rndm.nextInt(1, 100);
        this.category = category;
        this.title = title;
        this.coverImg = coverImg;
        this.content = content;
        this.readingTime = readingTime;
    }
}

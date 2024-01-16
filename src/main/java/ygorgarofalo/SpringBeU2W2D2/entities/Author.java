package ygorgarofalo.SpringBeU2W2D2.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@ToString
public class Author {

    private int id;

    private String name;

    private String surname;

    private String email;

    private LocalDate birthDate;

    private String avatar;

    public Author(String name, String surname, String email) {
        Random rndm = new Random();
        this.id = rndm.nextInt(101, 500);
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = LocalDate.now();
    }


}

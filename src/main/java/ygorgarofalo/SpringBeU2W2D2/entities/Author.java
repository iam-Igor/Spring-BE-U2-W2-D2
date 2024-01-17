package ygorgarofalo.SpringBeU2W2D2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String surname;

    private String email;

    private LocalDate birthDate;

    private String avatar;


    public Author(String name, String surname, String email) {

        this.name = name;
        this.surname = surname;
        this.avatar = setAvatar(name, surname);
        this.email = email;
        this.birthDate = setBirthDate();
    }


    public String setAvatar(String name, String surname) {
        return this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }

    public LocalDate setBirthDate() {
        return this.birthDate = LocalDate.now();
    }
}

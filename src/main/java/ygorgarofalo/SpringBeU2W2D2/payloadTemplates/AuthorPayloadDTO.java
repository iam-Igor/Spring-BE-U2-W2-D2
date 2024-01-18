package ygorgarofalo.SpringBeU2W2D2.payloadTemplates;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthorPayloadDTO(

        @NotEmpty(message = "Campo obbligatorio per la proprietà name.")
        String name,

        @NotEmpty(message = "Campo obbligatorio per la proprietà surname.")
        String surname,

        @Email
        @NotEmpty(message = "L'indirizzo email inserito non è un indirizzo valido.")
        String email) {


}

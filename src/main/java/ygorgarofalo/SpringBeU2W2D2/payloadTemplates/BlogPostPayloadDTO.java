package ygorgarofalo.SpringBeU2W2D2.payloadTemplates;

import jakarta.validation.constraints.NotEmpty;

public record BlogPostPayloadDTO(
        @NotEmpty(message = "Campo obbligatorio per la proprietà category.")
        String category,

        @NotEmpty(message = "Campo obbligatorio per la proprietà title.")
        String title,
        @NotEmpty(message = "Campo obbligatorio per la proprietà coverImg.")
        String imgUrl,
        @NotEmpty(message = "Campo obbligatorio per la proprietà content.")
        String content,
        @NotEmpty(message = "Campo obbligatorio per la proprietà readingTime.")
        double readingTime,

        @NotEmpty(message = "Campo obbligatorio per la proprietà authorId.")
        long authorId

) {
}

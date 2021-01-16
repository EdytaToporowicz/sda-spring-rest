package pl.blogservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import pl.blogservice.model.Topic;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostRequest {

    @Max(99999)
    @Min(1)
    private long authorId;

    @Size(min = 5, max = 50)
    private String title;

    @NonNull
    @Size(max = 1000)
    private String content;

    @NotNull
    private Topic topic;

    @AssertTrue(message = "Musi zaczynac sie z wielkiej litery")
    boolean isTitleValid() {
        return Character.isUpperCase(title.charAt(0));
    }

}

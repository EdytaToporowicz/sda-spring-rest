package pl.blogservice.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogPost {

    private final @NonNull long id;
    private final @NonNull User author;
    private final @NonNull String title;
    private final @NonNull String content;
    private final @NonNull Topic topic;
    private final @NonNull LocalDateTime created;
    private LocalDateTime modified;
    private List<Comment> comments;

}

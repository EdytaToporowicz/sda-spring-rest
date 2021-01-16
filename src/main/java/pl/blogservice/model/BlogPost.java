package pl.blogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

    private
    long id;
    private
    User author;
    private
    String title;
    private
    String content;
    private
    Topic topic;
    private
    LocalDateTime created;
    private LocalDateTime modified;
    private List<Comment> comments;


    public BlogPost(long id, User author, String title, String content, Topic topic, LocalDateTime created) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.created = created;
    }
}

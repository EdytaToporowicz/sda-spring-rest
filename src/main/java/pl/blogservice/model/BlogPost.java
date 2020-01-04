package pl.blogservice.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BlogPost {

    private long id;
    private User author;
    private String title;
    private String content;
    private Topic topic;
    private LocalDateTime created;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return id == blogPost.id &&
                Objects.equals(author, blogPost.author) &&
                Objects.equals(title, blogPost.title) &&
                Objects.equals(content, blogPost.content) &&
                topic == blogPost.topic &&
                Objects.equals(created, blogPost.created) &&
                Objects.equals(modified, blogPost.modified) &&
                Objects.equals(comments, blogPost.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, content, topic, created, modified, comments);
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", topic=" + topic +
                ", created=" + created +
                ", modified=" + modified +
                ", comments=" + comments +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

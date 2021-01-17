package pl.blogservice.exception;

public class BlogPostNotFoundException extends RuntimeException{

    public BlogPostNotFoundException(String message) {
        super(message);
    }
}

package pl.blogservice.service;

import org.springframework.stereotype.Component;
import pl.blogservice.model.BlogPost;
import pl.blogservice.model.request.BlogPostRequest;

@Component
public class BlogPostMapper {

    public BlogPost map(BlogPostRequest blogPostRequest) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogPostRequest.getTitle());
        blogPost.setTopic(blogPostRequest.getTopic());
        blogPost.setContent(blogPostRequest.getContent());
        return blogPost;
    }
}

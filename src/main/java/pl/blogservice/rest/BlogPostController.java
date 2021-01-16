package pl.blogservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.blogservice.model.BlogPost;
import pl.blogservice.model.Topic;

import pl.blogservice.model.request.BlogPostRequest;
import pl.blogservice.service.BlogPostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/blogPosts/{blogPostId}")
    public ResponseEntity<BlogPost> findById(@PathVariable long blogPostId) {
        final BlogPost blogPost = blogPostService.findById(blogPostId);
        if (blogPost != null) {
            return ResponseEntity.ok(blogPostService.findById(blogPostId));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/blogPosts")
    public ResponseEntity<List<BlogPost>> findByTopic(@RequestParam(required = false) Topic topic) {
        if (topic != null) {
            return ResponseEntity.ok(blogPostService.findByTopic(topic));
        }
        return ResponseEntity.ok(blogPostService.findAll());
    }

    @DeleteMapping("/blogPosts/{blogPostId}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable long blogPostId) {
        blogPostService.deleteBlogPost(blogPostId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/blogPosts", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPostRequest blogPostRequest) {
        BlogPost blogPost = blogPostService.createBlogPost(blogPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPost);
    }

}

package pl.blogservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.blogservice.TimestampProvider;
import pl.blogservice.exception.BlogPostNotFoundException;
import pl.blogservice.model.BlogPost;
import pl.blogservice.model.Topic;
import pl.blogservice.model.User;
import pl.blogservice.model.request.BlogPostRequest;
import pl.blogservice.repository.DataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class BlogPostService {
    private static final Logger logger = LoggerFactory.getLogger(BlogPostService.class);

    private final DataRepository<BlogPost> dataRepository;
    private final TimestampProvider timestampProvider;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public BlogPostService(DataRepository<BlogPost> dataRepository, TimestampProvider timestampProvider, BlogPostMapper blogPostMapper) {
        this.dataRepository = dataRepository;
        this.timestampProvider = timestampProvider;
        this.blogPostMapper = blogPostMapper;
    }


    public void save(final BlogPost blogPost) {                         //sprawdzić
        blogPost.setModified(timestampProvider.getTimestamp());
        dataRepository.save(blogPost);
    }

    public BlogPost findById(final long id) {
        return dataRepository.findAll().stream()
                .filter(post -> id == post.getId())
                .findFirst()
                .orElseThrow(() -> new BlogPostNotFoundException("could not find post by id " + id));     //do obsługi wyjątku
    }

    public List<BlogPost> findAll() {
        return dataRepository.findAll();
    }

    public List<BlogPost> findByTopic(final Topic topic) {
        return dataRepository.findAll()
                .stream()
                .filter(blogPost -> blogPost.getTopic().equals(topic))
                .collect(Collectors.toList());
    }

    public void deleteBlogPost(long blogPostId) {
        dataRepository.findAll()
                .stream()
                .filter(blogPost -> blogPost.getId() == blogPostId)
                .findFirst()
                // .ifPresent(blogPost -> dataRepository.remove(blogPost));
                .ifPresentOrElse(blogPost -> dataRepository.remove(blogPost), () -> {
                    throw new BlogPostNotFoundException("BlogPost doesnt exist-coul not delete: " + blogPostId);
                });
    }

    public BlogPost createBlogPost(BlogPostRequest blogPostRequest) {
        BlogPost blogPost = blogPostMapper.map(blogPostRequest);
        blogPost.setCreated(LocalDateTime.now());
        blogPost.setId(new Random().nextLong());
        User author = new User(blogPostRequest.getAuthorId(), null, null);
        blogPost.setAuthor(author);
        dataRepository.save(blogPost);
        return blogPost;
    }


}

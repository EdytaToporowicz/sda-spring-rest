package pl.blogservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.blogservice.TimestampProvider;
import pl.blogservice.model.BlogPost;
import pl.blogservice.repository.DataRepository;

@Component
public class BlogPostService {

    private static final Logger logger = LoggerFactory.getLogger(BlogPostService.class);

    private final DataRepository<BlogPost> dataRepository;
    private final TimestampProvider timestampProvider;

    @Autowired
    public BlogPostService(
            final DataRepository<BlogPost> dataRepository,
            final TimestampProvider timestampProvider
    ) {
        this.dataRepository = dataRepository;
        this.timestampProvider = timestampProvider;
    }

    public void save(final BlogPost blogPost) {
        blogPost.setModified(timestampProvider.getTimestamp());
        dataRepository.save(blogPost);
    }

    public BlogPost findById(final long id) {
        return dataRepository.findAll().stream()
                .filter(post -> id == post.getId())
                .findFirst()
                .orElse(null);
    }

}

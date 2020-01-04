package pl.blogservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.blogservice.ContentAnalyser;
import pl.blogservice.TimestampProvider;
import pl.blogservice.decorator.StringDecorator;
import pl.blogservice.model.BlogPost;
import pl.blogservice.repository.DataRepository;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlogPostService {

    private static final Logger logger = LoggerFactory.getLogger(BlogPostService.class);

    private final DataRepository<BlogPost> dataRepository;
    private final StringDecorator uppercaseStringDecorator;
    private final TimestampProvider timestampProvider;
    private final ContentAnalyser contentAnalyser;

    @Autowired
    public BlogPostService(
            final DataRepository<BlogPost> dataRepository,
            final @Qualifier("uppercaseStringDecorator") StringDecorator stringDecorator,
            final TimestampProvider timestampProvider,
            final ContentAnalyser contentAnalyser) {
        this.dataRepository = dataRepository;
        this.uppercaseStringDecorator = stringDecorator;
        this.timestampProvider = timestampProvider;
        this.contentAnalyser = contentAnalyser;
    }

    public void save(final BlogPost blogPost) {
        if (!contentAnalyser.analyse(blogPost.getContent())) {
            blogPost.setCreated(timestampProvider.getTimestamp());
            dataRepository.save(blogPost);
        } else {
            logger.warn("BlogPost " + blogPost.getId() + " contained forbidden word(s) and was not saved");
        }
    }

    public BlogPost findById(final long id) {
        return dataRepository.findAll().stream()
                .filter(post -> id == post.getId())
                .findFirst()
                .map(post -> {
                    post.setTitle(uppercaseStringDecorator.decorate(post.getTitle()));
                    return post;
                })
                .orElse(null);
    }

}

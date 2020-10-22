package pl.blogservice.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import pl.blogservice.model.BlogPost;
import pl.blogservice.model.Topic;
import pl.blogservice.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogPostRepository implements DataRepository<BlogPost>, InitializingBean {

    private final List<BlogPost> blogPosts = new ArrayList<>();

    @Override
    public void save(final BlogPost blogPost) {
        blogPosts.add(blogPost);
    }

    @Override
    public List<BlogPost> findAll() {
        return blogPosts;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        final User janek = new User(1, "janko_muzykant", "janek@gmail.com");
        final User kasia = new User(2, "kaska", "kasia@gmail.com");
        this.save(new BlogPost(1, janek, "Spring framework - wady i zalety", "Spring ma same zalety",
                Topic.PROGRAMMING, LocalDateTime.now()));
        this.save(new BlogPost(2, kasia, "Czy Java umiera?", "Java ma sie dobrze jak nigdy", Topic.PROGRAMMING,
                LocalDateTime.now()));
        this.save(new BlogPost(3, janek, "Snowbard - jak zacząć i się nie zabić?", "Weź instruktora, ale i tak ne " +
                "masz gwarancji", Topic.SPORTS, LocalDateTime.now()));
        this.save(new BlogPost(4, kasia, "  Właśnie to jest mój   -    PIerwSzY    -  BLOG post.", "  nic specjalnego" +
                "    ", Topic.LIFESTYLE, LocalDateTime.now()));
        this.save(new BlogPost(5, janek, "Wolność słowa w internecie", "Cenzura twittów to zamach na wolność słowa w " +
                "internecie", Topic.SOCIAL_MEDIA, LocalDateTime.now()));
    }

}

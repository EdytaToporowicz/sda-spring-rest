package pl.blogservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ContentAnalyser {

    private final List<String> forbiddenWords;

    public ContentAnalyser(final @Value("${blog.forbidden.words}") String[] forbiddenWords) {
        this.forbiddenWords = Arrays.asList(forbiddenWords);
    }

    public boolean analyse(final String content) {
        final List<String> contentAsWords = Arrays.asList(content.split("\\s+"));
        if(!contentAsWords.isEmpty()){
            return forbiddenWords.stream().anyMatch(forbiddenPhrase -> {
                        return contentAsWords.stream()
                                .map(String::toLowerCase)
                                .anyMatch(element -> element.equalsIgnoreCase(forbiddenPhrase));
                    }
            );
        }
        return false;
    }
}

package pl.blogservice.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Priority(2)
@Qualifier("spaceRemovingStringDecorator")
@Component
public class SpaceRemovingStringDecorator implements StringDecorator {

    private static final Logger logger = LoggerFactory.getLogger(SpaceRemovingStringDecorator.class);

    @Override
    public String decorate(String input) {
        logger.info("SpaceRemovingStringDecorator was invoked");
        return input.replaceAll(" +", " ").trim();
    }
}

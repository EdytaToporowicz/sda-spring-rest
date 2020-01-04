package pl.blogservice.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Priority(1)
@Qualifier("uppercaseStringDecorator")
@Component
public class UppercaseStringDecorator implements StringDecorator {

    private static final Logger logger = LoggerFactory.getLogger(UppercaseStringDecorator.class);

    @Override
    public String decorate(String input) {
        logger.info("UpperCaseStringDecorator was invoked");
        return input.toUpperCase();
    }
}

package vn.edu.rmit.Utilities;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/10/13
 * Time: 3:57 PM
 * References:
 * http://javapapers.com/spring/spring-applicationcontext/
 */
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext ctx = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        return ctx;
    }
}

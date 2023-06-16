package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Greeter greeter = context.getBean("greeter", Greeter.class);
        String msg = greeter.greet("스프링");
        System.out.println(msg);
        context.close();
    }
}

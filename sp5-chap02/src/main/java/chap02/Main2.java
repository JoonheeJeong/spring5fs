package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Greeter greeter1 = context.getBean("greeter", Greeter.class);
        Greeter greeter2 = context.getBean("greeter", Greeter.class);
        System.out.println("(greeter1 == greeter2) = " + (greeter1 == greeter2));
        context.close();
    }
}

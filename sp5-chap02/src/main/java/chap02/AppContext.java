package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public Greeter greeter() {
        Greeter greeter = new Greeter();
        greeter.setFormat("안녕하세요, %s!");
        return greeter;
    }
}

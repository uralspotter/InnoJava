import entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import service.UserService;

@ComponentScan
public class Main implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Override
    public void run(String... args){
        try {
            User user = new User(5L, "test");

            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
            UserService service = context.getBean(UserService.class);
            service.save(user);
            service.update(user);
            log.debug(service.getById(1L).getUsername());
            service.deleteById(user.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}

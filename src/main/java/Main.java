import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("main-contex.xml");

    public static void main(String[] args) {

    }
}

package pl.coderslab.tanitabody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TanitaBodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TanitaBodyApplication.class, args);
    }

//    private static final Logger logger = Logger.getLogger(TanitaBodyApplication.class);
//
//    public static void main(String[]args) {
//        System.out.println(getLocalCurrentDate());
//    }
//
//    private static String getLocalCurrentDate() {
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("getLocalCurrentDate() is executed!");
//        }
//
//        LocalDate date = new LocalDate();
//        return date.toString();
//
//    }

}

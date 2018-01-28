import calculators.CalculateController;
import config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 *
 * Created by PC on 27.01.2018.
 */
public class Main {

  public static void main(String[] args) throws IOException {
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    CalculateController controller = context.getBean(Config.CALCULATE_CONTROLLER_BEAN, CalculateController.class);
    controller.startCalculate("test_file.txt");
  }
}

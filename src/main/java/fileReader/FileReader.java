package fileReader;


import calculators.CalculateController;
import config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by PC on 27.01.2018.
 */
public class FileReader implements IFileReader {

  @Override
  public void parseFile(String filePath) throws IOException {
    AnnotationConfigApplicationContext con = new AnnotationConfigApplicationContext(Config.class);
    CalculateController controller = con.getBean(Config.CALCULATE_CONTROLLER_BEAN, CalculateController.class);
    InputStream is = new FileInputStream(filePath);
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));
    String oneFileLine;
    int lineCount = 0;
    while ((oneFileLine = buf.readLine()) != null) {
      final List<Integer> fileArgs = new ArrayList<>();
      Arrays.stream(oneFileLine.split(" "))
              .forEach(oneArg -> fileArgs.add(Integer.parseInt(oneArg)));
      // каждую строку отправляем сразу на вычисление
      controller.sendToCalculateF1(fileArgs);
      lineCount++;
    }
    // отправляем общее количество строк в файле
    controller.setLineCount(lineCount);

  }
}

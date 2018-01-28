package calculators;

import config.Config;
import fileReader.FileReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Created by PC on 28.01.2018.
 */
public class CalculateController {

  private AtomicInteger localResult;
  private Integer lineCount = null;
  private AtomicInteger f2CalculateCount = new AtomicInteger();
  private ApplicationContext context;

  public void startCalculate(String filePath) throws IOException {
    context = new AnnotationConfigApplicationContext(Config.class);
    context.getBean(Config.FILE_READER_BEAN, FileReader.class).parseFile(filePath);
  }

  @Async
  public void sendToCalculateF1(List<Integer> calcArgs, int localCount) {
    context = new AnnotationConfigApplicationContext(Config.class);
    ICalculate f1Calculator = context.getBean(Config.F1_CACLULATOR_BEAN, F1Calculate.class);
    int f1Result = f1Calculator.calculate(calcArgs.get(0), calcArgs.get(1));
    if (this.localResult == null) {
      // если никаких результатов раньше не было, записываем
      this.localResult = new AtomicInteger(f1Result);
    } else {
      sendToCalculateF2(f1Result, getAndClearLocalResult(), localCount);
    }
    if (lineCount != null && (lineCount - 1) == f2CalculateCount.get()) {
      // количество выполнений f2 должно быть = <кол. строк в файле> - 1
      System.out.println("Result = " + this.localResult.get());
    }
  }

  public void sendToCalculateF2(int f1Result, int f1_2, int localCount) {
    // отправляем на вычисление f2
    f2CalculateCount.incrementAndGet();
    context = new AnnotationConfigApplicationContext(Config.class);
    ICalculate f2Calculator = context.getBean(Config.F2_CACLULATOR_BEAN, F2Calculate.class);
    int f2Result = f2Calculator.calculate(f1Result, f1_2);
    if (this.localResult == null) {
      this.localResult = new AtomicInteger(f2Result);
    } else {
      sendToCalculateF2(f2Result, getAndClearLocalResult(), --localCount);
    }
  }

  private int getAndClearLocalResult() {
    // достаём значение, чтобы отдать на вычисление следующего и очищаем
    int buf = this.localResult.get();
    this.localResult = null;
    return buf;
  }

  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
  }

}

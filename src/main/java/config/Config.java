package config;

import calculators.CalculateController;
import calculators.F1Calculate;
import calculators.F2Calculate;
import calculators.ICalculate;
import fileReader.FileReader;
import fileReader.IFileReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 *
 * Created by PC on 27.01.2018.
 */
@Configuration
@EnableAsync
public class Config implements AsyncConfigurer {

  public static final String F1_CACLULATOR_BEAN = "f1Caclulator";
  public static final String F2_CACLULATOR_BEAN = "f2Caclulator";
  public static final String CALCULATE_CONTROLLER_BEAN = "calculateController";
  public static final String FILE_READER_BEAN = "fileReader";

  @Bean(name = F1_CACLULATOR_BEAN)
  public ICalculate getF1Calculate() {
    return new F1Calculate();
  }

  @Bean(name = F2_CACLULATOR_BEAN)
  public ICalculate getF2Calculate() {
    return new F2Calculate();
  }

  @Bean(name = CALCULATE_CONTROLLER_BEAN)
  public CalculateController getCalculateController() {
    return new CalculateController();
  }

  @Bean(name = FILE_READER_BEAN)
  public IFileReader getFileReader() {
    return new FileReader();
  }

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(1);
    executor.setMaxPoolSize(10);
    executor.initialize();
    return executor;
  }
}

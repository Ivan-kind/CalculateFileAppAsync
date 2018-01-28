package Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import java.util.List;

/**
 *
 * Created by PC on 28.01.2018.
 */
@Aspect
public class Logger {

  @Before("execution(* calculators.CalculateController.startCalculate(..))")
  public void logBeforeStartCalculate(JoinPoint joinPoint) {
    System.out.println("Обработка файла: " + joinPoint.getArgs()[0].toString());
  }

  @Before("execution(* calculators.CalculateController.sendToCalculateF1(..))")
  public void logBeforeSendToCalculateF1(JoinPoint joinPoint) {
    List<Integer> args = (List<Integer>) joinPoint.getArgs()[0];
    System.out.println("Вычисление функции f1(" + args.get(0) + ", " + args.get(1) + ")");
  }

  @Before("execution(* calculators.CalculateController.sendToCalculateF2(..))")
  public void logBeforeSendToCalculateF2(JoinPoint joinPoint) {
    List<Integer> args = (List) joinPoint.getArgs()[0];
    System.out.println("Вычисление функции f2(" + args.get(0) + ", " + args.get(1) + ")");
  }

  @Before("execution(* calculators.CalculateController.setLineCount(..))")
  public void logBeforeSetLineCount(JoinPoint joinPoint) {
    System.out.println("Общее количество строк в файле = " + joinPoint.getArgs()[0].toString());
  }
}

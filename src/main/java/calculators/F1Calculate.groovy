package calculators

class F1Calculate implements ICalculate {

    @Override
    int calculate(int argOne, int argTwo) {
        print("f1 (" + argOne + ", " + argTwo + ")")
        return argOne - argTwo
    }
}
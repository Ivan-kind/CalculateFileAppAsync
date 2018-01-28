package calculators

class F2Calculate implements ICalculate {

    @Override
    int calculate(int argOne, int argTwo) {
        print("f2 (" + argOne + ", " + argTwo + ")")
        return argOne + argTwo
    }
}
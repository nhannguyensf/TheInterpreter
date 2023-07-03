package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class BopCode implements ByteCode {
    private String operator;

    public BopCode(String[] args) {
        this.operator = args[1];
    }

    @Override
    public void init(List<String> args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        int secondOperand = vm.popRunStack();
        int firstOperand = vm.popRunStack();

        switch (operator) {
            case "+" -> vm.pushRunStack(firstOperand + secondOperand);
            case "-" -> vm.pushRunStack(firstOperand - secondOperand);
            case "/" -> vm.pushRunStack(firstOperand / secondOperand);
            case "*" -> vm.pushRunStack(firstOperand * secondOperand);
            case "==" -> vm.pushRunStack(firstOperand == secondOperand ? 1 : 0);
            case "!=" -> vm.pushRunStack(firstOperand != secondOperand ? 1 : 0);
            case "<=" -> vm.pushRunStack(firstOperand <= secondOperand ? 1 : 0);
            case "<" -> vm.pushRunStack(firstOperand < secondOperand ? 1 : 0);
            case ">=" -> vm.pushRunStack(firstOperand >= secondOperand ? 1 : 0);
            case ">" -> vm.pushRunStack(firstOperand > secondOperand ? 1 : 0);
            case "&" -> vm.pushRunStack((firstOperand != 0 && secondOperand != 0) ? 1 : 0);
            case "|" -> vm.pushRunStack((firstOperand != 0 || secondOperand != 0) ? 1 : 0);
            default -> throw new RuntimeException("Invalid operator: " + operator);
        }
    }

    @Override
    public String toString(VirtualMachine vm) {
        return "BOP " + operator;
    }
}

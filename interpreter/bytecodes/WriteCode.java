package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class WriteCode implements ByteCode {
    public WriteCode(String[] args) {
    }

    @Override
    public void init(List<String> args) {
        // No initialization needed
    }

    @Override
    public void execute(VirtualMachine vm) {
        int topValue = vm.peekRunStack();
        System.out.println(topValue);
    }

    @Override
    public String toString(VirtualMachine vm) {
        return "WRITE";
    }
}

package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class WriteCode implements ByteCode {
    public WriteCode(String[] args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        int topValue = vm.peekRunStack();
        System.out.println(topValue);
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}

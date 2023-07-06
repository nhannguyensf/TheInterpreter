package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class HaltCode implements ByteCode {
    public HaltCode(String[] args) {
        // Halt takes no arguments, so there's nothing to do here.
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.halt();
    }

    @Override
    public String toString() {
        // Halt bytecode is to not be dumped, this should have never been called.
        return "";
    }
}

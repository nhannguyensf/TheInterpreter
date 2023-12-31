package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class PopCode implements ByteCode {
    private int popCount;

    public PopCode(String[] args) {
        if (args.length != 2) { // the first element of args is byte code name, so the actual arg is at index 1
            throw new IllegalArgumentException("Pop ByteCode requires 1 argument: number of values to be popped.");
        }
        this.popCount = Integer.parseInt(args[1]); // get the arg
    }

    @Override
    public void execute(VirtualMachine vm) {
        int popAllowed = Math.min(this.popCount, vm.getFrameSize());
        for (int i = 0; i < popAllowed; ++i) {
            vm.popRunStack();
        }
    }

    @Override
    public String toString() {
        return "POP " + this.popCount;
    }
}

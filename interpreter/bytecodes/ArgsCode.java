package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ArgsCode implements ByteCode {
    private int numArgs;

    public ArgsCode(String[] args) {
        this.numArgs = Integer.parseInt(args[1]);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(numArgs);
    }

    @Override
    public String toString() {
        return "ARGS " + numArgs;
    }
}

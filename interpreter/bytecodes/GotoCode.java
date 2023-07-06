package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class GotoCode implements ByteCode {
    private String label;
    private int targetAddress;

    public GotoCode(String[] args) {
        this.label = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(this.targetAddress);
    }

    public String getLabel() {
        return this.label;
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    @Override
    public String toString() {
        return "GOTO " + this.label;
    }
}

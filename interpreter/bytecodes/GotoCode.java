package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class GotoCode implements ByteCode {
    private String label;
    private int targetAddress;

    public GotoCode(String[] args) {
        this.label = args[1];

    }

    @Override
    public void init(List<String> args) {

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
    public String toString(VirtualMachine vm) {
        return "GOTO " + this.label;
    }
}

package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LabelCode implements ByteCode {
    private String label;

    public LabelCode(String[] args) {
        this.label = args[1];
    }

    @Override
    public void init(List<String> args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        // No functionality for LabelCode
    }

    @Override
    public String toString() {
        return "LABEL " + label;
    }

    public String getLabel() {
        return this.label;
    }
}

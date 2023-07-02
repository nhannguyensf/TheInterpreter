package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LabelCode implements ByteCode {
    private String label;

    public LabelCode(String[] args) {
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Do nothing
    }

    @Override
    public String toString(VirtualMachine vm) {
        return "LABEL " + label;
    }

    public String getLabel() {
        return this.label;
    }
}

package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CallCode implements ByteCode {
    private String label;
    private String baseID;
    private String args = "";
    private int targetAddress;

    public CallCode(String[] args) {
        this.label = args[1];
        if (this.label.contains("<")) {
            this.baseID = (this.label.substring(0, this.label.indexOf("<")));
        } else {
            this.baseID = this.label;
        }
    }

    @Override
    public void init(List<String> arguments) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter());
        vm.setProgramCounter(targetAddress);
        this.args = (vm.getFrameArguments().toString()).substring(1, vm.getFrameArguments().toString().length() - 1);
    }

    public void setAddress(int address) {
        this.targetAddress = address;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        if (!this.args.isEmpty()) {
            return "CALL " + this.label + "\t" + baseID + "(" + this.args + ")";
        } else {
            return "CALL " + this.label + "\t" + baseID + "()";
        }
    }
}

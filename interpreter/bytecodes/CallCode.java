package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class CallCode implements ByteCode {
    private String label;
    private String baseID;
    private String args = "";
    private int targetAddress;

    public CallCode(String[] args) {
        this.label = args[1];
        if (this.label.contains("<<")) {
            this.baseID = (this.label.substring(0, this.label.indexOf("<<")));
        } else {
            this.baseID = this.label;
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter());
        vm.setProgramCounter(targetAddress);
        String argsToString = (vm.getFrameArguments().toString()).replace(" ","");
        this.args = argsToString.substring(1, argsToString.length()-1);
    }

    public void setAddress(int address) {
        this.targetAddress = address;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return "CALL " + this.label + "\t" + baseID + "(" + this.args + ")";
    }
}

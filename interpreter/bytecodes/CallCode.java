package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class CallCode extends JumpCode {
    private String baseID;
    private String args = "";

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
        vm.setProgramCounter(this.targetAddress);
        String argsToString = (vm.getFrameArguments().toString()).replace(" ", "");
        this.args = argsToString.substring(1, argsToString.length() - 1);
    }

    @Override
    public String toString() {
        return "CALL " + this.label + "\t" + this.baseID + "(" + this.args + ")";
    }
}

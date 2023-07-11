package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class FalseBranchCode extends JumpCode {
    private String label;
    private int targetAddress;

    public FalseBranchCode(String[] args) {
        this.label = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.popRunStack() == 0) {
            vm.setProgramCounter(this.targetAddress);
        }
    }

    public String getLabel() {
        return this.label;
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    @Override
    public String toString() {
        return "FALSEBRANCH " + this.label;
    }
}

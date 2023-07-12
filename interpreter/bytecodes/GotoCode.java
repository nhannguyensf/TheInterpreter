package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class GotoCode extends JumpCode {
    public GotoCode(String[] args) {
        this.label = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(this.targetAddress);
    }

    @Override
    public String toString() {
        return "GOTO " + this.label;
    }
}

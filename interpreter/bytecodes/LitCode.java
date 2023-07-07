package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class LitCode implements ByteCode {
    private String id;
    private int valueToPush;

    public LitCode(String[] args) {
        this.valueToPush = Integer.parseInt(args[1]);
        if (args.length == 3) {
            this.id = args[2];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunStack(this.valueToPush);
    }

    @Override
    public String toString() {
        String base = "LIT " + this.valueToPush;
        if (this.id != null) {
            base += " " + this.id + "\tint " + this.id;
        }
        return base;
    }
}


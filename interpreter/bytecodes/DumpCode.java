package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class DumpCode implements ByteCode,NotDumpable {
    private String mode;

    public DumpCode(String[] args) {
        this.mode = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (mode.equals("ON")) {
            vm.setDumping(true);
        } else if (mode.equals("OFF")) {
            vm.setDumping(false);
        }
    }

    @Override
    public String toString() {
        // Dump bytecode is to not be dumped, this should have never been called.
        return "";
    }
}

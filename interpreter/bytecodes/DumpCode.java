package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class DumpCode implements ByteCode {
    private String mode;
    public DumpCode(String[] args) {
        this.mode = args[1];
    }

    @Override
    public void init(List<String> args) {
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
    public String toString(VirtualMachine vm) {
        return "DUMP " + mode;
    }
}

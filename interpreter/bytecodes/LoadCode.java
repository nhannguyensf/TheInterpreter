package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class LoadCode implements ByteCode {
    private int offset;
    private String id;

    public LoadCode(String[] args) {
        offset = Integer.parseInt(args[1]);
        if (args.length > 2) {
            id = args[2];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        int value = vm.load(offset);
    }

    public String toString() {
        String base = "LOAD " + this.offset;
        if (this.id != null) {
            base += " " + this.id + "\t<load " + this.id + ">";
        }
        return base;
    }
}

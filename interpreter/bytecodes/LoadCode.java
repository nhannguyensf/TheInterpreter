package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class LoadCode implements ByteCode {
    private int offset;
    private String id;

    public LoadCode(String[] args) {
        this.offset = Integer.parseInt(args[1]);
        if (args.length > 2) {
            this.id = args[2];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (this.id != null) {
            System.out.println("LOAD " + this.offset + " " + this.id + "\t<load " + this.id + ">");
        } else {
            System.out.println("LOAD " + this.offset);
        }
    }

    @Override
    public String toString() {
        if (this.id != null) {
            return "LOAD " + this.offset + " " + this.id + "\t<load " + this.id + ">";
        } else {
            return "LOAD " + this.offset;
        }
    }
}

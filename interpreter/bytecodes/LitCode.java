package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class LitCode implements ByteCode {
    private String id;
    private int valueToPush;

    public LitCode(String[] args) {
        valueToPush = Integer.parseInt(args[1]);
        if (args.length == 3) {
            this.id = args[2];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunStack(valueToPush);
    }

    @Override
    public String toString() {
        String base = "LIT " + this.valueToPush;
        if (this.id != null) {
            base += " " + this.id + "\tint " + this.id;
        }
        return base;
    }

//    public static void main(String[] args) {
//        String[] x = {"LIT", "1"};
//        LitCode c = new LitCode(x);
//        System.out.println(c);
//    }
}


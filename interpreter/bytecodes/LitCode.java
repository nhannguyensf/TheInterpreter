package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

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
    public void init(List<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunStack(this.valueToPush);
    }

    @Override
    public String toString(VirtualMachine vm) {
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


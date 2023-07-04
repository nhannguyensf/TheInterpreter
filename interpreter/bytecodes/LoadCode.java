package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LoadCode implements ByteCode {
    private int offset;
    private String id;

    public LoadCode(String[] args) {
    }

    @Override
    public void init(List<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            id = args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        int value = vm.load(offset);
        //vm.pushRunStack(value);
    }

//    @Override
//    public String toString(VirtualMachine vm) {
//        if (this.id != null) {
//            return "LOAD " + this.offset + " " + this.id + "\t<load " + this.id + ">";
//        } else {
//            return "LOAD " + this.offset;
//        }
//    }
    public String toString() {
        if (this.id != null) {
            return "LOAD " + this.offset + " " + this.id + "\t<load " + this.id + ">";
        } else {
            return "LOAD " + this.offset;
        }
    }
}

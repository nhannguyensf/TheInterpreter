package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class StoreCode implements ByteCode {
    private int offset;
    private String id;
    private int value;

    public StoreCode(String[] args) {
        this.offset = Integer.parseInt(args[1]);
        if (args.length > 2) {
            this.id = args[2];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
           value =  vm.store(offset);
    }

    @Override
    public String toString() {
        String base = "STORE " + this.offset;
        if (this.id != null) {
            base += " " + this.id + "\t" + this.id + " = " + this.value;
        }
        return base;
    }
}

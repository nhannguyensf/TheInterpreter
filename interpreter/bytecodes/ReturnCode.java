package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class ReturnCode implements ByteCode {
    private String id;
    private int returnValue;

    public ReturnCode(String[] args) {
        if (args.length > 1) {
            this.id = args[1];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Save the top of the runtime stack as the return value
        this.returnValue = vm.peekRunStack();

        // Empty the current frame
        vm.emptyCurrentFrame();

        // Pop the top value from the framePointer stack to remove the frame boundary
        vm.popFrame();

        // Pop the top of the return address stack and save it into program counter
        vm.setProgramCounter(vm.popReturnAddress());

        // Save the return value at the top of the runtime stack
        vm.pushRunStack(returnValue);
    }

    @Override
    public String toString() {
        String base = "RETURN";
        if (this.id != null) {
            String baseID = this.id.split("<<")[0];
            base += " " + this.id + "  EXIT " + baseID + " : " + this.returnValue;
        }
        return base;
    }
}

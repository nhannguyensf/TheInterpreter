package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode implements ByteCode {
    private String id;

    public ReturnCode(String[] args) {
    }

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.id = args.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Save the top of the runtime stack as the return value
        int returnValue = vm.peekRunStack();

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
    public String toString(VirtualMachine vm) {
        String baseID = id.split("<<")[0];
        String returnValue = String.valueOf(vm.peekRunStack());

        if (id.contains("<<")) {
            return "RETURN " + id + " EXIT " + baseID + ":" + returnValue;
        } else {
            return "RETURN EXIT " + baseID + ":" + returnValue;
        }
    }
}

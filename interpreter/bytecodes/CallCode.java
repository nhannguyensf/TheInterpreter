package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CallCode implements ByteCode {
    private String label;
    private String baseID;
    private ArrayList<Integer> args;
    private int targetAddress;

    public CallCode(String[] args) {
        this.label = args[1];
    }

    @Override
    public void init(List<String> arguments) {
//        this.label = arguments.get(1);
//        if (label.contains("<<")) {
//            this.baseID = label.split("<<")[0];
//        } else {
//            this.baseID = label;
//        }
//        args = new ArrayList<>();
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter());
        vm.setProgramCounter(targetAddress);
    }

    public void setAddress(int address) {
        this.targetAddress = address;
    }
    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString(VirtualMachine vm) {
        String argsString = vm.getFrameArguments().stream()
                .map(String::valueOf)  // convert each integer to string
                .collect(Collectors.joining(","));  // join them with comma
        return "CALL " + this.label + " " + baseID + "(" + argsString + ")";
    }
}

package interpreter.virtualmachine;

import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.CallCode;
import interpreter.bytecodes.FalseBranchCode;
import interpreter.bytecodes.GotoCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program {

    private List<ByteCode> program;
    private HashMap<String, Integer> labelAddresses;

    /**
     * Instantiates a program object using an
     * ArrayList
     */
    public Program() {
        this.program = new ArrayList<>();
        this.labelAddresses = new HashMap<>();
    }

    /**
     * Gets the size of the current program.
     *
     * @return size of program
     */
    public int getSize() {
        return program.size();
    }

    /**
     * Grabs an instance of a bytecode at an index.
     *
     * @param programCounter index of bytecode to get.
     * @return a bytecode.
     */
    public ByteCode getCode(int programCounter) {
        return program.get(programCounter);
    }

    /**
     * Adds a bytecode instance to the Program List.
     *
     * @param c bytecode to be added
     */
    public void addByteCode(ByteCode c) {
        program.add(c);
    }

    /**
     * Makes multiple passes through the program ArrayList resolving
     * addrss for Goto,Call and FalseBranch bytecodes. These bytecodes
     * can only jump to Label codes that have a matching label value.
     * HINT: make note of what type of data-structure ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CAHNGED *****
     */
    public void resolveAddress() {
        for (ByteCode code : program) {
            if (code instanceof GotoCode gotoCode) {
                String label = gotoCode.getLabel();
                int address = getLabelAddress(label);
                gotoCode.setTargetAddress(address);
            } else if (code instanceof CallCode callCode) {
                String label = callCode.getLabel();
                int address = getLabelAddress(label);
                callCode.setAddress(address);
            } else if (code instanceof FalseBranchCode falseBranchCode) {
                String label = falseBranchCode.getLabel();
                int address = getLabelAddress(label);
                falseBranchCode.setTargetAddress(address);
            }
        }
    }

    public int getLabelAddress(String label) {
        return this.labelAddresses.get(label);
    }
}
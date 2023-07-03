package interpreter.virtualmachine;

import interpreter.bytecodes.ByteCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class VirtualMachine {

    private final RunTimeStack runTimeStack;
    private final Stack<Integer> returnAddress;
    private final Program program;
    private int programCounter;
    private boolean isRunning;
    private boolean isDumping;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
        this.isRunning = false;
    }

    public void executeProgram() {
        programCounter = 0;
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            programCounter++;
        }
    }

    // To push a value onto the runtime stack.
    public void pushRunStack(int value) {
        this.runTimeStack.push(value);
    }

    // To pop a value from the runtime stack.
    public int popRunStack() {
        return this.runTimeStack.pop();
    }

    // To store the return address when a function is called.
    public void pushReturnAddress(int addr) {
        returnAddress.push(addr);
    }

    // To restore the program counter when a function returns.
    public int popReturnAddress() {
        if (!returnAddress.isEmpty()) {
            programCounter = returnAddress.pop();
        } else {
            isRunning = false;
        }
        return returnAddress.pop();
    }

    public Program getProgram() {
        return this.program;
    }

    // To get and set the program counter.
    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int newProgramCounter) {
        this.programCounter = newProgramCounter;
    }

    // To stop the program execution from a byte code.
    public void halt() {
        this.isRunning = false;
    }

    // To peek the value at the top of the runtime stack without removing it.
    public int peekRunStack() {
        return runTimeStack.peek();
    }

    // To start a new frame at a given offset from the top of the runtime stack.
    public void newFrameAt(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    // To pop the current frame off the runtime stack.
    public void popFrame() {
        runTimeStack.popFrame();
    }

    // To store into a variable from the runtime stack.
    public int store(int offset) {
        return runTimeStack.store(offset);
    }

    // To load a variable onto the runtime stack.
    public int load(int offset) {
        return this.runTimeStack.load(offset);
    }

    public ArrayList<Integer> getArgs(int numArgs) {
        ArrayList<Integer> args = new ArrayList<>();
        for (int i = 0; i < numArgs; i++) {
            args.add(this.runTimeStack.pop());
        }
        return args;
    }

    public boolean runTimeStackIsEmpty() {
        return this.runTimeStack.isEmpty();
    }

    public void emptyCurrentFrame() {
        int frameStartIndex = this.runTimeStack.getFramePointerPeek();
        while (this.runTimeStack.getSize() > frameStartIndex) {
            this.runTimeStack.pop();
        }
    }

    public List<Integer> getFrameArguments() {
        return this.runTimeStack.getFrameArguments();
    }

    public void setDumping(boolean dumping) {
        this.isDumping = dumping;
    }
}

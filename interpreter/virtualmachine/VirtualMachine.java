package interpreter.virtualmachine;

import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.NotDumpable;

import java.util.List;
import java.util.Stack;

public class VirtualMachine {

    private final RunTimeStack runTimeStack;
    private final Stack<Integer> returnAddress;
    private final Program program;
    private int programCounter;
    private boolean isRunning;
    private boolean isDumping = false;

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
            if (this.isDumping && !(code instanceof NotDumpable)) {
                System.out.println(code);
                System.out.println(this.runTimeStack.dump());
            }
            programCounter++;
        }
    }

    public void pushRunStack(int value) {
        this.runTimeStack.push(value);
    }

    public int popRunStack() {
        return this.runTimeStack.pop();
    }

    public void pushReturnAddress(int addr) {
        returnAddress.push(addr);
    }

    public int popReturnAddress() {
        if (!returnAddress.isEmpty()) {
            programCounter = returnAddress.pop();
        } else {
            isRunning = false;
        }
        return programCounter;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int newProgramCounter) {
        this.programCounter = newProgramCounter;
    }

    public void halt() {
        this.isRunning = false;
    }

    public int peekRunStack() {
        return runTimeStack.peek();
    }

    public void newFrameAt(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    public void popFrame() {
        runTimeStack.popFrame();
    }

    public int store(int offset) {
        return runTimeStack.store(offset);
    }

    public int load(int offset) {
        return this.runTimeStack.load(offset);
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

    public int getFrameSize() {
        return this.runTimeStack.getSize()-runTimeStack.getFramePointerPeek()-1;
    }
}

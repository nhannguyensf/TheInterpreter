package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private List<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial frame pointer value, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public String dump() {
        StringBuilder output = new StringBuilder();
        // Use iterator to iterate over framePointer stack and runTimeStack list
        for (int i = 1; i < framePointer.size(); i++) {
            output.append("[");
            int start;
            int end;

            start = framePointer.get(i);
            if (i == framePointer.size() - 1) {
                end = runTimeStack.size();
            } else {
                end = framePointer.get(i + 1);
            }

            for (int j = start; j < end; j++) {
                output.append(runTimeStack.get(j));
                if (j != end - 1) {
                    output.append(",");
                }
            }
            output.append("] ");
        }
        return output.toString();
    }

    public int push(int value) {
        this.runTimeStack.add(value);
        return value;
    }

    public int getFramePointerPeek() {
        return framePointer.peek();
    }

    public int peek() {
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }

    public int pop() {
        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
    }

    public int store(int offsetFromFramePointer) {
        int top = pop();
        int currentFrameStart = framePointer.peek();
        this.runTimeStack.set(currentFrameStart + offsetFromFramePointer, top);
        return top;
    }

    public int load(int offsetFromFramePointer) {
        int currentFrameStart = framePointer.peek();
        int loadedValue = this.runTimeStack.get(currentFrameStart + offsetFromFramePointer);
        push(loadedValue);
        return loadedValue;
    }

    public void newFrameAt(int offsetFromTopOfRunStack) {
        int newFramePointer = this.runTimeStack.size() - offsetFromTopOfRunStack;
        framePointer.push(newFramePointer);
    }

    public void popFrame() {
        int topFrame = pop();
        int lastFrameStart = framePointer.pop();
        for (int i = this.runTimeStack.size() - 1; i >= lastFrameStart; i--) {
            this.runTimeStack.remove(i);
        }
        push(topFrame);
    }

    public boolean isEmpty() {
        return this.runTimeStack.isEmpty();
    }

    public int getSize() {
        return this.runTimeStack.size();
    }

    public List<Integer> getFrameArguments() {
        if (!framePointer.empty()) {
            int startFramePointer = framePointer.peek();
            return new ArrayList<>(runTimeStack.subList(startFramePointer, runTimeStack.size()));
        }
        return new ArrayList<>();
    }
//    public static void main(String[] args) {
//        RunTimeStack rts = new RunTimeStack();
//        rts.push(1);
//        rts.push(2);
//        rts.push(3);
//        rts.push(4);
//        rts.push(5);
//        rts.push(6);
//        rts.push(7);
//        rts.push(8);
//        rts.framePointer.push(0);
//        rts.framePointer.push(3);
//        rts.framePointer.push(6);
//        System.out.println(rts.peek());
//        System.out.print(rts.dump());
//    }
}

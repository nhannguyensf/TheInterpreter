package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {
    private static final int STACK_OVERFLOW_THRESHOLD = 1000;
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
        for (int i = 0; i < framePointer.size(); i++) {
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
                    output.append(", ");
                }
            }
            output.append("] ");
        }
        return output.toString().trim();
    }

    public void push(int value) {
        if (this.runTimeStack.size() >= STACK_OVERFLOW_THRESHOLD) {
            throw new RuntimeException("Stack overflow. The entered number maybe too big");
        }
        this.runTimeStack.add(value);
    }

    public int getFramePointerPeek() {
        return framePointer.peek();
    }

    public int peek() {
        if (this.runTimeStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }

    public int pop() {
        if (this.runTimeStack.isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
    }

    public int store(int offsetFromFramePointer) {
        if (this.framePointer.isEmpty()) {
            throw new RuntimeException("Frame pointer stack is empty");
        }
        int top = pop();
        int currentFrameStart = framePointer.peek();
        this.runTimeStack.set(currentFrameStart + offsetFromFramePointer, top);
        return top;
    }

    public int load(int offsetFromFramePointer) {
        if (this.framePointer.isEmpty()) {
            throw new RuntimeException("Frame pointer stack is empty");
        }
        int currentFrameStart = framePointer.peek();
        int loadedValue = this.runTimeStack.get(currentFrameStart + offsetFromFramePointer);
        this.push(loadedValue);
        return loadedValue;
    }

    public void newFrameAt(int offsetFromTopOfRunStack) {
        int newFramePointer = this.runTimeStack.size() - offsetFromTopOfRunStack;
        framePointer.push(newFramePointer);
    }

    public void popFrame() {
        if (this.framePointer.isEmpty()) {
            throw new RuntimeException("Frame pointer stack is empty");
        }
        int frameStartIndex = framePointer.pop();
        if (runTimeStack.size() > frameStartIndex) {
            runTimeStack.subList(frameStartIndex, runTimeStack.size()).clear();
        }
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
}

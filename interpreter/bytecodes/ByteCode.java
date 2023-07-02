package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public interface ByteCode {
    static ByteCode getNewInstance(String type, String[] args) {
        return switch (type) {
            case "HALT"-> new HaltCode(args);
            case "POP" -> new PopCode(args);
            case "FALSEBRANCH" -> new FalseBranchCode(args);
            case "GOTO" -> new GotoCode(args);
            case "STORE" -> new StoreCode(args);
            case "LOAD" -> new LoadCode(args);
            case "LIT" -> new LitCode(args);
            case "ARGS" -> new ArgsCode(args);
            case "CALL" -> new CallCode(args);
            case "RETURN" -> new ReturnCode(args);
            case "BOP" -> new BopCode(args);
            case "READ" -> new ReadCode(args);
            case "WRITE" -> new WriteCode(args);
            case "LABEL" -> new LabelCode(args);
            case "DUMP" -> new DumpCode(args);
            default -> throw new IllegalArgumentException();
        };
    }

//    public abstract void init(ArrayList<String> str);
//
    public abstract void execute(VirtualMachine vm);
//
//    @Override
//    public  abstract String toString();

}
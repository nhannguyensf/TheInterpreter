package interpreter;

import interpreter.loaders.ByteCodeLoader;
import interpreter.loaders.InvalidProgramException;
import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;

/**
 * <pre>
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the ByteCodes from file
 *     3. Run the virtual machine
 * 
 *     THIS FILE CANNOT BE MODIFIED. DO NOT
 *     LET ANY EXCEPTIONS REACH THE
 * 
 * </pre>
 */
public class Interpreter {

    private ByteCodeLoader byteCodeLoader;

    public Interpreter(String codeFile) {
        byteCodeLoader = new ByteCodeLoader(codeFile);
    }

    void run() {
        Program program = null;
        try{
            program = byteCodeLoader.loadCodes();
        } catch(InvalidProgramException ex){
            System.out.println(ex);
            ex.printStackTrace();
            System.exit(-2);
        }
        program.resolveAddress();
        VirtualMachine virtualMachine = new VirtualMachine(program);
        virtualMachine.executeProgram();
        
    }

    public static void main(String args[]) {

        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        }
        (new Interpreter(args[0])).run();
    }
}
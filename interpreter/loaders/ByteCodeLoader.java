package interpreter.loaders;

import interpreter.virtualmachine.Program;
import interpreter.bytecodes.ByteCode;

import java.io.*;

public final class ByteCodeLoader {
    private String codSourceFileName;

    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     *
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName) {
        this.codSourceFileName = fileName;
    }

    /**
     * Loads a program from a .cod file.
     *
     * @return a constructed Program Object.
     * @throws InvalidProgramException thrown when
     *                                 loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {
        Program program = new Program();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(codSourceFileName));
            String line;
            String[] items;
            String byteCodeName;
            ByteCode bc;

            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                items = line.split("\\s+");
//                System.out.println(Arrays.toString(items));
                byteCodeName = items[0];
                bc = ByteCode.getNewInstance(byteCodeName, items);
                program.addByteCode(bc);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return program;
    }
}

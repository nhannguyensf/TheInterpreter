package interpreter.bytecodes;

public abstract class JumpCode implements ByteCode {
    private String label;
    private int targetAddress;

    public JumpCode() {
    }

    public String getLabel() {
        return label;
    }

    public void setTargetAddress(int address) {
        this.targetAddress = address;
    }
}

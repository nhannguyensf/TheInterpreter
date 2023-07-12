package interpreter.bytecodes;

public abstract class JumpCode implements ByteCode {
    protected String label;
    protected int targetAddress;

    public JumpCode() {
    }

    public String getLabel() {
        return label;
    }

    public void setTargetAddress(int address) {
        this.targetAddress = address;
    }
}

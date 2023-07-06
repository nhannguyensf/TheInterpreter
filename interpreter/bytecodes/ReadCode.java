package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.Scanner;

public class ReadCode implements ByteCode {
    public ReadCode(String[] args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter an integer: ");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                validInput = true;
            } else {
                scanner.nextLine(); // Clear the invalid input
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        vm.pushRunStack(value);
    }

    @Override
    public String toString() {
        return "READ";
    }
}

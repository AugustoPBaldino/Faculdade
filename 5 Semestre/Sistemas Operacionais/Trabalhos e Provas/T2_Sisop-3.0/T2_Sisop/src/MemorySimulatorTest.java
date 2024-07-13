import java.util.Random;

public class MemorySimulatorTest {

    public static void main(String[] args) {
        runTest(16, 15, 12, 5); // Caso 1
        runTest(20, 18, 10, 5); // Caso 2
        runTest(18, 16, 14, 5); // Caso 3
        runTest(22, 20, 12, 5); // Caso 4
    }

    private static void runTest(int virtualMemorySize, int physicalMemorySize, int pageSize, int numAddresses) {
        System.out.println("Testing with Virtual Memory Size: " + virtualMemorySize + " bits, Physical Memory Size: "
                + physicalMemorySize + " bits, Page Size: " + pageSize + " bits, Number of Addresses: " + numAddresses);

        MemorySimulator simulator = new MemorySimulator(virtualMemorySize, physicalMemorySize, pageSize);

        Random random = new Random();
        int[] virtualAddresses = new int[numAddresses];

        System.out.println("Generated virtual addresses:");

        for (int i = 0; i < numAddresses; i++) {
            // Modify here to generate numbers up to 100000 (exclusive)
            virtualAddresses[i] = random.nextInt(10000);
            System.out.println(virtualAddresses[i]);
        }

        System.out.println("\nTranslating addresses...");
        boolean allAddressesValid = true;
        for (int address : virtualAddresses) {
            try {
                int physicalAddress = simulator.translate(address);
                if (physicalAddress < 0 || physicalAddress >= (1 << physicalMemorySize)) {
                    System.out.println("Error: Physical address " + physicalAddress + " is out of bounds!");
                    allAddressesValid = false;
                }
                System.out.println("Virtual address " + address + " -> Physical address " + physicalAddress);
            } catch (Exception e) {
                System.out.println("Error translating address " + address + ": " + e.getMessage());
                allAddressesValid = false;
            }
        }

        simulator.printState();

        if (allAddressesValid) {
            System.out.println("Test completed successfully: All addresses are valid.\n");
        } else {
            System.out.println("Test failed: Some addresses are invalid.\n");
        }
    }
}

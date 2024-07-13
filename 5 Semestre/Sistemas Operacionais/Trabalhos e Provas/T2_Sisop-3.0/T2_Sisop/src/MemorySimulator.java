import java.util.Random;
import java.util.Scanner;

public class MemorySimulator {
    private int virtualMemorySize;
    private int physicalMemorySize;
    private int pageSize;
    private int numVirtualPages;
    private int numPhysicalPages;
    private PageTable pageTable;
    private PhysicalMemory physicalMemory;

    public MemorySimulator(int virtualMemorySize, int physicalMemorySize, int pageSize) {
        this.virtualMemorySize = virtualMemorySize; // 16
        this.physicalMemorySize = physicalMemorySize; // 15
        this.pageSize = pageSize; // 12
        this.numVirtualPages = (int) Math.pow(2, virtualMemorySize - pageSize); // 2^(16-12) = 2^4 = 16
        this.numPhysicalPages = (int)Math.pow(2, physicalMemorySize - pageSize); // 2^(15-12) = 2^3 = 8
        this.pageTable = new PageTable(numVirtualPages);
        this.physicalMemory = new PhysicalMemory(numPhysicalPages);
    }

    public int translate(int virtualAddress) {
        int pageNumber = virtualAddress / (1 << pageSize);
        int offset = virtualAddress % (1 << pageSize);
        int frameNumber = pageTable.getFrame(pageNumber);

        if (frameNumber == -1) { // Page fault
            frameNumber = physicalMemory.allocateFrame();
            pageTable.setFrame(pageNumber, frameNumber);
        }

        return (frameNumber * (1 << pageSize)) + offset;
    }

    public void printState() {
        pageTable.printState();
        physicalMemory.printState();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter virtual memory size (in bits): ");
        int virtualMemorySize = scanner.nextInt();
        System.out.print("Enter physical memory size (in bits): ");
        int physicalMemorySize = scanner.nextInt();
        System.out.print("Enter page size (in bits): ");
        int pageSize = scanner.nextInt();

        MemorySimulator simulator = new MemorySimulator(virtualMemorySize, physicalMemorySize, pageSize);

        Random random = new Random();
        System.out.println("Enter the number of virtual addresses to generate: ");
        int numAddresses = scanner.nextInt();
        int[] virtualAddresses = new int[numAddresses];

        System.out.println("Generated virtual addresses:");

        for (int i = 0; i < numAddresses; i++) {
            virtualAddresses[i] = random.nextInt(1 << virtualMemorySize);
            System.out.println(virtualAddresses[i]);
        }

        System.out.println("\nTranslating addresses...");
        for (int address : virtualAddresses) {
            int physicalAddress = simulator.translate(address);
            System.out.println("Virtual address " + address + " -> Physical address " + physicalAddress);
        }

        simulator.printState();
    }


}

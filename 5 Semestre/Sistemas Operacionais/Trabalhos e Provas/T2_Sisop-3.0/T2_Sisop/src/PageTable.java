import java.util.Arrays;

public class PageTable {
    private int[] pageTable;

    public PageTable(int numPages) {
        this.pageTable = new int[numPages];
        Arrays.fill(pageTable, -1); // Initialize page table with -1
    }

    public int getFrame(int pageNumber) {
        return pageTable[pageNumber];
    }

    public void setFrame(int pageNumber, int frameNumber) {
        pageTable[pageNumber] = frameNumber;
    }

    public void printState() {
        System.out.println("Page Table: " + Arrays.toString(pageTable));
    }
}


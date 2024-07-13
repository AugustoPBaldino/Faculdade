import java.util.Arrays;

public class PhysicalMemory {
    private int[] frames;
    private int nextFreeFrame = 0;

    public PhysicalMemory(int numFrames) {
        this.frames = new int[numFrames];
    }

    public int allocateFrame() {
        if (nextFreeFrame >= frames.length) {
            throw new RuntimeException("Physical memory is full");
        }
        frames[nextFreeFrame] = 1; // Mark frame as used
        return nextFreeFrame++;
    }

    public void printState() {
        System.out.println("Physical Memory: " + Arrays.toString(frames));
    }
}


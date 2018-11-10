import java.util.ArrayList;

public class Buffer {
    private ArrayList<int[]> bufferArray;
    private int getIndex;
    private int addIndex;

    public Buffer() {
        this.bufferArray = new ArrayList<>();
        this.getIndex = 0;
    }

    public void add(int[] array) {
        this.bufferArray.add(addIndex, array);
    }

    public int[] get() {
        int[] returnArray = this.bufferArray.get(getIndex);
        this.bufferArray.remove(getIndex);
        getIndex++;

        return returnArray;
    }

    public int getBufferSize() {
        return this.bufferArray.size();
    }

}

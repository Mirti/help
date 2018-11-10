public class Producer extends Thread {

    private int id;
    private Buffer buffer;

    public Producer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.getBufferSize() >= 10) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int[] array = generateArray();
                this.buffer.add(array);
                printArray(array);
                buffer.notifyAll();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private int[] generateArray() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = (int) (Math.random() * 100 + 1);
        }

        return array;
    }

    private void printArray(int[] array) {
        System.out.print("Producer " + this.id + ": ");
        for (int element : array) {
            System.out.print(" " + element);
        }
        System.out.print("   Buffer size: " + this.buffer.getBufferSize());
        System.out.println();
    }
}

public class Consumer extends Thread {

    private int id;
    private Buffer buffer;

    public Consumer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.getBufferSize() == 0) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int[] array = buffer.get();
                printArray(bubbleSort(array));
                buffer.notifyAll();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private int[] bubbleSort(int[] array) {
        int n = array.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-1; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }

    private void printArray(int[] array) {
        System.out.print("Consumer " + this.id + ": ");
        for (int element : array) {
            System.out.print(" " + element);
        }
        System.out.print("   Buffer size: " + this.buffer.getBufferSize());

        System.out.println();
    }
}

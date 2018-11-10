public class Main {

    public static void main(String [] args) {
        Buffer buffer = new Buffer();

        Producer producer1 = new Producer(1, buffer);
        Producer producer2 = new Producer(2, buffer);

        Consumer consumer1 = new Consumer(1, buffer);
        Consumer consumer2 = new Consumer(2, buffer);
        Consumer consumer3 = new Consumer(3, buffer);

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

    }
}

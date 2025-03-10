public class Main {

    public static void main(String[] args) throws InterruptedException {

        ZivaEntita ze = new ZivaEntita();

        Thread t1 = new Thread(ze);
        t1.start();

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                System.out.println("ja taky ziju");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t2.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();

        System.out.println("Konci hlavni vlakno");

    }

}

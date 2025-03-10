public class ZivaEntita implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("ziju");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("ja neumru !!!");
            }
        }

    }
}

import java.sql.SQLOutput;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CreatingStartingThreads {
        public static Thread[] threads = new Thread[7];



    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(2);
        CountDownLatch countDownLatch3 = new CountDownLatch(2);
        Work unu =  new Work(countDownLatch1,"1");
        unu.reset();
        new Work(countDownLatch1,"2");
        new Work(countDownLatch2,"3");
        new Work(countDownLatch2,"4");
        countDownLatch1.await();
        System.out.println("Trecut de 1-2");
        new Work(countDownLatch3,"5");
        countDownLatch2.await();
        System.out.println("Trecut de 3-4");
        new Work(countDownLatch3,"6");
        countDownLatch3.await();
        System.out.println("Trecut de 5-6");
        new Work(countDownLatch3,"7");

    }

}
class Work extends Thread{
    CountDownLatch countDownLatch;
     String name ;

    public Work(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
        start();
    }


    @Override
    public void run() {
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.currentThread().setName(name);
        System.out.println(Thread.currentThread().getName()+" Sa finisat");
        countDownLatch.countDown();
    }

    public CountDownLatch reset(){
        countDownLatch = new CountDownLatch(1);
        return countDownLatch;
    }


}







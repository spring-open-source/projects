package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author hardiku
 */
public class CoucurrencyTest
{

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    private static final int TOTAL_THREADS = 1000;

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException
    {
        Runnable r = new ComputeThread();

        long startTime = System.currentTimeMillis();

        try
        {
            for(int i = 0; i < TOTAL_THREADS; i++)
            {
                executorService.submit(r);
            }

            System.out.println("attempt to shutdown executor");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch(InterruptedException e)
        {
            System.err.println("tasks interrupted");
        }
        finally
        {
            if(!executorService.isTerminated())
            {
                System.err.println("cancel non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("shutdown finished");
        }

        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
    }

    /**
     *
     */
    private static class ComputeThread implements Runnable
    {

        private final int sumUpTo = 100000;

        @Override
        public void run()
        {
            int sum = 0;

            for(int i = sumUpTo; i > 0; i--)
            {
                sum = sum + i;
            }
            System.out.println("Final Sum by Thread:" + Thread.currentThread().getName() + " is " + sum);
        }

    }
}

package by.epam.javatraining.matrix.service;

import by.epam.javatraining.matrix.util.MatrixResultWriter;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceHelperThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ServiceHelperThread.class);
    private static Lock lock = new ReentrantLock();
    private static MatrixService service = MatrixService.getInstance();
    private static Semaphore semaphore = new Semaphore(service.getMatrix().getN());
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(service.getMatrix().getN(), new MatrixResultWriter());
    private static List<Integer> listOfSumResult = new ArrayList<>();
    private static int idCounter = 1;


    public ServiceHelperThread() {
        super(String.valueOf(idCounter++));
    }

    public static List<Integer> getListOfSumResult() {
        return listOfSumResult;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted in the semaphore acquire method!", e);
            Thread.currentThread().interrupt();
        }

        lock.lock();
        int diagonalPlace = service.findPlaceToInsertThreadNameToMatrixDiagonal();
        service.insertThreadNameNumberIntoDiagonal(diagonalPlace);
        service.insertValueIntoColumnOrRowOfMatrix(diagonalPlace);
        int sum = service.findSumOfColumnAndRowElements(diagonalPlace);
        listOfSumResult.add(sum);
        lock.unlock();

        try {
            cyclicBarrier.await();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted CyclingBarrier await method!", e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            LOGGER.error("Barrier in broken state!", e);
        }

        semaphore.release();
    }
}

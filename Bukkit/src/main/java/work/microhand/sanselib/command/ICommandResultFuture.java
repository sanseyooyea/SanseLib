package work.microhand.sanselib.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author SanseYooyea
 */
public interface ICommandResultFuture<T> {
    /**
     * 阻塞等待命令结果。
     *
     * @return 命令结果
     * @throws InterruptedException 如果线程在等待结果时被中断，则抛出此异常
     * @throws ExecutionException 如果执行过程中出现异常，则抛出此异常
     */
    T get() throws InterruptedException, ExecutionException;

    /**
     * 阻塞等待命令结果。
     *
     * @param timeout 等待时间
     * @param unit 时间单位
     * @return 命令结果
     * @throws InterruptedException 如果线程在等待结果时被中断，则抛出此异常
     * @throws ExecutionException 如果执行过程中出现异常，则抛出此异常
     * @throws TimeoutException 如果超时，则抛出此异常
     */
    T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    /**
     * 取消执行命令。
     *
     * @param mayInterruptIfRunning 是否可以中断正在执行的命令
     * @return 如果取消成功，则返回 true；否则返回 false。
     */
    boolean cancel(boolean mayInterruptIfRunning);

    /**
     * 返回命令是否已经完成。
     *
     * @return 如果已完成，则返回 true；否则返回 false。
     */
    boolean isDone();

    /**
     * 返回命令是否已经被取消。
     *
     * @return 如果已取消，则返回 true；否则返回 false。
     */
    boolean isCancelled();
}

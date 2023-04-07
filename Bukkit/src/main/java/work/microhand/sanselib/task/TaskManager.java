package work.microhand.sanselib.task;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author SanseYooyea
 */
public enum TaskManager {
    /**
     * 单例
     */
    INSTANCE;

    @SuppressWarnings("AlibabaThreadPoolCreation")
    private final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);

    private final Map<String, ScheduledFuture<?>> FUTURES = new HashMap<>();

    /**
     * 获取所有任务名称
     * @return 所有任务名称的列表
     */
    public synchronized List<String> getAllTaskNames() {
        return new ArrayList<>(FUTURES.keySet());
    }

    public synchronized boolean addTask(String taskName, ITask task) {
        return addTask(taskName, task, 0, TimeUnit.SECONDS);
    }

    /**
     * 添加一个定时任务
     *
     * @param taskName 任务名称
     * @param task 任务执行体
     * @param delay 延迟时间
     * @param unit 时间单位
     * @return true表示添加成功，false表示任务名称已存在
     */
    public synchronized boolean addTask(String taskName, ITask task, long delay, TimeUnit unit) {
        return addTask(taskName, task, delay, 0, unit);
    }

    /**
     * 添加一个定时任务
     *
     * @param taskName 任务名称
     * @param task 任务执行体
     * @param delay 延迟时间
     * @param period 执行周期
     * @param unit 时间单位
     * @return true表示添加成功，false表示任务名称已存在
     */
    public synchronized boolean addTask(String taskName, ITask task, long delay, long period, TimeUnit unit) {
        if (FUTURES.containsKey(taskName)) {
            return false;
        }

        ScheduledFuture<?> future;
        if (period == 0) {
            future = EXECUTOR_SERVICE.schedule(task, delay, unit);
        } else {
            future = EXECUTOR_SERVICE.scheduleAtFixedRate(task, delay, period, unit);
        }
        FUTURES.put(taskName, future);
        return true;
    }

    /**
     * 添加一个定时任务
     *
     * @param taskName 任务名称
     * @param task 任务执行体
     * @param initialDelay 首次延迟时间
     * @param period 执行周期
     * @param unit 时间单位
     * @return true表示添加成功，false表示任务名称已存在
     */
    public synchronized boolean addTaskWithInitialDelay(String taskName, ITask task, long initialDelay, long period, TimeUnit unit) {
        if (FUTURES.containsKey(taskName)) {
            return false;
        }
        ScheduledFuture<?> future = EXECUTOR_SERVICE.scheduleWithFixedDelay(task, initialDelay, period, unit);
        FUTURES.put(taskName, future);
        return true;
    }

    /**
     * 取消一个定时任务
     * @param taskName 任务名称
     * @return true表示取消成功，false表示任务名称不存在
     */
    public synchronized boolean cancelTask(String taskName) {
        ScheduledFuture<?> future = FUTURES.get(taskName);
        if (future != null) {
            future.cancel(false);
            FUTURES.remove(taskName);
            return true;
        }
        return false;
    }

    /**
     * 停止所有任务
     */
    public synchronized void stopAllTasks() {
        FUTURES.values().forEach(future -> future.cancel(false));
        FUTURES.clear();
    }

    /**
     * 获取某个任务的执行状态
     * @param taskName 任务名称
     * @return 任务的执行状态，不存在返回null
     */
    public synchronized TaskStatus getTaskStatus(String taskName) {
        ScheduledFuture<?> future = FUTURES.get(taskName);
        if (future == null) {
            return null;
        }
        if (future.isCancelled()) {
            return TaskStatus.CANCELLED;
        } else if (future.isDone()) {
            return TaskStatus.DONE;
        } else {
            return TaskStatus.RUNNING;
        }
    }

    /**
     * 获取某个任务的下一次执行时间
     * @param taskName 任务名称
     * @param unit 时间单位
     * @return 任务的下一次执行时间，不存在返回null
     */
    public synchronized Date getNextExecutionTime(String taskName, TimeUnit unit) {
        ScheduledFuture<?> future = FUTURES.get(taskName);
        if (future == null) {
            return null;
        }
        long delay = future.getDelay(unit);
        return new Date(System.currentTimeMillis() + unit.toMillis(delay));
    }

    /**
     * 获取所有任务的执行状态
     * @return 所有任务名称和状态的映射表
     */
    public synchronized Map<String, TaskStatus> getAllTaskStatus() {
        Map<String, TaskStatus> statusMap = new HashMap<>();
        for (String taskName : FUTURES.keySet()) {
            statusMap.put(taskName, getTaskStatus(taskName));
        }
        return statusMap;
    }

    /**
     * 查询指定任务是否已存在
     *
     * @param taskName 任务名称
     * @return true表示任务已存在，false表示任务不存在
     */
    public synchronized boolean hasTask(String taskName) {
        return FUTURES.containsKey(taskName);
    }
}

package work.microhand.sanselib.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author SanseYooyea
 */
public interface ICommandContext {
    /**
     * 获取该命令的发送者，如果是控制台则返回 null
     *
     * @return 发送者
     */
    @Nullable
    CommandSender sender();

    /**
     * 获取该命令的参数列表
     * @return 参数列表
     */
    @NotNull
    List<String> args();

    /**
     * 获取该命令的别名
     * @return 命令别名
     */
    @NotNull
    String alias();

    /**
     * 获取父命令的 ISubCommand 实例
     * @return 父命令的 ISubCommand 实例
     */
    @NotNull
    ISubCommand parent();

    /**
     * 获取子命令的 ISubCommand 实例
     * @return 子命令的 ISubCommand 实例
     */
    @Nullable
    ISubCommand subCommand();

    /**
     * 获取该命令的 CommandResultFuture
     * @return 该命令的 CommandResultFuture
     */
    @NotNull
    ICommandResultFuture<?> resultFuture();

    /**
     * 向该命令的发送者发送一条信息
     * @param message 信息
     */
    void sendMessage(@NotNull String message);

    /**
     * 向该命令的发送者发送多条信息
     * @param messages 信息列表
     */
    void sendMessage(@NotNull String... messages);

    /**
     * 向该命令的发送者发送一条错误信息
     * @param message 错误信息
     */
    void sendErrorMessage(@NotNull String message);

    /**
     * 向该命令的发送者发送多条错误信息
     * @param messages 错误信息列表
     */
    void sendErrorMessage(@NotNull String... messages);
}

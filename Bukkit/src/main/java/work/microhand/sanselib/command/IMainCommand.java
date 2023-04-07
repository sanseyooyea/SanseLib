package work.microhand.sanselib.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import work.microhand.sanselib.command.impl.SubCommand;

import java.util.List;

/**
 * @author SanseYooyea
 */
/**
 * 主命令接口
 */
public interface IMainCommand {
    /**
     * 设置命令别名
     *
     * @param aliases 别名
     * @return this
     */
    IMainCommand aliases(String... aliases);

    /**
     * 添加子命令
     *
     * @param subCommand 子命令实例
     * @return this
     */
    IMainCommand subCommand(SubCommand subCommand);

    /**
     * 添加子命令
     *
     * @param name 子命令名称
     * @return 新创建的子命令实例
     */
    SubCommand subCommand(String name);

    /**
     * 注册主命令
     *
     * @param plugin 插件实例
     */
    void register(JavaPlugin plugin);
}

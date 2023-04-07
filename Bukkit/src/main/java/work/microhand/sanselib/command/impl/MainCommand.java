package work.microhand.sanselib.command.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import work.microhand.sanselib.command.IMainCommand;
import work.microhand.sanselib.task.TaskManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author SanseYooyea
 */
public class MainCommand implements IMainCommand {
    private final String name;
    private final List<String> aliases = new ArrayList<>();
    private final List<SubCommand> subCommands = new ArrayList<>();
    private TabExecutor executor;

    public MainCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 设置命令别名
     *
     * @param aliases 别名
     * @return this
     */
    public MainCommand aliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public MainCommand subCommand(SubCommand subCommand) {
        this.subCommands.add(subCommand);
        return this;
    }

    public SubCommand subCommand(String name) {
        SubCommand subCommand = new SubCommand(this, name);
        subCommands.add(subCommand);
        return subCommand;
    }

    public void build() {
        this.executor = new TabExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                command.setAliases(aliases);

                if (args.length == 0) {
                    // TODO 待处理
                }

                String flag = args[0].toLowerCase();
                subCommands
                        .stream()
                        .filter(subCommand -> subCommand.getName().equalsIgnoreCase(flag) || subCommand.getAliases().contains(flag))
                        .findFirst()
                        .ifPresent(subCommand -> {
                            TaskManager.INSTANCE.addTask(subCommand.getName(), subCommand.getExecutor());
                        });
                return true;
            }

            @Nullable
            @Override
            public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                return null;
            }
        };
    }

    public void register(JavaPlugin plugin) {
        Objects.requireNonNull(plugin.getCommand(name)).setExecutor(executor);
        Objects.requireNonNull(plugin.getCommand(name)).setTabCompleter(executor);
    }
}

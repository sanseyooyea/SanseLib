package work.microhand.sanselib.command.impl;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import work.microhand.sanselib.command.ICommandResultFuture;
import work.microhand.sanselib.command.ICommandContext;
import work.microhand.sanselib.command.ISubCommand;

import java.util.List;

/**
 * @author SanseYooyea
 */
public record CommandContext(CommandSender sender, List<String> args, String alias, ISubCommand parent,
                             ISubCommand subCommand, ICommandResultFuture<?> resultFuture) implements ICommandContext {

    @Override
    @Nullable
    public CommandSender sender() {
        return sender;
    }

    @Override
    @NotNull
    public List<String> args() {
        return args;
    }

    @Override
    @NotNull
    public String alias() {
        return alias;
    }

    @Override
    @NotNull
    public ISubCommand parent() {
        return parent;
    }

    @Override
    @Nullable
    public ISubCommand subCommand() {
        return subCommand;
    }

    @Override
    @NotNull
    public ICommandResultFuture<?> resultFuture() {
        return resultFuture;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        if (sender != null) {
            sender.sendMessage(message);
        }
    }

    @Override
    public void sendMessage(@NotNull String... messages) {
        if (sender != null) {
            sender.sendMessage(messages);
        }
    }

    @Override
    public void sendErrorMessage(@NotNull String message) {
        if (sender != null) {
            sender.sendMessage("§c| " + message);
        }
    }

    @Override
    public void sendErrorMessage(@NotNull String... messages) {
        if (sender != null) {
            for (String message : messages) {
                sender.sendMessage("§c| " + message);
            }
        }
    }
}
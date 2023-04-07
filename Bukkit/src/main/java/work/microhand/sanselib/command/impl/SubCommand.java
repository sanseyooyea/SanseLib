package work.microhand.sanselib.command.impl;

import work.microhand.sanselib.command.IParameter;
import work.microhand.sanselib.command.ISubCommand;
import work.microhand.sanselib.command.task.CommandExecuteTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class SubCommand implements ISubCommand {
    private final String name;
    private final MainCommand parent;
    private String usage;
    private String description;
    private String permission;
    /**
     * 是否允许控制台执行
     * 默认为 false
     */
    private boolean console;
    private final List<String> aliases = new ArrayList<>();
    private final List<IParameter<?>> parameters = new ArrayList<>();
    private CommandExecuteTask executor;

    public SubCommand(MainCommand parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isConsole() {
        return console;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public List<IParameter<?>> getParameters() {
        return parameters;
    }

    public CommandExecuteTask getExecutor() {
        return executor;
    }

    @Override
    public ISubCommand.Builder builder() {
        return null;
    }

    public class Builder {
        public SubCommand.Builder usage(String usage) {
            SubCommand.this.usage = usage;
            return this;
        }

        public SubCommand.Builder description(String description) {
            SubCommand.this.description = description;
            return this;
        }

        public SubCommand.Builder permission(String permission) {
            SubCommand.this.permission = permission;
            return this;
        }

        public SubCommand.Builder console(boolean console) {
            SubCommand.this.console = console;
            return this;
        }

        public SubCommand.Builder aliases(String... aliases) {
            SubCommand.this.aliases.addAll(Arrays.asList(aliases));
            return this;
        }

        public <T> Parameter<T> parameter(String name) {
            return new Parameter<>(name);
        }
        public SubCommand.Builder parameters(Parameter<?>... parameters) {
            SubCommand.this.parameters.addAll(Arrays.asList(parameters));
            return this;
        }

        public SubCommand.Builder execute(CommandExecuteTask runnable) {
            SubCommand.this.executor = runnable;
            return this;
        }

        public SubCommand build() {
            if (usage.isEmpty()) {
                StringBuilder sb = new StringBuilder("/" + parent.getName());
                sb.append(" ");
                parameters.forEach(para -> {
                    if (para.isOptional()) {
                        sb.append("[");
                        sb.append(para.getDescription());
                        sb.append("]");
                    } else {
                        if (para.getDescription() != null) {
                            sb.append("<");
                            sb.append(para.getDescription());
                            sb.append(">");
                        } else {
                            sb.append(para.getName());
                        }
                    }
                    sb.append(" ");
                });
                // 去除最后一个空格
                usage = sb.substring(0, sb.toString().length() - 1);
            }
            return SubCommand.this;
        }

        /**
         * 表示子命令完成
         * <p>
         *     若结束时 usage 为 ""，则自动按照 parameters 去生成默认 usage
         * </p>
         * @return 命令建造器
         */
        public MainCommand end() {
            return parent;
        }
    }
}

package work.microhand.sanselib.command;

import work.microhand.sanselib.command.task.CommandExecuteTask;

import java.util.List;

/**
 * A sub-command that can be executed as part of a larger command.
 *
 * @author SanseYooyea
 */
public interface ISubCommand {
    /**
     * Get the name of the sub-command.
     *
     * @return the name of the sub-command
     */
    String getName();

    /**
     * Get the usage string of the sub-command.
     *
     * @return the usage string of the sub-command
     */
    String getUsage();

    /**
     * Get the description of the sub-command.
     *
     * @return the description of the sub-command
     */
    String getDescription();

    /**
     * Get the required permission of the sub-command.
     *
     * @return the required permission of the sub-command
     */
    String getPermission();

    /**
     * Check whether this sub-command can be executed from console.
     *
     * @return {@code true} if this sub-command can be executed from console, otherwise {@code false}
     */
    boolean isConsole();

    /**
     * Get a list of aliases for the sub-command.
     *
     * @return a list of aliases for the sub-command
     */
    List<String> getAliases();

    /**
     * Get a list of parameters for the sub-command.
     *
     * @return a list of parameters for the sub-command
     */
    List<IParameter<?>> getParameters();

    /**
     * Get the executor for the sub-command.
     *
     * @return the executor for the sub-command
     */
    CommandExecuteTask getExecutor();

    /**
     * Get a new builder to build a sub-command.
     *
     * @return a new builder to build a sub-command
     */
    ISubCommand.Builder builder();

    /**
     * A builder for building sub-commands.
     */
    interface Builder {
        /**
         * Set the usage string for the sub-command.
         *
         * @param usage the usage string for the sub-command
         * @return this builder
         */
        ISubCommand.Builder usage(String usage);

        /**
         * Set the description for the sub-command.
         *
         * @param description the description for the sub-command
         * @return this builder
         */
        ISubCommand.Builder description(String description);

        /**
         * Set the required permission for the sub-command.
         *
         * @param permission the required permission for the sub-command
         * @return this builder
         */
        ISubCommand.Builder permission(String permission);

        /**
         * Set whether this sub-command can be executed from console.
         *
         * @param console {@code true} if this sub-command can be executed from console, otherwise {@code false}
         * @return this builder
         */
        ISubCommand.Builder console(boolean console);

        /**
         * Add one or more aliases for the sub-command.
         *
         * @param aliases one or more aliases for the sub-command
         * @return this builder
         */
        ISubCommand.Builder aliases(String... aliases);

        /**
         * Add one or more parameters for the sub-command.
         *
         * @param parameters one or more parameters for the sub-command
         * @return this builder
         */
        ISubCommand.Builder parameters(IParameter<?>... parameters);

        /**
         * Set the executor for the sub-command.
         *
         * @param runnable the executor for the sub-command
         * @return this builder
         */
        ISubCommand.Builder execute(CommandExecuteTask runnable);

        /**
         * Build and return the sub-command.
         *
         * @return the sub-command
         */
        ISubCommand build();
    }
}
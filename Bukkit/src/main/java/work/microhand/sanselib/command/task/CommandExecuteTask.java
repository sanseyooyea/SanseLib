package work.microhand.sanselib.command.task;

import work.microhand.sanselib.command.ICommandContext;
import work.microhand.sanselib.task.ITask;

/**
 * @author SanseYooyea
 */
public interface CommandExecuteTask extends ITask {
    ICommandContext getCommandContext();
}

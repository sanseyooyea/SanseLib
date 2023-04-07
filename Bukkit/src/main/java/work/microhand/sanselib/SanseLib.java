package work.microhand.sanselib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SanseYooyea
 */
public final class SanseLib extends JavaPlugin {

    private static SanseLib instance;

    public static SanseLib getInstance() {
        return instance;
    }

    public SanseLib() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getLogger().info("§a| §2SanseLib §a插件启动中...");
        getLogger().info("§a| 作者：§2SanseYooyea");
        getLogger().info("§a| QQ：§21187586838");

        getServer().getVersion();
    }

    @Override
    public void onDisable() {
        getLogger().info("§a| §2SanseLib §a插件启动中...");
        getLogger().info("§a| 作者：§2SanseYooyea");
        getLogger().info("§a| QQ：§21187586838");
    }
}

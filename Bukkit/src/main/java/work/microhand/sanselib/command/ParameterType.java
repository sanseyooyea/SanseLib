package work.microhand.sanselib.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 提供默认的 parser 以及 TabCompleter
 *
 * @author SanseYooyea
 */
public enum ParameterType {
    /**
     * 整数型
     */
    INTEGER(Integer::valueOf, List.of("<整数型>")),
    /**
     * 逻辑型
     */
    BOOLEAN(Boolean::valueOf, Arrays.asList(String.valueOf(true), String.valueOf(false))),
    /**
     * 小数型
     */
    DOUBLE(Double::valueOf, List.of("<小数型>")),
    /**
     * 玩家
     */
    ONLINE_PLAYER(Bukkit::getPlayer, Bukkit.getOnlinePlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList()));

    private final Function<String, ?> parser;
    private final List<String> tabCompleterHints;

    ParameterType(Function<String, ?> parser, List<String> tabCompleterHint) {
        this.parser = parser;
        this.tabCompleterHints = tabCompleterHint;
    }

    public <T> Function<String, T> getParser() {
        return (Function<String, T>) parser;
    }

    public List<String> getTabCompleterHints() {
        return tabCompleterHints;
    }
}

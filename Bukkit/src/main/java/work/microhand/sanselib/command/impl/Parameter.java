package work.microhand.sanselib.command.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import work.microhand.sanselib.command.IParameter;
import work.microhand.sanselib.command.ParameterType;
import work.microhand.sanselib.exception.ParameterParseException;

import java.util.function.Function;

/**
 * @author SanseYooyea
 */
public class Parameter<T> implements IParameter<T> {
    @NotNull
    private final String name;
    @Nullable
    private String description;
    private boolean optional;
    private ParameterType type;
    private Function<String, T> parser;

    public Parameter(@NotNull String name) {
        this.name = name;
    }

    /**
     * 参数的构造函数
     *
     * @param name        标识符
     * @param description 描述，当option为true时，不得为null，当option为false时可为null，此时由name替代
     * @param optional    是否为可选参数
     * @param parser      转换函数
     */
    public Parameter(@NotNull String name, @Nullable String description, boolean optional, @NotNull Function<String, T> parser) {
        this.name = name;
        this.optional = optional;
        this.description = description;
        if (optional && description == null) {
            throw new IllegalArgumentException("option 为 true 时，description 不得为 null");
        }
        this.parser = parser;
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @Nullable
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    @Override
    public ParameterType getParameterType() {
        return type;
    }

    @Override
    public T parse(String arg) {
        return parser.apply(name);
    }

    @Override
    public IParameter<T> description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public IParameter<T> optional(boolean optional) {
        this.optional = optional;
        return this;
    }

    @Override
    public IParameter<T> type(ParameterType type) {
        this.type = type;
        return parser(type.getParser());
    }

    @Override
    public IParameter<T> parser(Function<String, T> parser) {
        this.parser = parser;
        return this;
    }

    @Override
    public IParameter<T> build() throws ParameterParseException {
        if (parser == null) {
            throw new ParameterParseException("parser is null!");
        }
        return this;
    }
}

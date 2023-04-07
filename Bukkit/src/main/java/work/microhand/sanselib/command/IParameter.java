package work.microhand.sanselib.command;

import work.microhand.sanselib.exception.ParameterParseException;

import java.util.function.Function;

/**
 * @author SanseYooyea
 */
public interface IParameter<T> {

    /**
     * Gets the name of the parameter.
     * @return the name of the parameter
     */
    String getName();

    /**
     * Gets the description of the parameter.
     * @return the description of the parameter
     */
    String getDescription();

    /**
     * Checks whether the parameter is optional.
     * @return true if the parameter is optional, false otherwise
     */
    boolean isOptional();

    /**
     * Gets the type of the parameter
     * @return the type of the parameter
     */
    ParameterType getParameterType();

    /**
     * Parses the given argument value and returns an instance of type T.
     * @param arg the argument value to be parsed
     * @return an instance of type T
     * @throws ParameterParseException if the argument value cannot be parsed
     */
    T parse(String arg) throws ParameterParseException;

    /**
     * Set the description for the parameter.
     *
     * @param description the description for the parameter
     * @return this parameter
     */
    IParameter<T> description(String description);

    /**
     * Set the optional for the parameter
     *
     * @param optional is parameter optional
     * @return this parameter
     */
    IParameter<T> optional(boolean optional);

    /**
     * Set the type for the parameter
     *
     * @param type the type of the parameter
     * @return this parameter
     */
    IParameter<T> type(ParameterType type);

    /**
     * Set the parser for the parameter
     *
     * @param parser the parser of the parameter
     * @return this parameter
     */
    IParameter<T> parser(Function<String, T> parser);

    /**
     * Build the parameter
     *
     * @return this parameter
     * @throws ParameterParseException parse null
     */
    IParameter<T> build() throws ParameterParseException;
}

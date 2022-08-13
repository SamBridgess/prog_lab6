package ilya.client.IO;

import java.io.IOException;
import java.util.Objects;

import ilya.client.ClientUtil.RouteValueValidator;
import ilya.common.Exceptions.CtrlDException;
import ilya.common.Exceptions.InvalidValueException;
import ilya.common.Exceptions.WrongFileFormatException;

/**
 * manages input of a field checking input restrictions
 */
public class RouteFieldInputManager {
    /**
     * loops input until all field requirements are fulfilled
     *
     * @param message   message with input invitation
     * @param io        passed IOManager
     * @param clazz     expected input type
     * @param validator contains input restrictions
     * @param <T>
     * @return returns required value
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    public <T> T validatedLoopInput(String message, IOManager io, Class<T> clazz, RouteValueValidator validator)
            throws WrongFileFormatException, CtrlDException {
        while (true) {
            try {
                if (!io.getIsFile()) {
                    io.print(message);
                }
                String s = io.getNextLine();

                Object n;
                if (Objects.equals(s, "") || Objects.equals(s, null)) {
                    n = null;
                } else {
                    n = convertTo(s, clazz);
                }
                T t = (T) n;
                if (!validator.validate(n, clazz)) {
                    throw new InvalidValueException();
                }
                return t;
            } catch (NumberFormatException | InvalidValueException | IOException e) {
                io.printWarning("Invalid value!");
                if (io.getIsFile()) {
                    throw new WrongFileFormatException();
                }
            }
        }
    }

    /**
     * ask whether this collection element should be null
     *
     * @param fieldName name of field
     * @param io        passed IOManager
     * @return returns whether this collection element should be null
     * @throws CtrlDException
     * @throws WrongFileFormatException
     */
    public boolean askIfNull(String fieldName, IOManager io) throws CtrlDException, WrongFileFormatException {
        while (true) {
            try {
                if (!io.getIsFile()) {
                    io.println("Would you want for " + fieldName + " to be null?(y/n)");
                }
                String s = io.getNextLine();
                if (Objects.equals(s, "y") | Objects.equals(s, "Y")) {
                    return true;
                }
                if (Objects.equals(s, "n") | Objects.equals(s, "N")) {
                    return false;
                }
                throw new InvalidValueException();
            } catch (IOException | InvalidValueException e) {
                io.printWarning("Invalid value! Must be Y or N");
                if (io.getIsFile()) {
                    throw new WrongFileFormatException();
                }
            }
        }
    }

    /**
     * converts string to passed type
     *
     * @param s     string to convert
     * @param clazz type to convert to
     * @param <T>
     * @return returns converted object
     */
    @SuppressWarnings("unchecked")
    private <T> T convertTo(String s, Class<?> clazz) {
        T t;
        if (clazz == Integer.class) {
            t = (T) Integer.valueOf(s);
        } else if (clazz == Long.class) {
            t = (T) Long.valueOf(s);
        } else if (clazz == Double.class) {
            t = (T) Double.valueOf(s);
        } else if (clazz == Float.class) {
            t = (T) Float.valueOf(s);
        } else {
            t = (T) s;
        }
        return t;
    }
}

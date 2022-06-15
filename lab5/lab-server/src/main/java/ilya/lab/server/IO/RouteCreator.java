package ilya.lab.server.IO;


import ilya.lab.common.Classes.Coordinates;
import ilya.lab.common.Classes.Location;
import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.Utility.CollectionManager;
import ilya.lab.server.Utility.ValueValidator;

/**
 * creates new Routes
 */
public final class RouteCreator {
    private final float noMax = Float.MAX_VALUE;
    private final float noMin = Float.MIN_VALUE;

    private final long yBiggerThan = -673;
    private final float distanceBiggerThan = 1;
    private final IOManager io;
    private final CollectionManager manager;

    /**
     * creates new RouteCreator
     *
     * @param io        passed IOManager
     * @param manager   passed CollectionManager
     */
    public RouteCreator(IOManager io, CollectionManager manager) {
        this.io = io;
        this.manager = manager;
    }

    /**
     * creates new Route
     *
     * @return          returns created Route
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    public Route createRoute() throws WrongFileFormatException, CtrlDException {
        FieldInputManager inputManager = new FieldInputManager();

        String name = inputManager.validatedLoopInput("Enter route name: ", io, String.class, new ValueValidator(new Number[]{0}, noMin, noMax, false));
        int x = inputManager.validatedLoopInput("Enter X coordinate: ", io, Integer.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
        long y = inputManager.validatedLoopInput("Enter Y coordinate: ", io, Long.class, new ValueValidator(new Number[]{}, yBiggerThan, noMax, false));
        Coordinates coordinates = new Coordinates(x, y);

        Location from;
        if (!inputManager.askIfNull("From Location", io)) {
            Integer fromX = inputManager.validatedLoopInput("Enter from X coordinate: ", io, Integer.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
            long fromY = inputManager.validatedLoopInput("Enter from Y coordinate: ", io, Long.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
            double fromZ = inputManager.validatedLoopInput("Enter from Z coordinate: ", io, Double.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
            String fromName = inputManager.validatedLoopInput("Enter from name: ", io, String.class, new ValueValidator(new Number[]{}, noMin, noMax, true));
            from = new Location(fromX, fromY, fromZ, fromName);
        } else {
            from = null;
        }


        Integer toX = inputManager.validatedLoopInput("Enter to X coordinate: ", io, Integer.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
        long toY = inputManager.validatedLoopInput("Enter to Y coordinate: ", io, Long.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
        double toZ = inputManager.validatedLoopInput("Enter to Z coordinate: ", io, Double.class, new ValueValidator(new Number[]{}, noMin, noMax, false));
        String toName = inputManager.validatedLoopInput("Enter to name: ", io, String.class, new ValueValidator(new Number[]{}, noMin, noMax, true));
        Location to = new Location(toX, toY, toZ, toName);

        float distance = inputManager.validatedLoopInput("Enter distance: ", io, Float.class, new ValueValidator(new Number[]{}, distanceBiggerThan, noMax, false));

        return new Route(manager.assignNewId(), name, coordinates, from, to, distance);
    }

}

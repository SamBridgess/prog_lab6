package ilya.lab.client.ClientUtil;

public class CommandInfo {
    private Integer numberOfArgs;
    private boolean requiresNewRoute;
    public CommandInfo(Integer numberOfArgs, boolean requiresNewRoute) {
        this.numberOfArgs = numberOfArgs;
        this.requiresNewRoute = requiresNewRoute;
    }
    public Integer getNumberOfArgs() {
        return numberOfArgs;
    }
    public boolean getRequiresNewRoute() {
        return requiresNewRoute;
    }
}

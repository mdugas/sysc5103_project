package ca.carleton.sce.actions;

import ca.carleton.sce.SendCommand;
import jason.asSyntax.Structure;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class Action {

    private static final Logger LOGGER = Logger.getLogger(Action.class.getName());

    private final String name;
    private final Logger childLogger;

    Action(String name) {
        this.name = name;
        this.childLogger = Logger.getLogger(this.getClass().getName());
        this.childLogger.setParent(LOGGER);
    }

    public final boolean matches(Structure structure) {
        return structure.getFunctor().equals(this.name);
    }

    public final boolean execute(SendCommand agent, Structure structure) {
        LOGGER.log(Level.INFO, String.format("Executing action: %s", structure));
        return this.doExecute(agent, structure);
    }

    Logger getLogger() {
        return this.childLogger;
    }

    abstract boolean doExecute(SendCommand agent, Structure structure);

    public static Collection<Action> getAll() {
        Reflections reflections = new Reflections(Action.class.getPackage().getName());
        Set<Class<? extends Action>> actionClasses = reflections.getSubTypesOf(Action.class);
        return actionClasses.stream().map(clazz -> {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.log(Level.WARNING, String.format("Invalid action defined: %s", clazz.getName()), e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}

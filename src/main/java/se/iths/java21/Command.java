package se.iths.java21;

public interface Command {
    public static final String UNDO = "undo";
    public static final String REDO = "redo";
    public void execute(String command);
}

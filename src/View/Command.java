package View;

public abstract class Command {
    private String option;
    private String description;

    public Command(String option, String description)
    {
        this.option = option;
        this.description = description;
    }

    public abstract void execute();

    public String getOption()
    {
        return option;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return String.format("%2s : %s", option, description);
    }
}

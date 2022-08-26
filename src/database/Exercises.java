package database;

public class Exercises extends DB
{
    private String objective;
    private String exName;
    private String sets;
    private String reps;
    private String rest;

    public String getObjective() { return objective; }

    public void setObjective(String objective) { this.objective = objective; }

    public String getExName() { return exName; }

    public void setExName(String name) { this.exName = name; }

    public String getSets() { return sets; }

    public void setSets(String sets) { this.sets = sets; }

    public String getReps() { return reps; }

    public void setReps(String reps) { this.reps = reps; }

    public String getRest() { return rest; }

    public void setRest(String rest) { this.rest = rest; }

    public Exercises() { }

    public Exercises(String objective, String exName, String sets, String reps, String rest)
    {
        this.objective = objective;
        this.exName = exName;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
    }

    @Override
    public String toString()
    {
        return "Exercises{" +
                "objective='" + objective + '\'' +
                ", exName='" + exName + '\'' +
                ", sets='" + sets + '\'' +
                ", reps='" + reps + '\'' +
                ", rest='" + rest + '\'' +
                '}';
    }
}

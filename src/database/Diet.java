package database;

public class Diet
{
    private int id;
    private String name;
    private String objective;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getObjective() { return objective; }

    public void setObjective(String objective) { this.objective = objective; }

    public Diet() { }

    public Diet(int id, String name, String objective)
    {
        this.id = id;
        this.name = name;
        this.objective = objective;
    }

    @Override
    public String toString()
    {
        return "Diet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", objective='" + objective + '\'' +
                '}';
    }
}

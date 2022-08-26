package database;

public class Halls extends DB
{
    int id;
    int capacity;
    String name;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Halls() { }

    public Halls(int id, String name, int capacity)
    {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() { return "halls" + " id = " + id + ", capacity = " + capacity + ", name = " + name; }
}

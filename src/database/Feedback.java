package database;

public class Feedback extends DB
{
    private int id;
    private String forWho;
    private String text;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getForWho() { return forWho; }

    public void setForWho(String forWho) { this.forWho = forWho; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public Feedback() { }

    public Feedback(String forWho, String text)
    {
        this.forWho = forWho;
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "Feedback{" +
                "id=" + id +
                ", forWho='" + forWho + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

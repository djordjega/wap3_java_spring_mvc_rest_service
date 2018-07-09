package djordje.wap3m.model;

/**
 *
 * @author djordje gavrilovic
 */
public class Entry {
    
    private int id;
    private String title;
    private String text;

    public Entry(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
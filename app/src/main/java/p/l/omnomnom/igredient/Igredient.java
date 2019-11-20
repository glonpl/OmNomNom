package p.l.omnomnom.igredient;

public class Igredient {
    private int id;
    private String name;

    public Igredient(){

    }

    public Igredient(String name){
        this.name = name;
    }

    public Igredient(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

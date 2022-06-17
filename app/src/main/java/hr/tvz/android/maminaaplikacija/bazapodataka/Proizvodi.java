package hr.tvz.android.maminaaplikacija.bazapodataka;

public class Proizvodi {
    private int id;
    private String title;
    private String created_at;

    // constructors
    public Proizvodi() {
    }

    public Proizvodi(String title) {
        this.title = title;
    }

    public Proizvodi(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Naslov: " + this.title;
    }
}

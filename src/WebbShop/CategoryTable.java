package WebbShop;

public class CategoryTable implements Interface {
    public CategoryTable(int id, String namn) {
        this.id = id;
        this.namn = namn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    protected int id;
    protected String namn;

    @Override
    public String printInfo() {
        return "Category:" + getNamn();
    }
}



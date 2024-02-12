package WebbShop.Tables;

import WebbShop.Interface;

public class CategoryTable implements Interface {
    protected int id;
    protected String namn;
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

    @Override
    public String printInfo() {
        return "Category:" + getNamn();
    }
}



package WebbShop;

public class ProductTable implements Interface{
    int id;
    String brand;
    int size;
    String color;
    int price;
    int categoryId;

    public ProductTable(int id, String brand, int size, String color, int price, int categoryId) {
        this.id = id;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
        this.categoryId = categoryId;
    }

    public ProductTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String printInfo() {
        return "Märke:" + getBrand() + " Storlek:" + getSize() + " Pris" + ":" + getPrice() + "kr" + "Färg: " + getColor();
    }

}

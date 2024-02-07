package WebbShop;

public class CustomerTable {
    protected String namn;
    protected int phoneNumber;
    protected long socialSecurityNumber;


    public CustomerTable() {}

    public CustomerTable(String namn, int phoneNumber, int socialSecurityNumber) {
        this.namn = namn;
        this.socialSecurityNumber = socialSecurityNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(long socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

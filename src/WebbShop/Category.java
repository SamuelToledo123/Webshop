package WebbShop;

import java.util.HashMap;

public class Category {

    HashMap<Integer,String> idToName = new HashMap<>();

    public Category(HashMap<Integer, String> idToName) {
        this.idToName = idToName;
    }

    public HashMap<Integer, String> getIdToName() {
        return idToName;
    }

    public void setIdToName(HashMap<Integer, String> idToName) {
        this.idToName = idToName;
    }
}

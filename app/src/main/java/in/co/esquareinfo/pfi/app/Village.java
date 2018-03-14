package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 14-03-2018.
 */

public class Village {

    private String villageId, villageName;

    public Village(String id, String name) {
        villageId = id;
        villageName = name;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villName) {
        villageName = villName;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villId) {
        villageId = villId;
    }


    public String toString() {
        return villageName;
    }
}

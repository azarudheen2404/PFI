package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 25-02-2018.
 */

public class Panchayath {

    private String panchId, panchName;

    public Panchayath(String id, String name) {
        panchId = id;
        panchName = name;
    }

    public String getPanchName() {
        return panchName;
    }

    public void setPanchName(String pnchName) {
        panchName = pnchName;
    }

    public String getPanchId() {
        return panchId;
    }

    public void setPanchId(String pnchId) {
        panchId = pnchId;
    }


    public String toString() {
        return panchName;
    }

}

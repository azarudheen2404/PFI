package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 25-02-2018.
 */

public class Panchayath {

    private String panchId, panchName;
    private String stateId, districtId,blockId;

    public Panchayath(String id, String name, String stId, String dtId, String blId) {
        panchId = id;
        panchName = name;
        stateId = stId;
        districtId = dtId;
        blockId = blId;
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

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stId) {
        stateId = stId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String dtId) {
        districtId = dtId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blckId) {
        blockId = blckId;
    }

    public String toString() {
        return panchName;
    }

}

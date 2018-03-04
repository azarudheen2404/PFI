package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 25-02-2018.
 */

public class Cluster {

    private String clusterId, clusterName;
    private String stateId, districtId,blockId,panchId;

    public Cluster(String id, String name, String stId, String dtId) {
        clusterId = id;
        clusterName = name;
        stateId = stId;
        districtId = dtId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clstrName) {
        clusterName = clstrName;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clstrId) {
        clusterId = clstrId;
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

    public String getPanchId() {
        return panchId;
    }

    public void setPanchId(String pnchId) {
        panchId = pnchId;
    }

    public String toString() {
        return clusterName;
    }
}

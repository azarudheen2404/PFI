package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 24-02-2018.
 */

public class Block {

    private String blockId;
    private String blockName;
    private String stateId, districtId;

    public Block(String id, String name, String stId, String dtId) {
        blockId = id;
        blockName = name;
        stateId = stId;
        districtId = dtId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blckName) {
        blockName = blckName;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blckId) {
        blockId = blckId;
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

    public String toString() {
        return blockName;
    }
}

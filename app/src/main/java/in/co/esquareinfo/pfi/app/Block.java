package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 24-02-2018.
 */

public class Block {

    private String blockId;
    private String blockName;

    public Block(String id, String name) {
        blockId = id;
        blockName = name;

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

    public String toString() {
        return blockName;
    }
}

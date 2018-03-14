package in.co.esquareinfo.pfi.app;

/**
 * Created by azarudheen on 14-03-2018.
 */

public class KeyValue {

    private String key, value;

    public KeyValue(String id, String name) {
        key = id;
        value = name;
    }

    public String getRationName() {
        return value;
    }

    public void setRationName(String ranName) {
        value = ranName;
    }

    public String getRationId() {
        return key;
    }

    public void setRationId(String ranId) {
        key = ranId;
    }


    public String toString() {
        return value;
    }
}

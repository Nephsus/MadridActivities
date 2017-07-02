package kc.dcava.domain.managers.sharedpreferences;

/**
 * Created by davidcavajimenez on 30/6/17.
 */

public interface PostTimeLastUpdateManager {

    void saveDataInConfigFile(String filename, String key, String data);
}

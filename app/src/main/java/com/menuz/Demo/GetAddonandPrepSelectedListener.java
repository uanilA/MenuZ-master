package com.menuz.Demo;

import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.ItemPreprationModel;

import java.util.HashMap;

public interface GetAddonandPrepSelectedListener {

    void getHashMapAddons(HashMap<String, AdddonChildModel> adddonChildModelHashMap);
    void getHashMapPreprationAddons(HashMap<String, AddonPreprationModel> addonPreprationModelHashMap);
    void getHashMapPreprationItem(HashMap<String, ItemPreprationModel> addonPreprationModelHashMap);
    void getRemarkItem(String remark);
    void getAddonGroup(HashMap<String, AddOnModel> addOnModelHashMap);
}

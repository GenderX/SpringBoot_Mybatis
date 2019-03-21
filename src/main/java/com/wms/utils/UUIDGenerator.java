package com.wms.utils;

import java.util.UUID;

public class UUIDGenerator {
    /**
     * 根据UUID生成不重复的数字ID
     * @return
     */
    public static String createID(){
    int machineId=1;
    int hashCodev= UUID.randomUUID().toString().hashCode();
    if(hashCodev<0){
        hashCodev=-hashCodev;
    }
    return machineId+String.format("%015d",hashCodev);
}

}

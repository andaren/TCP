package com.tagding.utils;

import java.util.UUID;

public abstract class IDGenerator {
	/** 
     *  UID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString();  
        return s;//s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }  
}

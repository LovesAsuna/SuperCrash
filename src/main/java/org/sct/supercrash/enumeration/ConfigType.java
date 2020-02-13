package org.sct.supercrash.enumeration;

import lombok.Getter;

public enum ConfigType {

    SETTING_DEFAULTMSG("Setting.DefaultMsg");


    @Getter String path;

    ConfigType(String path) {
        this.path = path;
    }

}

package org.sct.supercrash.enumeration;

import lombok.Getter;

public enum ConfigType {

    SETTING_DEFAULTSUBMSG("Setting.DefaultSubMsg"),
    SETTING_CRASH("Setting.Crash"),
    SETTING_DEFAULTMSG("Setting.DefaultMsg");


    @Getter String path;

    ConfigType(String path) {
        this.path = path;
    }

}

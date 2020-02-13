package org.sct.supercrash.enumeration;

import lombok.Getter;

public enum LangType {

    /**
     * 语言文件的路径
     */
    LANGUAGE_COMMANDHELP("Language.CommandHelp"),
    LANG_NOPERMISSION("Language.NoPermission"),
    LANG_RELOAD("Language.Reload"),
    LANG_COMMANDERROR("Language.CommandError"),
    LANG_SUCCESSSEND("Language.SuccessSend"),
    LANG_NOTEXISTPLAYER("Language.NotExistPlayer");

    @Getter String path;

    LangType(String path) {
        this.path = path;
    }

}

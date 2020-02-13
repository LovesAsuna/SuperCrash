package org.sct.supercrash.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.plugincore.util.BasicUtil;
import org.sct.supercrash.SuperCrash;
import org.sct.supercrash.enumeration.LangType;

import java.io.File;
import java.util.List;

public class Lang {

    private static File file;
    private static YamlConfiguration config;

    public static void loadLang() {
        file = new File(SuperCrash.getInstance().getDataFolder() + File.separator + "lang.yml");
        if (!file.exists()) { SuperCrash.getInstance().saveResource("lang.yml",false); }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static String getString(LangType langType) {
        return BasicUtil.convert(config.getString(langType.getPath()));
    }

    public static List<String> getStringList(LangType langType) {
        return BasicUtil.convert(config.getStringList(langType.getPath()));
    }

}

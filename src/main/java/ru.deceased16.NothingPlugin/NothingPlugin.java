package ru.deceased16.NothingPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class NothingPlugin extends JavaPlugin {

    private Language language = Language.DEFAULT;

    @Override
    public void onEnable() {
        // Creates config.yml in the plugin folder if it doesn't exist yet
        saveDefaultConfig();

        String configured = getConfig().getString("language", Language.DEFAULT.getCode());
        Language found = Language.fromCode(configured);

        if (found == null) {
            getLogger().warning("Unknown language '" + configured + "'. Supported: "
                    + Language.supportedCodes() + ". Falling back to " + Language.DEFAULT.getCode() + ".");
        } else {
            language = found;
        }

        getLogger().info(language.getEnableMessage());
    }

    @Override
    public void onDisable() {
        getLogger().info(language.getDisableMessage());
    }
}
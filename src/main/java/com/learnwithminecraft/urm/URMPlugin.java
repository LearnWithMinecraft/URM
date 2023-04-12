package com.learnwithminecraft.urm;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import com.learnwithminecraft.urm.listener.ProjectileListener;

public class URMPlugin extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ProjectileListener(), this);
        getLogger().info(TextFormat.GREEN + "Plugin made by Learn With Minecraft Team, enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(TextFormat.RED + "Plugin made by Learn With Minecraft Team, disabled!");
    }
}
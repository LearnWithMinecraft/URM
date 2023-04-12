package com.learnwithminecraft.urm.listener;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.EntityProjectile;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ProjectileHitEvent;
import cn.nukkit.event.entity.ProjectileLaunchEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.TextFormat;
import com.learnwithminecraft.urm.utils.VectorUtils;

import java.util.Date;

public class ProjectileListener implements Listener {

    private static final String NBT_START_TIME = "nbt_start_time_urm";
    private static final String NBT_START_VECTOR = "nbt_start_vector_urm";

    @EventHandler
    private void onProjectileLaunch(ProjectileLaunchEvent event) {
        EntityProjectile projectile = event.getEntity();

        Entity shootingEntity = projectile.shootingEntity;
        if (!(shootingEntity instanceof Player)) {
            return;
        }

        Player player = (Player) shootingEntity;

        CompoundTag namedTag = projectile.namedTag;
        if (namedTag == null) {
            return;
        }

        long startTime = System.currentTimeMillis();
        Vector3 startVector3 = projectile.getPosition().asVector3f().asVector3();

        namedTag.putLong(NBT_START_TIME, startTime);
        namedTag.putString(NBT_START_VECTOR, VectorUtils.vector3ToString(startVector3));

        Date date = new Date();
        int x = (int) startVector3.getX();
        int y = (int) startVector3.getY();
        int z = (int) startVector3.getZ();

        player.sendMessage(TextFormat.GREEN + String.format("You threw a projectile at %s in (X: %s, Y: %s, Z: %s)", date, x, y, z));
    }

    @EventHandler
    private void onProjectileHit(ProjectileHitEvent event) {
        EntityProjectile projectile = (EntityProjectile) event.getEntity();

        Entity shootingEntity = projectile.shootingEntity;
        if (!(shootingEntity instanceof Player)) {
            return;
        }

        Player player = (Player) shootingEntity;

        CompoundTag namedTag = projectile.namedTag;
        if (namedTag == null) {
            return;
        }

        if (!namedTag.contains(NBT_START_TIME) || !namedTag.contains(NBT_START_VECTOR)) {
            return;
        }

        long startTime = namedTag.getLong(NBT_START_TIME);
        Vector3 startVector3 = VectorUtils.stringToVector3(namedTag.getString(NBT_START_VECTOR));

        long currentTime = System.currentTimeMillis();
        Vector3 currentVector3 = event.getMovingObjectPosition().hitVector;

        double distance = startVector3.distance(currentVector3);
        double time = (currentTime - startTime) / 1000d;
        double velocity = (distance / time);

        player.sendMessage(TextFormat.GOLD + String.format("The velocity of the projectile was %s blocks/second", velocity));
        player.sendMessage(TextFormat.GOLD + String.format("The time to hit the projectile with a block was %s seconds", time));
        player.sendMessage(TextFormat.GOLD + String.format("The distance traveled by the projectile was %s blocks", distance));

        Date date = new Date();
        int x = (int) currentVector3.getX();
        int y = (int) currentVector3.getY();
        int z = (int) currentVector3.getZ();

        player.sendMessage(TextFormat.GREEN + String.format("The projectile you threw fell %s in (X: %s, Y: %s, Z: %s)", date, x, y, z));
    }
}

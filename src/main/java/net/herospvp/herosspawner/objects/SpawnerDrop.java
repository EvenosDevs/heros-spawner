package net.herospvp.herosspawner.objects;

import lombok.Getter;
import net.herospvp.heroscore.utils.items.ItemBuilder;
import net.herospvp.heroscore.utils.items.SkullCreator;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

@Getter
public enum SpawnerDrop {
    SILVERFISH(Material.SKULL_ITEM,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW" +
                    "5lY3JhZnQubmV0L3RleHR1cmUvZDA2MzEwYTg5NTJiMjY1YzZlNmJlZDQzNDgyMzlkZG" +
                    "VhOGU1NDgyYzhjNjhiZTZmZmY5ODFiYTgwNTZiZjJlIn19fQ==",
            EntityType.SILVERFISH, 50),

    ZOMBIE(Material.ROTTEN_FLESH, EntityType.ZOMBIE, 0.5),
    SKELETON(Material.BONE, EntityType.SKELETON, 0.7),
    IRON_GOLEM(Material.IRON_INGOT, EntityType.IRON_GOLEM, 1),
    CREEPER(Material.TNT, EntityType.CREEPER, 0),
    SNOW_GOLEM(Material.PUMPKIN, EntityType.SNOWMAN, 1.3),
    VILLAGER(Material.EMERALD, EntityType.VILLAGER, 1.6);

    private final Material dropType;
    private final String textureHead;
    private final EntityType entityType;
    private final double money;

    SpawnerDrop(Material dropType, String textureHead, EntityType entityType, double money) {
        this.dropType = dropType;
        this.textureHead = textureHead;
        this.entityType = entityType;
        this.money = money;
    }

    SpawnerDrop(Material dropType, EntityType entityType, double money) {
        this(dropType, null, entityType, money);
    }

    public static ItemStack getDrop(EntityType entityType) {
        if (entityType == EntityType.SILVERFISH) {
            return new ItemBuilder(SkullCreator.itemFromBase64(SILVERFISH.textureHead)).setName("&eSilverfish Core")
                    .setLore("&f&oQuesto item lo puoi vedere", "&f&outilizzando il comando &e&o/sellall").toItemStack();
        }

        for (SpawnerDrop value : values()) {
            if (entityType == value.entityType) {
                return new ItemStack(value.getDropType());
            }
        }
        return null;
    }

    public static double getPrice(Material material) {
        for (SpawnerDrop value : values()) {
            if (value.getDropType() == material) {
                return value.getMoney();
            }
        }
        return 0;
    }
}

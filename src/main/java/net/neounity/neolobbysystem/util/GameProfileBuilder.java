package net.neounity.neolobbysystem.util;

import java.lang.reflect.Field;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class GameProfileBuilder {
    public ItemStack getSkullByTextureURL(String textureURL, String displayName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        textureURL = "http://textures.minecraft.net/texture/" + textureURL;
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(displayName);
        try {
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
            gameProfile.getProperties().put("textures", new Property("textures",
                    Base64Coder.encodeString("{textures:{SKIN:{url:\"" + textureURL + "\"}}}")));
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, gameProfile);
            head.setItemMeta(headMeta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return head;
    }
}

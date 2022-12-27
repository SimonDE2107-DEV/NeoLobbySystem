/* Copyright 2016 Acquized
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neounity.neolobbysystem.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private final ItemStack item;
    private ItemMeta meta;
    private final Material material;
    private int amount = 1;
    private short damage = 0;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final String displayname;
    private List<String> lore = new ArrayList<>();
    private final List<ItemFlag> flags = new ArrayList<>();

    private final boolean andSymbol = true;
    private final boolean unsafeStackSize = false;

    public ItemBuilder(Material material, int amount, String displayname) {
        if (material == null) {
            material = Material.AIR;
        }
        this.item = new ItemStack(material, amount);
        this.material = material;
        if (((amount > material.getMaxStackSize()) || (amount <= 0)) && (!unsafeStackSize)) {
            amount = 1;
        }
        this.amount = amount;
        this.displayname = displayname;
    }

    public ItemBuilder durability(short damage) {
        this.damage = damage;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchant, int level) {
        enchantments.put(enchant, level);
        return this;
    }

    public ItemBuilder lore(String line, int index) {
        lore.set(index, andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
        return this;
    }
    public ItemBuilder loreList(List list) {
        this.lore = list;
        return this;
    }

    public ItemBuilder flag(ItemFlag flag) {
        flags.add(flag);
        return this;
    }

    public ItemBuilder unbreakable() {
        meta.setUnbreakable(true);
        return this;
    }
    public ItemBuilder glow() {
        enchant(material != Material.BOW ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 1);
        flag(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    @Deprecated
    public ItemBuilder setSkullOwner(String user) {
        // Validate.notNull(user, "The username is null.");
        if ((material == Material.PLAYER_HEAD)) {
            SkullMeta smeta = (SkullMeta) meta;
            smeta.setOwner(user);
            meta = smeta;
        }
        return this;
    }

    public ItemStack build() {
        item.setType(material);
        item.setAmount(amount);
        item.setDurability(damage);
        meta = item.getItemMeta();
        if (enchantments.size() > 0) {
            item.addUnsafeEnchantments(enchantments);
        }
        if (displayname != null) {
            meta.setDisplayName(displayname);
        }
        if (lore.size() > 0) {
            meta.setLore(lore);
        }
        if (flags.size() > 0) {
            for (ItemFlag f : flags) {
                meta.addItemFlags(f);
            }
        }
        item.setItemMeta(meta);
        return item;
    }
}
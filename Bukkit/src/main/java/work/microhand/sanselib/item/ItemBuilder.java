package work.microhand.sanselib.item;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.*;

/**
 * @author SanseYooyea
 */
public class ItemBuilder {
    private final Material material;
    private int amount;
    private byte data;
    private String name;
    private List<String> lore;
    private int customData;
    private Map<Enchantment, Integer> enchantments;
    private Set<ItemFlag> itemFlags;
    private boolean unbreakable;
    private Multimap<Attribute, AttributeModifier> attributeModifiers;
    private int durability;

    public ItemBuilder(Material material) {
        this.material = material;
        lore = new ArrayList<>();
        enchantments = new HashMap<>();
        itemFlags = new HashSet<>();
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder data(byte data) {
        this.data = data;
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder lore(String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public ItemBuilder lore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemBuilder customData(int customData) {
        this.customData = customData;
        return this;
    }

    public ItemBuilder enchantment(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder enchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag... itemFlags) {
        this.itemFlags.addAll(Arrays.asList(itemFlags));
        return this;
    }

    public ItemBuilder itemFlags(Set<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder attributeModifiers(Multimap<Attribute, AttributeModifier> attributeModifiers) {
        this.attributeModifiers = attributeModifiers;
        return this;
    }

    public ItemBuilder durability(int durability) {
        this.durability = durability;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);

        amount = amount == 0 ? 1 : amount;
        item.setAmount(amount);

        if (data != 0) {
            item.setData(new MaterialData(material, data));
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            throw new NullPointerException("ItemMeta is null");
        }

        if (name != null) {
            meta.setDisplayName(name);
        }

        if (lore.size() != 0) {
            meta.setLore(lore);
        }

        if (customData != 0) {
            meta.setCustomModelData(customData);
        }

        if (enchantments.size() != 0) {
            enchantments.forEach((key, value) -> meta.addEnchant(key, value, true));
        }

        if (itemFlags.size() != 0) {
            itemFlags.forEach(meta::addItemFlags);
        }

        if (unbreakable) {
            meta.setUnbreakable(true);
        }

        if (attributeModifiers != null) {
            meta.setAttributeModifiers(attributeModifiers);
        }

        if (durability != 0) {
            item.setDurability((short) durability);
        }

        item.setItemMeta(meta);
        return item;
    }
}

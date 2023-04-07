package work.microhand.sanselib.ui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SanseYooyea
 */
public class InventoryBuilder {
    private final String title;
    private InventoryHolder owner;
    private int size;
    private InventoryType type;
    private int maxStackSize;
    private Map<Integer, ItemStack> items;

    public InventoryBuilder(String title) {
        this.title = title;
        items = new HashMap<>();
    }

    public InventoryBuilder owner(InventoryHolder owner) {
        this.owner = owner;
        return this;
    }

    public InventoryBuilder size(int size) {
        this.size = size;
        return this;
    }

    public InventoryBuilder type(InventoryType type) {
        this.type = type;
        return this;
    }

    public InventoryBuilder maxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

    public InventoryBuilder items(Map<Integer, ItemStack> items) {
        this.items = items;
        return this;
    }

    public InventoryBuilder item(int slot, ItemStack item) {
        this.items.put(slot, item);
        return this;
    }

    public Inventory build() {
        Inventory inventory;
        if (type != null) {
            inventory = Bukkit.createInventory(owner, type, title);
        } else if (size != 0) {
            inventory = Bukkit.createInventory(owner, size, title);
        } else {
            return null;
        }

        if (maxStackSize != 0) {
            inventory.setMaxStackSize(maxStackSize);
        }

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }

        return inventory;
    }
}

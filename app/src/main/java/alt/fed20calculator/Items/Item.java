package alt.fed20calculator.Items;

import android.util.Log;

import java.util.HashMap;

/**
 * Defines an item that a character can hold on their inventory. This item can be either a weapon or a consumable.
 */
public class Item implements Cloneable {
    static final String TAG = "Item";
    private boolean drop = false;
    String name, effect;
    int might = 0, hit = 0, critical = 0, weight = 0, minRange = 0, maxRange = 0, durability, expGranted = 0;
    char proficiency = 'E';

    /**
     * Creates a consumable item that cannot be equipped or used in battle.
     * @param name Item's name.
     * @param durability Times the item can be used
     * @param effect Description of the item's effect.
     */
    public Item(String name, int durability, String effect){
        this.name = name;
        this.durability = durability;
        this.effect = effect;
    }

    Item() {
    }

    /**
     * Each time an item is used it reduces its durability. If it reaches 0 the item is wasted and cannot be used anymore.
     * @return uses left.
     */
    public int used(){
        durability--;
        if(durability <= 0) Log.d(TAG, "Weapon " + name + " has broken.");
        return durability;
    }

    /**
     * Gets all item's data.
     * @return List with the item's data.
     */
    public HashMap<String, String> getItem(){
        HashMap<String, String> item = new HashMap<>();
        item.put("name", name);
        item.put("durability", String.valueOf(durability));
        item.put("effect", effect);

        return item;
    }

    /**
     * Sets the item as droppable.
     */
    public void setDrop(){
        drop = true;
    }

    /**
     * Checks if the item is droppable.
     * @return Boolean value true if the item is droppable.
     */
    public boolean isDrop() {
        return drop;
    }

    @Override
    protected Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }
}

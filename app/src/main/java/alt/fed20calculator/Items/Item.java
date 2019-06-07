package alt.fed20calculator.Items;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Defines an item that a character can hold on their inventory. This item can be either a weapon or a consumable.
 */
public class Item implements Cloneable {
    private static final String TAG = "Item";
    private boolean dType, inverted = false, weapon = false, drop = false;
    private String name, type, effect, effective;
    private int might = 0, hit = 0, critical = 0, weight = 0, minRange = 0, maxRange = 0, durability, expGranted = 0;
    private char proficiency = 'E';

    /**
     * Creates a weapon item.
     * @param name Weapon's name.
     * @param type Weapon's type. Defines its functionality in the weapon triangle and the trinity of magic.
     * @param dType Type of damage the weapon deals. Define as True if Physic, False if Magic.
     * @param might Weapon's damage dealt. Adds to the character's strength or magic stat depending on damage type.
     * @param hit Weapon's chance of hitting the enemy.
     * @param critical Weapon's chance of dealing critical damage.
     * @param weight Weapon's weight. For each point it exceeds the wielder's constitution subtract a point of their speed.
     * @param minRange Closest distance the weapon can initiate or answer an attack.
     * @param maxRange Farthest distance the weapon can initiate or answer an attack.
     * @param durability Amount of times the weapon can be used before breaking. If the weapon breaks it cannot be used anymore.
     * @param proficiency Minimum level of exigence with the weapon type required to use this weapon.
     * @param effective Effectiveness of the weapon against some weakness.
     * @param effect Description of an effect dealt by the weapon if it has one.
     */
    public Item(String name, String type, boolean dType, int might, int hit, int critical, int weight, int minRange, int maxRange, int durability, char proficiency, String effective, String effect) {
        this.name = name;
        this.type = type;
        this.dType = dType;
        this.might = might;
        this.hit = hit;
        this.critical = critical;
        this.weight = weight;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.durability = durability;
        this.proficiency = proficiency;
        this.effective = effective;
        this.effect = effect;
        weapon = true;
        Log.d(TAG, "Custom weapon " + name + " created.");
    }

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
        type = "Consumable";
    }

    /**
     * Especial constructor to create a support item that cannot be equipped or used in battle.
     * @param name Item's name.
     * @param type Item's type. Should be "staff" for healing or "flute" for extra movement.
     * @param minRange Closest distance from its objective the item can be used.
     * @param maxRange Farthest distance from its objective the item can be used.
     * @param durability Amount of times the item can be used before breaking.
     * @param proficiency Only for staffs. Weapon level required to use this item.
     * @param effect Description of the item's effect.
     */
    public Item(String name, String type, int minRange, int maxRange, int durability, char proficiency, int expGranted, String effect) {
        this.name = name;
        this.type = type;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.durability = durability;
        this.proficiency = proficiency;
        this.expGranted = expGranted;
        this.effect = effect;
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
     * @return List with all the item's data.
     */
    public HashMap<String, String> getItem(){
        HashMap<String, String> item = new HashMap<>();
        item.put("name", name);
        item.put("weapon", String.valueOf(weapon));
        item.put("type", type);
        item.put("durability", String.valueOf(durability));
        if(weapon){
            item.put("dType", String.valueOf(dType));
            item.put("might", String.valueOf(might));
            item.put("hit", String.valueOf(hit));
            item.put("critical", String.valueOf(critical));
            item.put("weight", String.valueOf(weight));
            item.put("minRange", String.valueOf(minRange));
            item.put("maxRange", String.valueOf(maxRange));
            item.put("effective", effective);
            item.put("proficiency", String.valueOf(proficiency));
            item.put("inverted", String.valueOf(inverted));
        }
        item.put("effect", effect);

        return item;
    }

    /**
     * Improves the weapon's attributes.
     * @param might Amount of points added to the weapon's might.
     * @param hit Amount of points added to the weapon's hit.
     * @param critical Amount of points added to the weapon's crit.
     * @param weight Amount of points subtracted to the weapon's weight. Final result cannot be lower than 1.
     */
    public void forge(int might, int hit, int critical, int weight){
        if(weapon){
            this.might += might;
            this.hit += hit;
            this.critical += critical;
            if(this.weight - weight >= 1) this.weight -= weight;
            else this.weight = 1;
        }
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

    /**
     * Sets the weapon as inverted. Weapon triangle and trinity of magic is inverted during battles.
     */
    public void setInverted(){
        inverted = true;
    }

    /**
     * Checks if this is an inverted weapon.
     * @return Boolean value true if this is an inverted weapon.
     */
    public boolean isInverted() {
        return inverted;
    }

    @Override
    protected Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }
}

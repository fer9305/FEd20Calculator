package alt.fed20calculator.Items;

import android.util.Log;

import java.util.HashMap;

public class Weapon extends Item {

    boolean dType, inverted = false;
    String effective, type;

    /**
     * Creates a weapon item.
     * @param name Weapon's name.
     * @param type Weapon's type. Defines its functionality in the weapon triangle, the trinity of magic and grants bonuses for user's affinity.
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
     * @param effect Description of the item's special effect if it has one.
     */
    public Weapon(String name, String type, boolean dType, int might, int hit, int critical, int weight, int minRange, int maxRange, int durability, char proficiency, String effective, String effect) {
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
        Log.d(TAG, "Custom weapon " + name + " created.");
    }

    Weapon() {
    }

    /**
     * Gets all item's data.
     * @return List with all the item's data.
     */
    @Override
    public HashMap<String, String> getItem(){
        HashMap<String, String> item = new HashMap<>();
        item.put("name", name);
        item.put("type", type);
        item.put("durability", String.valueOf(durability));
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
        this.might += might;
        this.hit += hit;
        this.critical += critical;
        if(this.weight - weight >= 1) this.weight -= weight;
        else this.weight = 1;
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
}

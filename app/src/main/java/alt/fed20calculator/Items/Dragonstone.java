package alt.fed20calculator.Items;

import android.util.Log;

public class Dragonstone extends Weapon {
    private int[] bonuses;
    /**
     * Creates a weapon item.
     *
     * @param name        Weapon's name.
     * @param dType       Type of damage the weapon deals. Define as True if Physic, False if Magic.
     * @param might       Weapon's damage dealt. Adds to the character's strength or magic stat depending on damage type.
     * @param hit         Weapon's chance of hitting the enemy.
     * @param critical    Weapon's chance of dealing critical damage.
     * @param weight      Weapon's weight. For each point it exceeds the wielder's constitution subtract a point of their speed.
     * @param minRange    Closest distance the weapon can initiate or answer an attack.
     * @param maxRange    Farthest distance the weapon can initiate or answer an attack.
     * @param durability  Amount of times the weapon can be used before breaking. If the weapon breaks it cannot be used anymore.
     * @param proficiency Minimum level of exigence with the weapon type required to use this weapon.
     * @param effective   Effectiveness of the weapon against some weakness.
     * @param effect      Description of the item's special effect if it has one.
     */
    public Dragonstone(String name, boolean dType, int might, int hit, int critical, int weight, int minRange, int maxRange, int durability, char proficiency, String effective, String effect, int[] bonuses) {
        this.name = name;
        type = "Dragonstone";
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
        this.bonuses = bonuses;
        Log.d(TAG, "Custom weapon " + name + " created.");
    }
}

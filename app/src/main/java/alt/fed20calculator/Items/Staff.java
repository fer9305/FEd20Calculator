package alt.fed20calculator.Items;

import alt.fed20calculator.Characters.Player;

public class Staff extends Item {
    /**
     * Creates a staff item to heal allies but cannot be equipped or used in battle.
     * @param name Item's name.
     * @param minRange Closest distance from its objective the item can be used.
     * @param maxRange Farthest distance from its objective the item can be used.
     * @param durability Amount of times the item can be used before breaking.
     * @param proficiency Only for staffs. Weapon level required to use this item.
     * @param effect Description of the item's special effect if it has one.
     */
    public Staff(String name, int minRange, int maxRange, int durability, char proficiency, int expGranted, String effect) {
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.durability = durability;
        this.proficiency = proficiency;
        this.expGranted = expGranted;
        this.effect = effect;
    }
}

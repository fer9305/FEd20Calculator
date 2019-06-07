package alt.fed20calculator.Items;

import java.util.HashMap;
import java.util.Map;

public class ItemPool {
    private Map<String, Item> pool = new HashMap<>();
    public ItemPool() {
        Item special;
        /*---------------------------------------Swords-------------------------------------------*/
        pool.put("bSword", new Item("Bronze Sword", "Sword", true, 3,
                100, 0, 3, 1, 1, 50, 'E',
                "", "" ));
        pool.put("gSword", new Item("Glass sword", "Sword", true, 11,
                95, 0, 4, 1, 1, 3, 'E',
                "", ""));
        pool.put("rapier", new Item("Rapier", "Sword", true, 3, 100,
                5, 2, 1, 1, 30, 'E',
                "Armored", "Effective against armored units"));
        pool.put("iSword", new Item("Iron Sword", "Sword", true, 5, 95,
                0, 5, 1, 1, 40, 'D',
                "", ""));
        pool.put("iBlade", new Item("Iron Blade", "Sword", true, 9, 70,
                0, 12, 1, 1, 35, 'D',
                "", ""));
        pool.put("armorslayer", new Item("Armorslayer", "Sword", true, 8, 80,
                0, 11, 1, 1, 18, 'D',
                "Armored", "Effective against armored units"));
        pool.put("lSword", new Item("Long Sword", "Sword", true, 6, 85,
                0, 11, 1, 1, 18, 'D',
                "Mounted", "Effective against mounted units"));
        pool.put("woDao", new Item("Wo Dao", "Sword", true, 8, 75,
                35, 5, 1, 1, 20, 'D',
                "", "Only usable by Myrmidons and Swordmasters"));
        pool.put("pSword", new Item("Poison Sword", "Sword", true, 3, 70,
                0, 6, 1, 1, 40, 'D',
                "", "Inflicts poison on contact, regardless of damage dealt"));
        pool.put("sBlade", new Item("Steel Blade", "Sword", true, 11, 65,
                0, 14, 1, 1, 25, 'C',
                "", ""));
        pool.put("sSword", new Item("Steel Sword", "Sword", true, 8, 75,
                0, 14, 1, 1, 30, 'C',
                "", ""));
        pool.put("wyrmslayer", new Item("Wyrmslayer", "Sword", true, 7, 75,
                0, 5, 1, 1, 20, 'C',
                "Dragon", "Effective against dragons"));
        pool.put("kEdge", new Item("Killing Edge", "Sword", true, 9, 75,
                30, 7, 1, 1, 20, 'C',
                "", ""));
        special = new Item("Lancereaver", "Sword", true, 9, 75,
                5, 9, 1, 1, 15, 'C',
                "", "Reverses weapon triangle");
        special.setInverted();
        pool.put("lancereaver", special);
        //Check weapon type for Light Brand
        pool.put("lBrand", new Item("Light Brand", "Sword", false, 9, 70,
                0, 9, 1, 2, 25, 'C',
                "", "Attacks unit's resistance. Treated as Light magic"));
        pool.put("sBlade", new Item("Silver Blade", "Sword", true, 14, 60,
                0, 13, 1, 1, 15, 'B',
                "", ""));
        pool.put("wSword", new Item("Wind Sword", "Sword", false, 9, 70,
                0, 9, 1, 2, 40, 'B',
                "", "Attacks unit's resistance. Treated as Anima magic"));
        pool.put("sSword", new Item("Silver Sword", "Sword", true, 13, 80,
                0, 8, 1, 1, 20, 'B',
                "", ""));
        pool.put("bSword", new Item("Brave Sword", "Sword", true, 9, 75,
                0, 12, 1, 1, 30, 'A',
                "", "Allows two consecutive attacks"));
        pool.put("rSword", new Item("Rune Sword", "Sword", false, 12, 65,
                0, 11, 1, 2, 15, 'A',
                "", "Attacks unit's resistance. Treated as Dark magic, and damage dealt is added to attacker's HP."));
    }

    public Item getItem(String item) throws CloneNotSupportedException {
        Item res = pool.get(item);
        return res.clone();
    }
}

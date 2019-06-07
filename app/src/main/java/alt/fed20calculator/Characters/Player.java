package alt.fed20calculator.Characters;

import java.util.ArrayList;
import java.util.HashMap;

import alt.fed20calculator.Items.Item;

/**
 * Defines a playable character that shall fight in the army.
 */
public class Player{
    private static final String TAG = "Player";
    private String name, job, affinity;
    private String[] weakness = {"", ""};
    private boolean alive = true;
    private int level = 1, exp = 0;
    private int hp, con, mov;
    private int str, mag, spd, skl, lck, def, res;
    private char
            sword = 'E',
            lance = 'E',
            axe = 'E',
            bow = 'E',
            dagger = 'E',
            anima = 'E',
            light = 'E',
            dark = 'E',
            staff = 'E';

    private ArrayList<Item> inventory;
    private ArrayList<String> skills;
    private char hpG, strG, magG, spdG, sklG, lckG, defG, resG;
    private int swordExp, lanceExp, axeExp, bowExp, daggerExp, animaExp, lightExp, darkExp, staffExp;

    /**
     * Gets the character's data necessary to perform a battle.
     * @param wIndex Index pointing to the equipped weapon.
     * @return Character's data containing speed, hit rate, evasion, attack, weapon might, crit chance, evasion, def, res, weapon type and weapon's effectiveness.
     */
    public HashMap<String, String> getBattle(int wIndex){
        HashMap<String, String> weapon = inventory.get(wIndex).getItem();
        HashMap<String, String> result = new HashMap<>();
        //Speed
        int weight = Integer.parseInt(weapon.get("weight"));
        int speed;
        if(weight > con) speed = spd + (con - weight);
        else speed = spd;
        result.put("speed", String.valueOf(speed));
        //Hit
        result.put("hit", String.valueOf(((2 * skl) + (0.5 * lck) + Integer.parseInt(weapon.get("hit")) + aHit(weapon.get("type")))));
        //Attack. Since weapon damage can be triple if effective it must be separated from unit's attack.
        if(Boolean.parseBoolean(weapon.get("dType"))) result.put("attack", String.valueOf(str + aAttack(weapon.get("type"))));
        else result.put("attack", String.valueOf(mag + aAttack(weapon.get("type"))));
        result.put("might", weapon.get("might"));
        //Critical
        result.put("critical", String.valueOf(Integer.parseInt(weapon.get("critical")) + (0.5 * skl)));
        //Evasion
        result.put("evasion", String.valueOf((2 * speed) + lck));
        //Def and Res
        result.put("def", String.valueOf(def));
        result.put("res", String.valueOf(res));
        //Type
        result.put("Type", weapon.get("Type"));
        //Effective
        result.put("Effective", weapon.get("Effective"));

        return result;
    }

    private int aHit(String type) {
        switch (type) {
            case "Sword":
                switch (sword) {
                    case 'S':
                        return 5;
                }
                break;
            case "Axe":
                switch (lance) {
                    case 'C':
                        return 5;
                    case 'B':
                    case 'A':
                        return 10;
                    case 'S':
                        return 15;
                }
                break;
            case "Lance":
                switch (lance) {
                    case 'B':
                    case 'A':
                        return 5;
                    case 'S':
                        return 10;
                }
                break;
            case "Dagger":
                switch (dagger) {
                    case 'S':
                        return 5;
                }
                break;
            case "Bow":
                switch (bow) {
                    case 'B':
                    case 'A':
                        return 5;
                    case 'S':
                        return 10;
                }
                break;
            case "Fire":
            case "Thunder":
            case "Wind":
                switch (anima) {
                    case 'B':
                    case 'A':
                        return 5;
                    case 'S':
                        return 10;
                }
                break;
            case "Dark":
                switch (dark) {
                    case 'B':
                    case 'A':
                        return 5;
                    case 'S':
                        return 10;
                }
                break;
            case "Light":
                switch (light) {
                    case 'B':
                    case 'A':
                        return 5;
                    case 'S':
                        return 10;
                }
                break;
        }
        return 0;
    }

    private int aAttack(String type) {
        switch (type) {
            case "Sword":
                switch (sword) {
                    case 'C':
                        return 1;
                    case 'B':
                        return 2;
                    case 'A':
                        return 3;
                    case 'S':
                        return 4;
                }
                break;
            case "Axe":
                switch (lance) {
                    case 'A':
                        return 1;
                    case 'S':
                        return 2;
                }
                break;
            case "Lance":
            switch (lance) {
                case 'C':
                case 'B':
                    return 1;
                case 'A':
                    return 2;
                case 'S':
                    return 3;
                }
            break;
            case "Dagger":
                switch (dagger) {
                    case 'C':
                        return 1;
                    case 'B':
                        return 2;
                    case 'A':
                        return 3;
                    case 'S':
                        return 4;
                }
                break;
            case "Bow":
                switch (bow) {
                    case 'C':
                    case 'B':
                        return 1;
                    case 'A':
                        return 2;
                    case 'S':
                        return 3;
                }
                break;
            case "Fire":
            case "Thunder":
            case "Wind":
                switch (anima) {
                    case 'C':
                    case 'B':
                        return 1;
                    case 'A':
                        return 2;
                    case 'S':
                        return 3;
                }
                break;
            case "Dark":
                switch (dark) {
                    case 'C':
                    case 'B':
                        return 1;
                    case 'A':
                        return 2;
                    case 'S':
                        return 3;
                }
                break;
            case "Light":
                switch (light) {
                    case 'C':
                    case 'B':
                        return 1;
                    case 'A':
                        return 2;
                    case 'S':
                        return 3;
                }
                break;
        }
        return 0;
    }

    /**
     * Calculates the amount of exp gained from a battle when hitting the enemy.
     * @param eLevel Enemy's level.
     * @return Unit's total exp after the battle.
     */
    public int hitExp(int eLevel){
        int jValue = 3;
        if(job.equals("Soldier") || job.equals("Priest") || job.equals("Thief")) jValue = 2;
        int rewardedExp = (31 - (level - eLevel))/jValue;
        levelUp(rewardedExp);
        return exp;
    }

    /**
     * Calculates the amount of exp gained from a victory in battle.
     * @param eLevel Enemy's level.
     * @param eJob Enemy's class
     * @return Unit's total exp after the battle.
     */
    public int killExp(int eLevel, String eJob){
        int jValue = 3, eValue = 3;
        if(job.equals("Soldier") || job.equals("Priest") || job.equals("Thief")) jValue = 2;
        if(eJob.equals("Soldier") || eJob.equals("Priest") || eJob.equals("Thief")) eValue = 2;
        int rewardedExp = 30 + (eValue * eLevel) - (jValue * level);
        levelUp(rewardedExp);
        return exp;
    }

    /**
     * Procedure to add a fixed amount of exp from certain actions like using a staff or missing in battle.
     * @return Unit's total exp after the battle
     */
    public int otherExp(int expGained){
        levelUp(expGained);
        return exp;
    }

    /**
     * Adds the gained exp to the total counter and compares if the character has leveled up ans performs the stats growth if so.
     * @param rewardedExp Exp gained by the character.
     */
    private void levelUp(int rewardedExp){
        if(rewardedExp < 1) rewardedExp = 1;
        else if(rewardedExp > 100) rewardedExp = 100;
        exp += rewardedExp;
        if(exp >= 100) {
            exp -= 100;
            //Tirar aleatoriedad para subir stats.
        }
    }

    /**
     * Adds an item to the character's inventory.
     * @param item Item to add to the inventory.
     * @return True if the item could be saved in the character's inventory, false if its full.
     */
    public boolean setInventory(Item item){
        boolean success = false;
        if(inventory.size() < 5){
            inventory.add(item);
            success = true;
        }
        return success;
    }




}

package alt.fed20calculator.Characters;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import alt.fed20calculator.Items.Item;
import alt.fed20calculator.Items.Weapon;

/**
 * Defines a playable character that shall fight in the army.
 */
final public class Player{
    private static final String TAG = "Player";
    private Job job;
    private String name, affinity;
    private boolean alive = true;
    private int level = 1, exp = 0;
    private int maxHp = 0, currHp, mov;
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
    private int swordExp = 0, lanceExp = 0, axeExp = 0, bowExp = 0, daggerExp = 0, animaExp = 0,
            lightExp = 0, darkExp = 0, staffExp = 0;

    private Player(Builder builder, JSONObject jobObj) throws JSONException {
        //Value assignation
        name = builder.getName();
        affinity = builder.getAffinity();

        str = builder.getStr();
        mag = builder.getMag();
        spd = builder.getSpd();
        skl = builder.getSkl();
        lck = builder.getLck();
        def = builder.getDef();
        res = builder.getRes();

        hpG = builder.getHpG();
        strG = builder.getStrG();
        magG = builder.getMagG();
        spdG = builder.getSpdG();
        sklG = builder.getSklG();
        lckG = builder.getLckG();
        defG = builder.getDefG();
        resG = builder.getResG();

        job = new Job(builder.getJob(), jobObj, builder.getHp(), builder.getConstitution());
        for (String item : job.getsInv()) {
            //Add items to inventory
        }
    }

    /**
     * Gets the character's data necessary to perform a battle.
     * @param wIndex Index pointing to the equipped weapon.
     * @return Character's data containing speed, hit rate, evasion, attack, weapon might, crit chance, evasion, def, res, weapon type and weapon's effectiveness.
     */
    public HashMap<String, String> getBattle(int wIndex){
        Weapon eWeapon = (Weapon) inventory.get(wIndex);
        HashMap<String, String> battleData = new HashMap<>();
        //Speed
        int weight = Integer.parseInt(String.valueOf(eWeapon.getWeight()));
        int speed;
        if(weight > job.getConstitution()) speed = spd + (job.getConstitution() - weight);
        else speed = spd;
        battleData.put("speed", String.valueOf(speed));
        //Hit
        battleData.put("hit", String.valueOf(((2 * skl) + (0.5 * lck) + Integer.parseInt(String.valueOf(eWeapon.getHit())) + aHit(eWeapon.getType()))));
        //Attack. Since weapon damage can be triple if effective it must be separated from unit's attack.
        if(Boolean.parseBoolean(String.valueOf(eWeapon.isdType()))) battleData.put("attack", String.valueOf(str + aAttack(eWeapon.getType())));
        else battleData.put("attack", String.valueOf(mag + aAttack(eWeapon.getType())));
        battleData.put("might", String.valueOf(eWeapon.getMight()));
        //Critical
        battleData.put("critical", String.valueOf(Integer.parseInt(String.valueOf(eWeapon.getCritical())) + (0.5 * skl)));
        //Evasion
        battleData.put("evasion", String.valueOf((2 * speed) + lck));
        //Def and Res
        battleData.put("def", String.valueOf(def));
        battleData.put("res", String.valueOf(res));
        //Type
        battleData.put("type", eWeapon.getType());
        //Effective
        battleData.put("effective", eWeapon.getEffective());

        return battleData;
    }

    /**
     * Calculates hit bonuses depending on the weapon proficiency.
     * @param type Type of weapon used.
     * @return Hit bonus given by character's weapon proficiency.
     */
    private int aHit(String type) {
        switch (type) {
            case "Sword":
                if (sword == 'S') {
                    return 5;
                }
                break;
            case "Axe":
                switch (axe) {
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
                if (dagger == 'S') {
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

    /**
     * Calculates attack bonuses depending on the weapon proficiency.
     * @param type Type of weapon used.
     * @return Attack bonus given by character's weapon proficiency.
     */
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
                switch (axe) {
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
        if(job.getJobName().equals("Soldier") || job.getJobName().equals("Priest") || job.getJobName().equals("Thief")) jValue = 2;
        int rewardedExp = (31 - (level - eLevel))/jValue;
        levelUp(rewardedExp);
        return exp;
    }

    /**
     * Calculates the amount of exp gained from a victory in battle.
     * @param eLevel Enemy's level.
     * @param eJob Enemy's class name.
     * @return Unit's total exp after the battle.
     */
    public int killExp(int eLevel, String eJob){
        int jValue = 3, eValue = 3;
        if(job.getJobName().equals("Soldier") || job.getJobName().equals("Priest") || job.getJobName().equals("Thief")) jValue = 2;
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
            //Roll to improve stats.
        }
    }

    /**
     * Sets character's HP to max. Should be done at the beelining of a battle.
     */
    public void resetHp(){
        currHp = maxHp;
    }

    /**
     * When a character is hit or healed this method should be called to reflect HP change.
     * @param damage Amount of HP that shall be subtracted from character's health. Must be negative if healing.
     * @return Remaining HP.
     */
    public int damageReceived(int damage){
        currHp -= damage;
        if(currHp > maxHp){
            currHp = maxHp;
        }else if(currHp <= 0){
            //Character dies
            currHp = 0;
            alive = false;
        }
        return currHp;
    }

    /**
     * Similar to damageReceived, but this method prevents a character from dying. Should be used for environmental damage.
     * @param damage Amount of HP that shall be subtracted from character's health. Must be negative if healing.
     * @return Remaining HP.
     */
    public int enviromentalDamage(int damage){
        currHp -= damage;
        if(currHp <= 0) currHp = 1;
        return currHp;
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

    /**
     * Builder class for Player instances.
     */
    public static class Builder{
        private String name, affinity, job;
        private int hp, constitution;
        private int str, mag, spd, skl, lck, def, res;
        private char hpG, strG, magG, spdG, sklG, lckG, defG, resG;
        //Instance fields
        public static Builder newInstance(){
            return new Builder();
        }

        private Builder(){}

        /**
         * Sets primary properties for the character.
         * @param name Character's name.
         * @param affinity Character's affinity.
         * @param job Character's class.
         * @return Builder object
         */
        public Builder setName(String name, String affinity, String job){
            this.name = name;
            this.affinity = affinity;
            this.job = job;
            return this;
        }

        public Builder setJobRNGs(int hp, int constitution){
            this.hp = hp;
            this.constitution = constitution;
            return this;
        }

        /**
         * Sets initial stats for the character.
         * @param str Strength
         * @param mag Magic
         * @param spd Speed
         * @param skl Skill
         * @param lck Luck
         * @param def Defense
         * @param res Resistance
         * @return Builder object
         */
        public Builder setStats(int str, int mag, int spd, int skl, int lck, int def, int res){
            this.str = str;
            this.mag = mag;
            this.spd = spd;
            this.skl = skl;
            this.lck = lck;
            this.def = def;
            this.res = res;
            return this;
        }

        /**
         * Sets growths for each stat that can increment in level ups.
         * @param hpG HP increment
         * @param strG Strength increment
         * @param magG Magic increment
         * @param spdG Speed increment
         * @param sklG Skill increment
         * @param lckG Luck increment
         * @param defG Defense increment
         * @param resG Luck increment
         * @return Builder object
         */
        public Builder setGrowths(char hpG, char strG, char magG, char spdG, char sklG, char lckG, char defG, char resG) {
            this.hpG = hpG;
            this.strG = strG;
            this.magG = magG;
            this.spdG = spdG;
            this.sklG = sklG;
            this.lckG = lckG;
            this.defG = defG;
            this.resG = resG;

            return this;
        }

        /**
         * Builds player object
         * @return Player instance
         */
        public Player build(JSONObject jobObj) throws JSONException {
            return new Player(this, jobObj);
        }

        public String getName() {
            return name;
        }

        public int getHp() { return hp; }

        public int getConstitution() { return constitution; }

        String getAffinity() {
            return affinity;
        }

        String getJob() {
            return job;
        }

        int getStr() {
            return str;
        }

        int getMag() {
            return mag;
        }

        int getSpd() {
            return spd;
        }

        int getSkl() {
            return skl;
        }

        int getLck() {
            return lck;
        }

        int getDef() {
            return def;
        }

        int getRes() {
            return res;
        }

        char getHpG() {
            return hpG;
        }

        char getStrG() {
            return strG;
        }

        char getMagG() {
            return magG;
        }

        char getSpdG() {
            return spdG;
        }

        char getSklG() {
            return sklG;
        }

        char getLckG() {
            return lckG;
        }

        char getDefG() {
            return defG;
        }

        char getResG() {
            return resG;
        }
    }

}

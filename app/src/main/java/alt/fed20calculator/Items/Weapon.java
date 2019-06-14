package alt.fed20calculator.Items;

import android.util.Log;

import java.util.HashMap;

public class Weapon extends Item {

    private boolean dType, inverted;
    private int might, hit, critical, minRange = 0, maxRange = 0, weight;
    private String effective, type, triangle;
    private char rank;

    public Weapon(Builder builder) {
        super(builder.getName(), builder.getDurability(), builder.getEffect());
        dType = builder.isdType();
        inverted = builder.isInverted();
        might = builder.getMight();
        hit = builder.getHit();
        critical = builder.getCritical();
        effective = builder.getEffective();
        type = builder.getType();
        triangle = builder.getTriangle();
        minRange = builder.getMinRange();
        maxRange = builder.getMaxRange();
        weight = builder.getWeight();
        rank = builder.getRank();
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

    public boolean isdType() {
        return dType;
    }

    public String getEffective() {
        return effective;
    }

    public String getType() {
        return type;
    }

    public String getTriangle() { return triangle; }

    public int getMight() {
        return might;
    }

    public int getHit() {
        return hit;
    }

    public int getCritical() {
        return critical;
    }

    public int getWeight() {
        return weight;
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

    public static class Builder {
        private int durability, might, hit, critical, minRange, maxRange, weight;
        private String name, effective, type, triangle, effect;
        private char rank;
        private boolean dType, inverted;

        public static Builder newInstance(){
            return new Builder();
        }

        private Builder(){}

        public Builder basics(String name, char rank, String type, String triangle){
            this.name = name;
            this.rank = rank;
            this.type = type;
            this.triangle = triangle;
            return this;
        }

        //Properties definition pending

        public int getDurability() {
            return durability;
        }

        public int getMight() {
            return might;
        }

        public int getHit() {
            return hit;
        }

        public int getCritical() {
            return critical;
        }

        public int getMinRange() { return minRange; }

        public int getMaxRange() { return maxRange; }

        public int getWeight() {
            return weight;
        }

        public String getName() {
            return name;
        }

        public String getEffective() {
            return effective;
        }

        public String getType() {
            return type;
        }

        public String getTriangle() {
            return triangle;
        }

        public String getEffect() {
            return effect;
        }

        public char getRank() {
            return rank;
        }

        public boolean isdType() {
            return dType;
        }

        public boolean isInverted() {
            return inverted;
        }
    }
}

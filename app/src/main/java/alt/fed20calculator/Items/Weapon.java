package alt.fed20calculator.Items;

import android.util.Log;

import java.util.HashMap;

public class Weapon extends Item {

    boolean dType, inverted = false;
    int might = 0, hit = 0, critical = 0;
    String effective, type;

    Weapon() {
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

    public int getMight() {
        return might;
    }

    public int getHit() {
        return hit;
    }

    public int getCritical() {
        return critical;
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

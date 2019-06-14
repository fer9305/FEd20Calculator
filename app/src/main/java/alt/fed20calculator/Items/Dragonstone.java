package alt.fed20calculator.Items;

import android.util.Log;

public class Dragonstone extends Weapon {
    private int[] bonuses;

    /**
     * Creates a new Dragonstone weapon.
     * @param builder Weapon builder.
     * @param bonuses Stats that increment thanks to the stone's power.
     */
    public Dragonstone(Weapon.Builder builder, int[] bonuses) {
        super(builder);
        this.bonuses = bonuses;
        Log.d(TAG, "Dragonstone " + builder.getName() + " created.");
    }

    public int[] getBonuses() {
        return bonuses;
    }
}

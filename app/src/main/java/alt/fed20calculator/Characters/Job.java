package alt.fed20calculator.Characters;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Job extends AppCompatActivity {
    private boolean sword = false, lance = false,axe = false, staff = false,
            light = false, dark = false, anima = false, dagger = false;

    public Job(String job){
        String json;
        int jNo = 0;
        try {
            InputStream is = getAssets().open("Jobs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read();
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            JSONObject obj = jsonArray.getJSONObject(jNo);
            JSONArray wProficiencies = obj.getJSONArray("wProficiency");
            ArrayList<String> wProficiency = new ArrayList<>();
            for (int i = 0; i < wProficiencies.length(); i++) {
                wProficiency.add(wProficiencies.getString(i));
            }
            assignUsableWeapons(wProficiency);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    private void assignUsableWeapons(ArrayList<String> weapons) {
        for (String weapon : weapons) {
            switch (weapon){
                case "sword":
                    sword = true;
                    break;
                case "lance":
                    lance = true;
                    break;
                case "axe":
                    axe = true;
                    break;
                case "staff":
                    staff = true;
                    break;
                case "light":
                    light = true;
                    break;
                case "dark":
                    dark = true;
                    break;
                case "anima":
                    anima = true;
                    break;
                case "dagger":
                    dagger = true;
                    break;
            }
        }
    }

    public boolean isSword() {
        return sword;
    }

    public boolean isLance() {
        return lance;
    }

    public boolean isAxe() {
        return axe;
    }

    public boolean isStaff() {
        return staff;
    }

    public boolean isLight() {
        return light;
    }

    public boolean isDark() {
        return dark;
    }

    public boolean isAnima() {
        return anima;
    }

    public boolean isDagger() {
        return dagger;
    }
}

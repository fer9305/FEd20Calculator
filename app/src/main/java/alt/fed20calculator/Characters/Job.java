package alt.fed20calculator.Characters;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Job extends AppCompatActivity {
    private String jobName, mType;
    private String[] weakness = {"", ""};
    private boolean sword = false, lance = false,axe = false, staff = false,
            light = false, dark = false, anima = false, dagger = false;
    private ArrayList<String> sInv = new ArrayList<>();
    private int hp, constitution, movement;

    public Job(String job, JSONObject jobObj, int hp, int constitution) throws JSONException {
        //Name
        jobName = jobObj.getString("name");
        //HP, Constitution and Movement
        this.hp = hp;
        this.constitution = constitution;
        movement = jobObj.getInt("movement");
        mType = jobObj.getString("mType");
        //Weapon proficiencies
        JSONArray wProficiencies = jobObj.getJSONArray("wProficiency");
        ArrayList<String> wProficiency = new ArrayList<>();
        if(job.equals("Lord")){
            //Lord can choose their proficiency as any of the three physical weapons
            wProficiency.add(lordWP("Sword"));
        }else{
            for (int i = 0; i < wProficiencies.length(); i++) {
                wProficiency.add(wProficiencies.getString(i));
            }
        }
        assignUsableWeapons(wProficiency);
        //Starting inventory
        JSONArray inventory = jobObj.getJSONArray("inventory");
        for(int i = 0; i < inventory.length(); i++){
            sInv.add(inventory.getString(i));
        }
        //Weaknesses
        if (jobObj.has("weaknesses")){
            JSONArray weaknesses = jobObj.getJSONArray("weaknesses");
            for(int i = 0; i < 2; i++){
                weakness[i] = weaknesses.getString(i);
            }
        }

    }

    private String lordWP(String weapon) {
        //Scan for weapon proficiency
        return weapon;
    }

    public JSONObject getJSONJob(String rank, int jNo){
        String json;
        try {
            InputStream is = getAssets().open("Jobs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read();
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray;
            switch (rank) {
                case "unpromoted":
                    jsonArray = obj.getJSONArray("unpromoted");
                    break;
                case "promoted":
                    jsonArray = obj.getJSONArray("promoted");
                    break;
                default:
                    jsonArray = obj.getJSONArray("trainee");
                    break;
            }
            return jsonArray.getJSONObject(jNo);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
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

    public String getJobName() {
        return jobName;
    }

    public ArrayList<String> getsInv() {
        return sInv;
    }

    public String[] getWeakness() {
        return weakness;
    }

    public int getHp() {
        return hp;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getMovement() {
        return movement;
    }

    public String getmType() {
        return mType;
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

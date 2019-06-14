package alt.fed20calculator.Characters;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Job extends AppCompatActivity {
    private String jobName;
    private String[] weakness = {"", ""};
    private boolean sword = false, lance = false,axe = false, staff = false,
            light = false, dark = false, anima = false, dagger = false;
    private ArrayList<String> sInv = new ArrayList<>();
    private int hp = 0, constitution, movement;

    public Job(String job, String rank){
        String json;
        int jNo = 0;
        try {
            InputStream is = getAssets().open("Jobs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read();
            is.close();

            json = new String(buffer, "UTF-8");
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray;
            switch (rank){
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

            JSONObject jobObj = jsonArray.getJSONObject(jNo);
            //Name
            jobName = jobObj.getString("name");
            //HP, Constitution and movement
            hp = rngSetter(jobObj.getString("hp"));
            constitution = rngSetter(jobObj.getString("const"));
            movement = jobObj.getInt("movement");
            //Weapon proficiencies
            JSONArray wProficiencies = jobObj.getJSONArray("wProficiency");
            ArrayList<String> wProficiency = new ArrayList<>();
            if(job.equals("Lord")){
                //Lord can choose their proficiency as any of the three physical weapons
                wProficiency.add(lordWP());
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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    private int rngSetter(String roll) {
        //Define dice roll from input
        return 10;
    }

    private String lordWP() {
        //Scan for weapon proficiency
        return "Sword";
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

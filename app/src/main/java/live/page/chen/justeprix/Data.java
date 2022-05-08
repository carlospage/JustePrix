package live.page.chen.justeprix;

import android.content.Context;
import android.content.SharedPreferences;

public class Data {
    private static final String PREFS_NAME = "best_score";

    /**
     * @param context de l'appli
     * @param value   valeur à sauvegarder
     */
    public static void setBest(Context context, int value) {
        SharedPreferences best = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = best.edit();
        editor.putInt("best", value);
        editor.apply();
    }

    /**
     * @param context de l'appli
     * @param def     valeur par défault
     * @return la valeur sauvegardée
     */
    public static int getBest(Context context, int def) {
        SharedPreferences best = context.getSharedPreferences(PREFS_NAME, 0);
        return best.getInt("best", def);
    }
}

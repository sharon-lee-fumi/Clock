package pointclickcare.lish.clock.util;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class UiUtil {
    public static void switchFragment(FragmentManager fragmentManager, @IdRes int placeholderId, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(placeholderId, fragment, null);
        transaction.commit();
    }
}

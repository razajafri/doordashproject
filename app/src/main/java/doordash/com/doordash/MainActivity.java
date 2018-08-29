package doordash.com.doordash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.doordash.ui.RestaurantFragment;

public class MainActivity extends AppCompatActivity {
    private static final String FRAG_TAG_REST = "restaurant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    private void initFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment restaurantsFragment = fragmentManager.findFragmentByTag(FRAG_TAG_REST);
        if (restaurantsFragment == null) {
            restaurantsFragment = new RestaurantFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_content_frame, restaurantsFragment, FRAG_TAG_REST).commit();
        }
    }

}

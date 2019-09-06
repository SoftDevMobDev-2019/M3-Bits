package au.edu.swin.sdmd.bits;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
This app is an example of how fragments can be used. One fragment has been created already for
later use, but first we will look at a basic text-only fragment hosted in the layout file for an
activity, before attempting to add fragments (such as BinaryFragment) programatically. Finally, we
will move the existing EditText and Button into its own Fragment and see how fragments can
communicate.

@author nronald
@date August 2018, updated September 2019
 */


public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fInput = new InputFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_number, fInput).commit();
    }

    @Override
    public void onFragmentInteraction(String s) {
        Fragment fBinary = new BinaryFragment();
        Bundle b = new Bundle();
        b.putString("decimal", s);
        fBinary.setArguments(b);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_output, fBinary).commit();

    }
}
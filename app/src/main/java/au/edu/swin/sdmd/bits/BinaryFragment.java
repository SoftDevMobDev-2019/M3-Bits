package au.edu.swin.sdmd.bits;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BinaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BinaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String INPUT = "decimal";
    private String input;


    public BinaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param input Parameter 1.
     * @return A new instance of fragment BinaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BinaryFragment newInstance(String input) {
        BinaryFragment fragment = new BinaryFragment();
        Bundle args = new Bundle();
        args.putString(INPUT, input);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            input = getArguments().getString(INPUT);
        }
    }

    /** This function initialises the fragment UI. The string passed in with the bundle will be
     * converted to binary and displayed in the fragment.
     */
    private void initialiseUI(View v) {
        try {
            int iDecimal = Integer.parseInt(input);
            String sBinary = Integer.toString(iDecimal, 2);
            TextView tvBinary = v.findViewById(R.id.tvBinary);
            tvBinary.setText(sBinary);
        } catch (NumberFormatException nfe) {
            TextView tvBinary = v.findViewById(R.id.tvBinary);
            tvBinary.setText(R.string.binary);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_binary, container, false);
        initialiseUI(v);
        return v;
    }

}

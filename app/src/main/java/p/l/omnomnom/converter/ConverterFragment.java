package p.l.omnomnom.converter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import p.l.omnomnom.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConverterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText ET_from;
    private EditText ET_to;
    private RadioGroup RG_Type;
    private RadioButton selectedRB;
    private Button Bttn_Convert;
    private Spinner Spin_from;
    private Spinner Spin_to;
    private ArrayAdapter<Measure> adapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<Measure> drawerList;
    private RadioButton RB_Weight;


    public ConverterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConverterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConverterFragment newInstance(String param1, String param2) {
        ConverterFragment fragment = new ConverterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_converter, container, false);
        ET_from = v.findViewById(R.id.editTextFrom);
        ET_to = v.findViewById(R.id.editTextTo);
        Bttn_Convert = v.findViewById(R.id.ConvertButton);
        RG_Type = v.findViewById(R.id.RG_typr);
        RB_Weight = v.findViewById(R.id.weightRb);
        Spin_from = v.findViewById(R.id.spinnerFrom);
        Spin_to = v.findViewById(R.id.spinnerTo);
        drawerList = new ArrayList<>();
        ET_from.setText("0");
        ET_to.setText("0");
        selectedRB = v.findViewById(RG_Type.getCheckedRadioButtonId());
        setDrawers();
        RG_Type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedRB = v.findViewById(radioGroup.getCheckedRadioButtonId());
                setDrawers();
            }
        });
        Bttn_Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double answer = 0;
                if (ET_from.hasFocus()) {
                    Measure from = (Measure) Spin_from.getSelectedItem();
                    Measure to = (Measure) Spin_to.getSelectedItem();
                    answer = (from.getPrzelicznik() * Double.parseDouble(ET_from.getText().toString())) / to.getPrzelicznik();
                    ET_to.setText(String.format(Locale.UK, "%.5f", answer)); //todo: ogranicz dziesietne
                }
                if (ET_to.hasFocus()) {
                    Measure from = (Measure) Spin_to.getSelectedItem();
                    Measure to = (Measure) Spin_from.getSelectedItem();
                    answer = (from.getPrzelicznik() * Double.parseDouble(ET_to.getText().toString())) / to.getPrzelicznik();
                    ET_from.setText(String.format(Locale.UK, "%.5f", answer)); //todo: ogranicz dziesietne
                }
            }
        });
        return v;
    }

    private void setDrawers() {
        if (selectedRB.getId() == RB_Weight.getId()) {
            drawerList = Measure.getWeightList();
        } else {
            drawerList = Measure.getVelocityList();
        }
        adapter = new ArrayAdapter<Measure>(this.getContext(), android.R.layout.simple_spinner_item, drawerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_from.setAdapter(adapter);
        Spin_to.setAdapter(adapter);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

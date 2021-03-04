package com.example.myapplication.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.detail_product.DetailProductFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemoUIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoUIFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FrameLayout btnClickBus;
    ImageView imgClickBusContent;
    int x=1;

    public DemoUIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemoUIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DemoUIFragment newInstance(String param1, String param2) {
        DemoUIFragment fragment = new DemoUIFragment();
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
        View view= inflater.inflate(R.layout.fragment_demo_u_i, container, false);
        btnClickBus = view.findViewById(R.id.buttonClickBus);
        imgClickBusContent = view.findViewById(R.id.buttonClickBusContent);

        btnClickBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x==1) {
                    btnClickBus.setBackground(null);
                    imgClickBusContent.setImageResource(R.drawable.ic_bus_selected);
                    Toast.makeText(getContext(), "Hello anh em", Toast.LENGTH_SHORT).show();
                    x=0;
                    Fragment fragment = new DetailProductFragment();
                    DetailProductFragment.getInstance().setChangeText("QUang Huy");
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else
                {
                    btnClickBus.setBackgroundResource(R.drawable.ic_action_bus);
                    imgClickBusContent.setImageResource(R.drawable.ic_bus);
                    Toast.makeText(getContext(), "Hello anh em", Toast.LENGTH_SHORT).show();
                    x=1;
                }
            }
        });
        return view;
    }
}
package com.app.apekade.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.apekade.Adapters.CartAdapter;
import com.app.apekade.Model.CartItem;
import com.app.apekade.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.carts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("1", "P001", "https://th.bing.com/th/id/R.2215cdaf3928ad0f469bd5ce815724c9?rik=ZP1%2faG70vnemew&riu=http%3a%2f%2fupload.wikimedia.org%2fwikipedia%2fcommons%2f6%2f6d%2fStatue_of_Liberty_approach.jpg&ehk=XfiPTbJpygWfqGLnGNZ7lTGm8InAuxzu0l%2buhQ98Hzw%3d&risl=1&pid=ImgRaw&r=0", 1, 15.00));
        cartItemList.add(new CartItem("2", "P002", "https://th.bing.com/th/id/R.2215cdaf3928ad0f469bd5ce815724c9?rik=ZP1%2faG70vnemew&riu=http%3a%2f%2fupload.wikimedia.org%2fwikipedia%2fcommons%2f6%2f6d%2fStatue_of_Liberty_approach.jpg&ehk=XfiPTbJpygWfqGLnGNZ7lTGm8InAuxzu0l%2buhQ98Hzw%3d&risl=1&pid=ImgRaw&r=0", 2, 20.00));
        cartItemList.add(new CartItem("3", "P003", "https://th.bing.com/th/id/R.2215cdaf3928ad0f469bd5ce815724c9?rik=ZP1%2faG70vnemew&riu=http%3a%2f%2fupload.wikimedia.org%2fwikipedia%2fcommons%2f6%2f6d%2fStatue_of_Liberty_approach.jpg&ehk=XfiPTbJpygWfqGLnGNZ7lTGm8InAuxzu0l%2buhQ98Hzw%3d&risl=1&pid=ImgRaw&r=0", 1, 30.00));



        // Set up the adapter
        cartAdapter = new CartAdapter(cartItemList, getActivity());
        recyclerView.setAdapter(cartAdapter);

        return view;
    }
}

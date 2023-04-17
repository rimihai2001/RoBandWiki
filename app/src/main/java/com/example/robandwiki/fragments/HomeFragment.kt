package com.example.robandwiki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.robandwiki.Band
import com.example.robandwiki.MyAdapter
import com.example.robandwiki.R
import com.google.firebase.database.*

class HomeFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var bandArrayList : ArrayList<Band>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        userRecyclerview = view.findViewById(R.id.bandList)
        userRecyclerview.layoutManager = LinearLayoutManager(activity)
        userRecyclerview.setHasFixedSize(true)
        bandArrayList = arrayListOf<Band>()
        getUserData()
        return view
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("bands")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(Band::class.java)
                        bandArrayList.add(user!!)
                    }
                    userRecyclerview.adapter = MyAdapter(bandArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}

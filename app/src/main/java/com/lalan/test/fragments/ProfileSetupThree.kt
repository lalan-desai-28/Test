package com.lalan.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lalan.test.R
import com.lalan.test.adapter.InterestAdapter
import com.lalan.test.model.Data
import com.lalan.test.model.Interest
import com.lalan.test.viewmodel.EditProfileViewModel


class ProfileSetupThree(val initialData: Data, editProfileViewModel: EditProfileViewModel) :
    Fragment() {

    private lateinit var interestRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_setup_three, container, false)
        interestRecyclerView = view.findViewById(R.id.interestRecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = listOf(
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.gym),
                "Gym"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.yoga),
                "Yoga"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.cardio),
                "Cardio"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.home_workout),
                "Workout"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.cycling),
                "Cycling"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.zumba),
                "Zumba"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.diet),
                "Dieting"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.sports),
                "Sports"
            ),
            Interest(
                ContextCompat.getDrawable(requireContext(), R.drawable.running),
                "Running"
            )
        )

        interestRecyclerView.adapter = InterestAdapter(list)
        interestRecyclerView.itemAnimator = null
    }


}
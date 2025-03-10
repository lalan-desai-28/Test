package com.lalan.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.lalan.test.R
import com.lalan.test.model.Data
import com.lalan.test.viewmodel.EditProfileViewModel

class ProfileSetupOne(val initialData: Data, val editProfileViewModel: EditProfileViewModel) :
    Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var dobEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile_setup_one, container, false)

        nameEditText = view.findViewById(R.id.nameEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        dobEditText = view.findViewById(R.id.dobEditText)
        nextButton = view.findViewById(R.id.nextButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        nameEditText.setText(initialData.name)
        emailEditText.setText(initialData.email)
        dobEditText.setText(initialData.dob)

        editProfileViewModel.editProfileResult.observe(this as LifecycleOwner) { editProfileResponse ->

            if (editProfileResponse.code() == 200) {
                editProfileViewModel.viewPagerPosition.value = 1
            } else {
                Toast.makeText(
                    requireContext(),
                    "Error: ${editProfileResponse.body()?.meta?.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        nextButton.setOnClickListener {
            editProfileViewModel.editProfileScreenOne(
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                dobEditText.text.toString()
            )
        }

    }

}
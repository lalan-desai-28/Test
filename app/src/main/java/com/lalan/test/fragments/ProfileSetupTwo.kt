package com.lalan.test.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lalan.test.R
import com.lalan.test.model.Data
import com.lalan.test.viewmodel.EditProfileViewModel

class ProfileSetupTwo(val initialData: Data, editProfileViewModel : EditProfileViewModel) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_setup_two, container, false)
    }

}
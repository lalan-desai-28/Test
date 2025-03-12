package com.lalan.test.fragments

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.lalan.test.R
import com.lalan.test.model.Data
import com.lalan.test.viewmodel.EditProfileViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class ProfileSetupOne(
    val initialData: Data,
    val editProfileViewModel: EditProfileViewModel
) :
    Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var dobEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile_setup_one, container, false)

        nameEditText = view.findViewById(R.id.nameEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        dobEditText = view.findViewById(R.id.dobEditText)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        nameEditText.setText(initialData.name)
        emailEditText.setText(initialData.email)
        dobEditText.setText(initialData.dob)

        dobEditText.setOnClickListener {

            val dateBefore18 = LocalDate.now().minusYears(18).atStartOfDay()
            val calendar = Calendar.getInstance()
            calendar.set(dateBefore18.year, dateBefore18.monthValue, dateBefore18.dayOfMonth)

            val constraintsBuilder = CalendarConstraints.Builder()
                .setEnd(calendar.timeInMillis)
                .setOpenAt(calendar.timeInMillis)
                .setValidator(DateValidatorPointBackward.now()).build()

            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setCalendarConstraints(constraintsBuilder)
                    .setTitleText("Select Birth Date").build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                val selectedDate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(selection),
                    ZoneId.systemDefault()
                )

                val formattedDate =
                    "${selectedDate.monthValue}-${selectedDate.dayOfMonth}-${selectedDate.year}"
                dobEditText.setText(formattedDate)
            }
            datePicker.show(parentFragmentManager, "datepicker")
        }

    }


    fun submitData(token: String) {
        editProfileViewModel.editProfileScreenOne(
            nameEditText.text.toString(),
            emailEditText.text.toString(),
            dobEditText.text.toString(),
            token
        )

        editProfileViewModel.editProfileResult.observe(this as LifecycleOwner) { editProfileResponse ->
            if (editProfileResponse.code() == 200) {

            } else {
                Toast.makeText(
                    requireContext(),
                    "Error: ${editProfileResponse.body()?.meta?.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


}


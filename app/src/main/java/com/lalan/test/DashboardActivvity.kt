package com.lalan.test

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.lalan.test.model.Data


class DashboardActivvity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var nameTextView: TextView

    private lateinit var bioTextView: TextView

    private lateinit var interestsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_activvity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        profileImageView = findViewById(R.id.profileImageView)
        nameTextView = findViewById(R.id.nameTextView)
        bioTextView = findViewById(R.id.bioTextView)
        interestsTextView = findViewById(R.id.interestsTextView)


        val userDataObject = (intent.getSerializableExtra("data") as Data)

        Glide.with(this)
            .load(userDataObject.profile_photo)

            .into(profileImageView)


        nameTextView.text = userDataObject.name

        bioTextView.text = userDataObject.bio

        interestsTextView.text = userDataObject.interests.joinToString(" ")


    }
}
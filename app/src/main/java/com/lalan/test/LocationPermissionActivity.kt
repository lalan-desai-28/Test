package com.lalan.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lalan.test.model.Data

class LocationPermissionActivity : AppCompatActivity() {

    private lateinit var dontAllowTextview: TextView
    private lateinit var allowButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location_permission)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dontAllowTextview = findViewById(R.id.dontAllowTextview)
        allowButton = findViewById(R.id.allowButton)

        val data = intent.extras?.getSerializable("data") as Data

        dontAllowTextview.setOnClickListener {
            val setupIntent = Intent(this, ProfileSetupActivity::class.java)
            setupIntent.putExtra("data", data)
            startActivity(setupIntent)
        }

        allowButton.setOnClickListener {

        }
    }
}
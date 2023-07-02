package com.cxzcodes.riveanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import app.rive.runtime.kotlin.core.Rive
import com.cxzcodes.riveanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val stateMachinename = "Login Machine"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Rive.init(this)
        binding.email.setOnFocusChangeListener { view, b ->

            if (b) {
                binding.riveanimation.controller.setBooleanState(
                    stateMachinename,
                    "isChecking",
                    true
                )
            } else {
                binding.riveanimation.controller.setBooleanState(
                    stateMachinename,
                    "isChecking",
                    false
                )

            }
        }
        binding.password.setOnFocusChangeListener { view, b ->

            if (b) {
                binding.riveanimation.controller.setBooleanState(
                    stateMachinename,
                    "isHandsUp",
                    true
                )
            } else {
                binding.riveanimation.controller.setBooleanState(
                    stateMachinename,
                    "isHandsUp",
                    false
                )

            }
        }
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                try {
                    binding.riveanimation.controller.setNumberState(
                        stateMachinename,
                        "numLook",
                        p0!!.length.toFloat()
                    )
                } catch (e: Exception) {
                }
            }

        })
        binding.login.setOnClickListener { showanimation() }
    }

    private fun showanimation() {
        if (binding.email.text!!.isEmpty() || binding.password.text!!.isEmpty() ){
binding.riveanimation.controller.fireState(stateMachinename, "trigFail");
        }else{
            binding.riveanimation.controller.fireState(stateMachinename, "trigSuccess");

        }
     }
}
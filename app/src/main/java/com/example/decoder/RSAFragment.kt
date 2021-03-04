package com.example.decoder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.decoder.coderClasses.RSA
import com.example.decoder.databinding.FragmentRsaBinding

class RSAFragment : Fragment() {

    private var _binding: FragmentRsaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentRsaBinding.inflate(inflater, container, false)


        val encodeBtn = binding.encodeBtn

        encodeBtn.setOnClickListener {
            Toast.makeText(context, "Encrypting", Toast.LENGTH_SHORT).show()
            encrypt()
            try {
            }
            catch (e: Exception){
                Toast.makeText(context, "Ooops... Something went wrong!", Toast.LENGTH_SHORT).show()
                Log.d("FATAL", e.printStackTrace().toString())
            }

        }

        return binding.root
    }

    private fun encrypt(){
        val plainText = binding.userText.text.toString()
        if (plainText.isNotEmpty()){
            val rsa = RSA()
            var res = ""
            res = "Encrypting String: " + plainText
            res += "\nString in Bytes: " + RSA.bytesToString(plainText.toByteArray())
            //TODO: change the string for the decrypted
            res += "\nDecrypting Bytes: " + RSA.bytesToString(plainText.toByteArray())

            // encrypt
            val encrypted = rsa.encrypt(plainText.toByteArray())
            // decrypt
            val decrypted = rsa.decrypt(encrypted)


            res += "\nDecrypted String: " + decrypted.toString()
            res += "\nDecrypted String: " + String(decrypted)
            binding.rsaResultTextView.text = res
        }
    }

}
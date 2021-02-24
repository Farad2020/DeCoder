package com.example.decoder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.decoder.coderClasses.BlowfishJava
import com.example.decoder.databinding.FragmentBlowfishBinding
import com.example.decoder.databinding.FragmentMainHubBinding
import java.lang.Exception


class BlowfishFragment : Fragment() {

    private var _binding: FragmentBlowfishBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlowfishBinding.inflate(inflater, container, false)

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

        val decodeBtn = binding.decodeBtn

        decodeBtn.setOnClickListener {
            Toast.makeText(context, "Decrypting", Toast.LENGTH_SHORT).show()
            try {
                decrypt()
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
        val key = binding.blowfishKeyWord.text.toString()
        val transpositionResult = "Blowfish Result: "
        if (plainText.isNotEmpty() && key.isNotEmpty()){
            val ciphere = BlowfishJava(key)
            binding.blowfishResultTextView.text = transpositionResult + ciphere.encrypt(plainText)
        }
    }

    private fun decrypt(){
        val plainText = binding.userText.text.toString()
        val key = binding.blowfishKeyWord.text.toString()
        val transpositionResult = "Blowfish Result: "
        if (plainText.isNotEmpty() && key.isNotEmpty()){
            val ciphere = BlowfishJava(key)

            binding.blowfishResultTextView.text = transpositionResult + ciphere.decrypt(plainText)
        }
    }
//example key aabb09182736ccdd
//example text 123456abcd132536
//example encoded text d748ec383d3405f7
    companion object {}
}
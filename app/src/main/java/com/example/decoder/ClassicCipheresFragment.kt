package com.example.decoder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.decoder.coderClasses.*
import com.example.decoder.databinding.ClassicCipheresFragmentBinding
import java.lang.Exception
import java.util.*

class ClassicCipheresFragment : Fragment() {

    private var _binding: ClassicCipheresFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ClassicCipheresFragment()
    }

    private lateinit var viewModel: ClassicCipheresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ClassicCipheresFragmentBinding.inflate(inflater, container, false)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(ClassicCipheresViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun encrypt(){

        val sourceStr = binding.userText.text.toString().toLowerCase(Locale.ROOT)

        val shift = binding.userShift.text.toString()
        val caesareResult = "Caesar Result: "

        if (shift.isNotEmpty()){
            val caesar = CeaserCyphere()

            binding.caesarResultTextView.text = caesareResult + caesar.encodeCaesarCipher(sourceStr, shift.toInt())
        }
        else
        {
            binding.caesarResultTextView.text = caesareResult
        }

        val vigenereKey = binding.vigenereKeyWord.text.toString()
        val vigenereResult = "Vigenere Result: "

        if (vigenereKey.isNotEmpty()){
            binding.vigenereResultTextView.text = vigenereResult + VigenereCyphere().encodeVigenereCipher(sourceStr.toUpperCase(Locale.ROOT), vigenereKey.toUpperCase(Locale.ROOT))
        }
        else
        {
            binding.vigenereResultTextView.text = vigenereResult
        }

        // maybe pus swtich to encode playfair
        // switch for encoding without q or using j with i

        val playfairKey = binding.playfairKeyWord.text.toString()
        val playfairResult = "Playfair Result: "

        if (playfairKey.isNotEmpty()){
            binding.playfairResultTextView.text = playfairResult + PlayfairCyphere(playfairKey).encode(sourceStr)
        }
        else
        {
            binding.playfairResultTextView.text = playfairResult
        }

        val transpositionKey = binding.transpositionKeyWord.text.toString()
        val transpositionResult = "Transposition Result: "

        if (transpositionKey.isNotEmpty()){
            val tcj = TranspositionCyphereJava(transpositionKey).doEncryption(sourceStr)
            binding.transpositionResultTextView.text = transpositionResult + tcj
        }
        else
        {
            binding.transpositionResultTextView.text = transpositionResult
        }

    }

    private fun decrypt(){
        val sourceStr = binding.userText.text.toString().toLowerCase(Locale.ROOT)

        val shift = binding.userShift.text.toString()
        val caesareResult = "Caesar Result: "

        if (shift.isNotEmpty()){
            val caesar = CeaserCyphere()

            binding.caesarResultTextView.text = caesareResult + caesar.decodeCaesarCipher(sourceStr, shift.toInt())
        }
        else
        {
            binding.caesarResultTextView.text = caesareResult
        }

        val vigenereKey = binding.vigenereKeyWord.text.toString()
        val vigenereResult = "Vigenere Result: "

        if (vigenereKey.isNotEmpty()){
            binding.vigenereResultTextView.text = vigenereResult + VigenereCyphere().encodeVigenereCipher(sourceStr.toUpperCase(Locale.ROOT), vigenereKey.toUpperCase(Locale.ROOT), encrypt = false)
        }
        else
        {
            binding.vigenereResultTextView.text = vigenereResult
        }

        val playfairKey = binding.playfairKeyWord.text.toString()
        val playfairResult = "Playfair Result: "

        if (playfairKey.isNotEmpty()){
            binding.playfairResultTextView.text = playfairResult + PlayfairCyphere(playfairKey).decode(sourceStr.toUpperCase())
        }
        else
        {
            binding.playfairResultTextView.text = playfairResult
        }

        val transpositionKey = binding.transpositionKeyWord.text.toString()
        val transpositionResult = "Transposition Result: "

        if (transpositionKey.isNotEmpty()){
            val tcj = TranspositionCyphereJava(transpositionKey).doDecryption(sourceStr)
            binding.transpositionResultTextView.text = transpositionResult + tcj
        }
        else
        {
            binding.transpositionResultTextView.text = transpositionResult
        }
    }

}
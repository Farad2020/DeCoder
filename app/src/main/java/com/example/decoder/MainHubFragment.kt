package com.example.decoder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.decoder.databinding.ClassicCipheresFragmentBinding
import com.example.decoder.databinding.FragmentMainHubBinding

class MainHubFragment : Fragment() {

    private var _binding: FragmentMainHubBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainHubBinding.inflate(inflater, container, false)

        val classicCipheresBtn = binding.redirectToClassicCipheresBtn
        classicCipheresBtn.setOnClickListener {
            val action = MainHubFragmentDirections.actionMainHubFragmentToHubFragment()
            // Navigate using that action
            view?.findNavController()?.navigate(action)
        }

        val blowfishCiphereBtn = binding.redirectToBlowfishBtn
        blowfishCiphereBtn.setOnClickListener {
            val action = MainHubFragmentDirections.actionMainHubFragmentToBlowfishFragment()
            // Navigate using that action
            view?.findNavController()?.navigate(action)
        }

        return binding.root
    }

    companion object {
    }
}
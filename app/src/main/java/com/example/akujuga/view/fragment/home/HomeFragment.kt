package com.example.akujuga.view.fragment.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.FragmentHomeBinding
import com.example.akujuga.view.feature.AngkaActivity
import com.example.akujuga.view.feature.KamusActivity
import com.example.akujuga.view.feature.KataSifatActivity
import com.example.akujuga.view.feature.SapaanActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ivKamus.setOnClickListener {
            val intent = Intent(requireActivity(), KamusActivity::class.java)
            startActivity(intent)
        }

        binding.ivAngka.setOnClickListener {
            val intent = Intent(requireActivity(), AngkaActivity::class.java)
            startActivity(intent)
        }

        binding.ivKataSifat.setOnClickListener {
            val intent = Intent(requireActivity(), KataSifatActivity::class.java)
            startActivity(intent)
        }

        binding.ivSapaan.setOnClickListener {
            val intent = Intent(requireActivity(), SapaanActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
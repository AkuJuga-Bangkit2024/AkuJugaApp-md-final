package com.example.akujuga.view.guest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.akujuga.R
import com.example.akujuga.databinding.FragmentGuestProfileBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class GuestProfileFragment : Fragment() {
    private val viewModel by viewModels<GuestProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private var _binding: FragmentGuestProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestProfileBinding.inflate(inflater, container, false)


        setupAction()

        return binding.root

    }

    private fun  setupAction() {
        binding.login.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
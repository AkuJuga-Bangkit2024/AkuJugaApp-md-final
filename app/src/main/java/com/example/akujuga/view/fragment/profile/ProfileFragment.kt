package com.example.akujuga.view.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.akujuga.databinding.FragmentProfileBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupUser()
        setupAction()

        return binding.root
    }

    private fun setupAction() {
        binding.logout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun setupUser() {
        viewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
            if (user != null) {
                setupProfile(user)
            }
        }
    }

    private fun setupProfile(guestUser: FirebaseUser) {
        binding.username.text = guestUser.displayName ?: "Guest"
        binding.emailUser.text = guestUser.email ?: "No Email"

        guestUser.photoUrl?.let {
            Glide.with(this)
                .load(it)
                .into(binding.profileImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        // Hide the action bar
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}
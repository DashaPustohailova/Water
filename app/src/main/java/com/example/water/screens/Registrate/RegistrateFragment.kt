package com.example.water.screens.Registrate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentRegistrateBinding


class RegistrateFragment : Fragment() {


    private var _binding: FragmentRegistrateBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: RegistrateFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrateBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_registrate, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(RegistrateFragmentViewModel::class.java)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
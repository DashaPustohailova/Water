package com.example.water.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentStartBinding
import com.example.water.utilits.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization(){
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        btIn.setOnClickListener{
            APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_lkFragment)
        }
        btRegistrate.setOnClickListener{
            APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.water.screens.lk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentLkBinding
import com.example.water.models.UserData
import com.example.water.utilits.*
import kotlinx.android.synthetic.main.fragment_lk.*

class LkFragment : Fragment() {

    private var _binding: FragmentLkBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: LkFragmentViewModel
    private lateinit var mObserverList: Observer<UserData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLkBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        Toast.makeText(APP_ACTIVITY, USER_NAME, Toast.LENGTH_LONG).show()
        mViewModel = ViewModelProvider(this).get(LkFragmentViewModel::class.java)
        btStatistics.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_statisticsFragment)
        }
        btExit.setOnClickListener{
            AUTH.signOut()
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_startFragment)
        }

        tvHello.text = "Hello," + USER_DATA?.name

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
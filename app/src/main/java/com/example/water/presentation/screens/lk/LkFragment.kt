package com.example.water.presentation.screens.lk

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentLkBinding
import com.example.water.utilits.*
import kotlinx.android.synthetic.main.fragment_lk.*
import java.text.SimpleDateFormat
import java.util.*

import com.example.water.domain.models.Report


class LkFragment : Fragment() {

    private var _binding: FragmentLkBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: LkFragmentViewModel
    private lateinit var transition: AnimationDrawable



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

        mViewModel = ViewModelProvider(this, LkViewModelFactory()).get(LkFragmentViewModel::class.java)
        mViewModel.userLiveData.observe(
            viewLifecycleOwner,
            Observer { data ->
                data?.let {
                    tvHello.text = "Привет, " + it.name
                }
            }
        )

        mViewModel.currentReport.observe(
            viewLifecycleOwner,
            Observer { data ->
                data?.let {
                    mViewModel.createCurrentDataReport(data)
                }
            }
        )

        mViewModel.lastReport.observe(
            viewLifecycleOwner,
            Observer{report ->
                tvDate.text = "Сегодня: ${report.date}"
                tvWater.setText("${report.water}")
            }
        )


        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()

        btEnter.setOnClickListener{
            mViewModel.changeCountWater(tvWater.text.toString())
        }

        btStatistics.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_statisticsFragment)
        }

        btProfile.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_userPersonalDataFragment)
        }

        btExit.setOnClickListener{
            mViewModel.signOut()
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_startFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

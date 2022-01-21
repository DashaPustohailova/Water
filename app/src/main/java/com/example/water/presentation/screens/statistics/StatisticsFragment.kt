package com.example.water.presentation.screens.statistics

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.water.R
import com.example.water.databinding.FragmentStatisticsBinding
import com.example.water.domain.models.Report
import kotlinx.android.synthetic.main.fragment_lk.*

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StatisticksFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: StatisticAdapter
    private lateinit var transition: AnimationDrawable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStatisticsBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()

        mViewModel = ViewModelProvider(this, StatisticViewModelFactory()).get(StatisticksFragmentViewModel::class.java)

        mAdapter = StatisticAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter

        mViewModel.userData.observe(
            viewLifecycleOwner,
            {
                mAdapter.userData = it
            }
        )
        mViewModel.allReport.observe(
            viewLifecycleOwner,
            {
                val list = it.asReversed()
                mAdapter.setList(list)
            }
        )
    }
}
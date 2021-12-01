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
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.AUTH
import kotlinx.android.synthetic.main.fragment_lk.*

class LkFragment : Fragment() {

    private var _binding: FragmentLkBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: LkFragmentViewModel
    private lateinit var mObserverList: Observer<List<UserData>>

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
        mViewModel = ViewModelProvider(this).get(LkFragmentViewModel::class.java)
        btStatistics.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_statisticsFragment)
        }
        btExit.setOnClickListener{
            AUTH.signOut()
            APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_startFragment)
        }

        mObserverList = Observer {
            val userData1 = it
            tvHello.text = userData1[0].name
            Toast.makeText(APP_ACTIVITY, userData1[0].name, Toast.LENGTH_LONG).show()
        }
        mViewModel.userData.observe(this, mObserverList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
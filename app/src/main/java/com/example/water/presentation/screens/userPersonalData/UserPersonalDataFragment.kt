package com.example.water.presentation.screens.userPersonalData

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentUserPersonalDataBinding
import com.example.water.utilits.USER_DATA
import kotlinx.android.synthetic.main.fragment_lk.tvHello
import kotlinx.android.synthetic.main.fragment_user_personal_data.*

class UserPersonalDataFragment : Fragment() {

    private var _binding: FragmentUserPersonalDataBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var transition: AnimationDrawable

    private lateinit var mViewModel: UserPersonalDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentUserPersonalDataBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {

        mViewModel = ViewModelProvider(this, UserPersonalDataViewModelFactory())
            .get(UserPersonalDataViewModel::class.java)

        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()

        mViewModel.userLiveData.observe(
            viewLifecycleOwner,
            Observer { data ->
                data?.let {
                    USER_DATA = data
                    tvHello.text = "Привет, " + USER_DATA.name
                    tvWeight.text = "Ваш вес: " + USER_DATA.weight
                    tvNormalWater.text = "Норма воды в день: " + USER_DATA.normWater + "мл"
                }
            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
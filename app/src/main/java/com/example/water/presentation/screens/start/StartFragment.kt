package com.example.water.presentation.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentStartBinding
import com.example.water.utilits.*
import kotlinx.android.synthetic.main.fragment_start.*
import android.graphics.drawable.AnimationDrawable
import androidx.lifecycle.Observer
import com.example.water.data.repository.DatabaseRepositoryImpl
import com.example.water.data.storage.firebase.FirebaseStorage


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var transition: AnimationDrawable

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

        mViewModel = ViewModelProvider(this, StartFragmentViewModelFactory()).get(StartFragmentViewModel::class.java)

        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()


        mViewModel.resultSignInLiveData.observe(this, Observer {
            Toast.makeText(APP_ACTIVITY, it, Toast.LENGTH_SHORT).show()
        })

        btIn.setOnClickListener{
            mViewModel.signIn(mBinding.inputEmail.text.toString(), mBinding.inputPassword.text.toString())
        }


        btRegistrate.setOnClickListener{
            mViewModel.toRegistrateFragment()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
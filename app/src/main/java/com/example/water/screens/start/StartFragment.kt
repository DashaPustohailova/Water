package com.example.water.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentStartBinding
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.AUTH
import com.example.water.utilits.EMAIL
import com.example.water.utilits.PASSWORD
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.*

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
            val inputEmail = mBinding.inputEmail.text.toString()
            val inputPassword = mBinding.inputPassword.text.toString()

            Toast.makeText(APP_ACTIVITY," IN", Toast.LENGTH_SHORT).show()

            if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                EMAIL = inputEmail
                PASSWORD = inputPassword
                mViewModel.initDatabase(){
                    //если инициализация прошла успешно
                    Toast.makeText(APP_ACTIVITY,"Иницициализация прошла успешно", Toast.LENGTH_SHORT).show()
                    APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_lkFragment)
                }
            }
            else{
                Toast.makeText(APP_ACTIVITY, getString(R.string.empty_email_or_password), Toast.LENGTH_SHORT).show()
            }
//            APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_lkFragment)
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
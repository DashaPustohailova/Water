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
<<<<<<< HEAD:app/src/main/java/com/example/water/presentation/screens/start/StartFragment.kt
=======
import androidx.lifecycle.Observer
>>>>>>> 04a74eaed8119723aecfe8c70e062fa68d6061cb:app/src/main/java/com/example/water/screens/start/StartFragment.kt


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

        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()


        mViewModel.resultSignInLiveData.observe(this, Observer {
            Toast.makeText(APP_ACTIVITY, it, Toast.LENGTH_SHORT).show()
        })

        btIn.setOnClickListener{
<<<<<<< HEAD:app/src/main/java/com/example/water/presentation/screens/start/StartFragment.kt
            val inputEmail = mBinding.inputEmail.text.toString()
            val inputPassword = mBinding.inputPassword.text.toString()


            if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                EMAIL = inputEmail
                PASSWORD = inputPassword
//                Log.d("Value", EMAIL +" ")
                mViewModel.initDatabase(){
                    //если инициализация прошла успешно
                    APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_lkFragment)
                }
            }
            else{
                Toast.makeText(APP_ACTIVITY, getString(R.string.empty_email_or_password), Toast.LENGTH_SHORT).show()
            }
=======
            mViewModel.signIn(mBinding.inputEmail.text.toString(), mBinding.inputPassword.text.toString())
>>>>>>> 04a74eaed8119723aecfe8c70e062fa68d6061cb:app/src/main/java/com/example/water/screens/start/StartFragment.kt
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
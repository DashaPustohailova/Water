package com.example.water.screens.Registrate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentRegistrateBinding
import com.example.water.models.UserData
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.EMAIL
import com.example.water.utilits.INIT_DATABASE
import com.example.water.utilits.PASSWORD
import kotlinx.android.synthetic.main.fragment_registrate.*
import kotlinx.android.synthetic.main.fragment_registrate.view.*
import kotlin.properties.Delegates


class RegistrateFragment : Fragment() {


    private var _binding: FragmentRegistrateBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: RegistrateFragmentViewModel

    lateinit var name: String
    var weight by Delegates.notNull<Int>()
    lateinit var gender: String
    lateinit var inputEmail: String
    lateinit var inputPassword: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrateBinding.inflate(layoutInflater, container, false)


        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        val genderArray = resources.getStringArray(R.array.gender)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, genderArray)
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_item)
        mBinding.autoCompleteTextView.setAdapter(arrayAdapter)



    }

    override fun onStart() {
        super.onStart()
        initialization()



        btRegistrate.setOnClickListener{

            name = mBinding.etName.text.toString()
            weight = mBinding.etWeight2.text.toString().toInt()
            gender = "Женский"
            inputEmail = mBinding.inputEmail.text.toString()
            inputPassword = mBinding.etPassword.text.toString()


            EMAIL = inputEmail
            PASSWORD = inputPassword
            Toast.makeText(APP_ACTIVITY, weight.toString(), Toast.LENGTH_SHORT ).show()

            mViewModel.registration(UserData(name = name, weight = weight, gender = gender, normWater = 0)){
                //если регистрация прошла успешно
                mViewModel.initUser()
                //APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
            }
        }

    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(RegistrateFragmentViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
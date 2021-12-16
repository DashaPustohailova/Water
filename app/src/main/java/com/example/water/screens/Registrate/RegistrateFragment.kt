package com.example.water.screens.Registrate

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
    private lateinit var transition: AnimationDrawable

    lateinit var name: String
    var selectedItem: String = "Женский"
    var weight by Delegates.notNull<Int>()
    lateinit var gender: String
    lateinit var inputEmail: String
    lateinit var inputPassword: String
    lateinit var inputPasswordSecond: String

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
    }

    override fun onStart() {
        super.onStart()
        initialization()

        val genderArray = resources.getStringArray(R.array.gender)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, genderArray)
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_item)
        mBinding.autoCompleteTextView.setAdapter(arrayAdapter)

        mBinding.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener {
                parent, _,  position, id ->
            selectedItem = parent.getItemAtPosition(position).toString()
        }


        btRegistrate.setOnClickListener{

            name = mBinding.etName.text.toString()
            weight = mBinding.etWeight2.text.toString().toInt()
            gender = selectedItem
            inputEmail = mBinding.inputEmail.text.toString()
            inputPassword = mBinding.etPassword.text.toString()
            inputPasswordSecond = mBinding.etPassword2.text.toString()

            EMAIL = inputEmail
            PASSWORD = inputPassword


            if(inputPassword.equals(inputPasswordSecond)){
                mViewModel.registration(UserData(name = name, weight = weight, gender = gender)){
                    //если регистрация прошла успешно
//                APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
//                APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
                }
            }
            else{
                Toast.makeText(APP_ACTIVITY, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }

        }

        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(RegistrateFragmentViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
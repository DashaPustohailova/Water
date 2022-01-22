package com.example.water.presentation.screens.Registrate

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.water.R
import com.example.water.databinding.FragmentRegistrateBinding
import com.example.water.domain.models.UserData
import com.example.water.utilits.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_registrate.*


class RegistrateFragment : Fragment() {

    private var _binding: FragmentRegistrateBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: RegistrateFragmentViewModel
    private lateinit var transition: AnimationDrawable

    lateinit var name: String
    var selectedItem: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrateBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this, RegistrationViewModelFactory()).get(RegistrateFragmentViewModel::class.java)


        mViewModel.toastMessageLiveData.observe(
            viewLifecycleOwner,
            Observer { text ->
                text?.let {
                    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                }
            })

        val genderArray = resources.getStringArray(R.array.gender)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, genderArray)
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_item)
        mBinding.autoCompleteTextView.setAdapter(arrayAdapter)

        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()


        mBinding.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener {
                parent, _,  position, _ ->
            selectedItem = parent.getItemAtPosition(position).toString()
        }


        btRegistrate.setOnClickListener{
            val email = mBinding.inputEmail.text.toString()
            val password = mBinding.etPassword.text.toString()
            val secondPassword = mBinding.etPassword2.text.toString()

            mViewModel.registration(
                    inputEmail = email,
                    inputPassword = password,
                    secondPassword = secondPassword,
                    userData = UserData(
                        name = mBinding.etName.text.toString(),
                        weight = mBinding.etWeight2.text.toString().toInt(),
                        gender = selectedItem
                    ),
                    onSuccess = {
                        Toast.makeText(APP_ACTIVITY, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                        APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
                    },
                    onFail = {

                    }
                )
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
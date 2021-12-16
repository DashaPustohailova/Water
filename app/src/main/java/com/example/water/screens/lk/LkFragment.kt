package com.example.water.screens.lk

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
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
import com.example.water.utilits.*
import kotlinx.android.synthetic.main.fragment_lk.*
import java.text.SimpleDateFormat
import java.util.*
import android.text.Editable

import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.example.water.models.Report


class LkFragment : Fragment() {

    private var _binding: FragmentLkBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: LkFragmentViewModel
    val pref = APP_ACTIVITY.getPreferences(Context.MODE_PRIVATE)
    private lateinit var transition: AnimationDrawable
    val editor = pref.edit()
    val sdf = SimpleDateFormat("dd.M.yyyy")
    var currentDate = sdf.format(Date())




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLkBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {


        mViewModel = ViewModelProvider(this).get(LkFragmentViewModel::class.java)
//        mViewModel.initUser()
        mViewModel.userLiveData.observe(
            viewLifecycleOwner,
            Observer { data ->
                data?.let {
                    USER_DATA = data
                    tvHello.text = "Привет, " + USER_DATA.name
                }
            }
        )

        mViewModel.currentReport.observe(
            viewLifecycleOwner,
            Observer { data ->
                data?.let {
                    if (data == 0L) {
                        //если в базе данных еще нет записи с текущей датой
                        val idReport  = REF_DATABASE?.ref
                            ?.child("report/$CURRENT_ID")
                            ?.push()?.key.toString()
                        val reportNote = hashMapOf<String, Any>()
                        val report = Report(sdf.format(Date()),"0")
                        reportNote[DATE] =  report.date
                        reportNote[WATER] = report.water
                        REF_DATABASE?.child("report/$CURRENT_ID/$idReport")
                            ?.updateChildren(reportNote)
                            ?.addOnSuccessListener {
                                    }
//                            }
                    }

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
//        Log.d("Value", "LK_FRAGMENT" + " " + CURRENT_ID + USER_DATA.name.toString())


        btEnter.setOnClickListener{
            val reportNote = hashMapOf<String, Any>()
            val report = Report(sdf.format(Date()),"0")
            reportNote[DATE] =  report.date
            reportNote[WATER] = tvWater.text.toString()
            REF_DATABASE?.child("report/$CURRENT_ID/$ID_REPORT")
                ?.updateChildren(reportNote)
                ?.addOnSuccessListener {
                }
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



        mBinding.bback.setBackgroundResource(R.drawable.back_water)
        transition =  mBinding.bback.background as AnimationDrawable
        transition.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

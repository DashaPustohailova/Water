package com.example.water.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.water.R
import com.example.water.data.repository.DatabaseRepositoryImpl
import com.example.water.data.storage.firebase.FirebaseStorage
import com.example.water.databinding.ActivityMainBinding
import com.example.water.utilits.APP_ACTIVITY


class MainActivity : AppCompatActivity() {


    lateinit var mToolbar: Toolbar
    lateinit var mNavController: NavController
    private  var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        //чтобы получать контекст в любом месте приложения
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title = "Water"

    }



    override fun onDestroy() {
        super.onDestroy()
        //предотвращение возможности утечки памяти
        _binding = null
    }
}
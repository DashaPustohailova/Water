package com.example.water

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.water.databinding.ActivityMainBinding
import com.example.water.models.UserData
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.REF_DATABASE
import com.example.water.utilits.REPOSITORY
import com.example.water.utilits.USER_DATA


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
package com.example.fragmentandmvvmstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    // MainViewModel을 사용하여 데이터를 관찰하고, 해당 데이터를 UI에 업데이트하는 역할
    // Android Jetpack의 뷰모델(ViewModel)을 가져오기 위한 프로퍼티 위임(delegate) 구문
    // 이를 통해 현재 액티비티나 프래그먼트와 연결된 뷰모델 인스턴스를 손쉽게 얻을 수 있습니다.
    // by viewModels()는 이 뷰모델 인스턴스를 현재 액티비티나 프래그먼트에 연결하고 관리
    // 이를 통해 액티비티나 프래그먼트의 수명 주기에 맞게 뷰모델을 생성하고 관리할 수 있습니다.
    private val viewModel : MainViewModel by viewModels()
    private lateinit var editText : EditText
    private lateinit var submitButton: Button

    val resultFragment = ResultFragment()

    // Fragment를 추가, 제거 또는 교체하기 위한 FragmentTransaction 객체를 생성
    val fragmentTransaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pushButton()
    }

    fun pushButton() {
        editText = findViewById(R.id.editText)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val cityName = editText.text.toString()
            val apiKey = "a8c1d55d8c112dbe5f0576f243f507ac"

            viewModel.updateWeatherData(cityName, apiKey)

            // 프래그먼트는 화면을 전환하는 것이므로 뷰!
            fragmentTransaction
                .replace(R.id.fragment_container_view, resultFragment)
                .commit()
        }
    }
}
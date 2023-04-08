package com.example.myweight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myweight.databinding.ActivityMainBinding
import com.example.myweight.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    private val resultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resultBinding.root)

        // 전달 받은 키와 몸무게
        val height = intent.getFloatExtra("height", 0f)
        val weight = intent.getFloatExtra("weight", 0f)

        // 몸무게 계산
        val bmi = weight / (height / 100.0f).pow(2.0f)

        // 결과 표시
        when {
            bmi >= 35 -> resultBinding.resultTextView.text = "고도 비만"
            bmi >= 30 -> resultBinding.resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultBinding.resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultBinding.resultTextView.text = "과체중"
            bmi >= 18.5 -> resultBinding.resultTextView.text = "정상"
            else -> resultBinding.resultTextView.text = "저체중"
        }

        when {
            bmi >= 25 -> resultBinding.imageView.setImageResource(R.drawable.obesity)
            bmi >= 23 -> resultBinding.imageView.setImageResource(R.drawable.over_weight)
            bmi >= 18.5 -> resultBinding.imageView.setImageResource(R.drawable.nomal_weight)
            else -> resultBinding.imageView.setImageResource(R.drawable.under_weight)
        }

    }
}
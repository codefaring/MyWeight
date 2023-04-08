package com.example.myweight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.myweight.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    
    // 데이터 저장하기
    private fun saveData(height: Float, weight: Float) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height)
            .putFloat("KEY_WEIGHT", weight)
            .apply()
    }
    
    // 데이터 불러오기
    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getFloat("KEY_HEIGHT", 0f)
        val weight = pref.getFloat("KEY_WEIGHT", 0f)
        
        if(height != 0f && weight != 0f) {
            binding.heightEdit.setText(height.toString())
            binding.weightEdit.setText(weight.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        // 저장한 값 불러오기
        loadData()


        binding.resultBtn.setOnClickListener {
            // 아무 입력도 하지 않았을 경우의 에러 처리
            if(binding.weightEdit.text.isNotBlank() && binding.heightEdit.text.isNotBlank()) {
                // 마지막에 입력한 내용 저장
                saveData(
                    binding.weightEdit.text.toString().toFloat(),
                    binding.heightEdit.text.toString().toFloat(),
                )

                // apply 이용한 데이터 담기
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("weight", binding.weightEdit.text.toString().toFloat())
                    putExtra("height", binding.heightEdit.text.toString().toFloat())
                }
                startActivity(intent)
            }
        }
    }
}
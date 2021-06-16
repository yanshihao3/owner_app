package com.zq.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class UserCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_code)

        findViewById<AppCompatButton>(R.id.btn).setOnClickListener {
            setResult(1)
            //返回登录结果
            finish()
        }
    }


}
package kr.co.tjoeun.daily10minutes_20200927

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

            val inputID = idEdt.text.toString()
            val inputpw = pwEdt.text.toString()

        }

    }

    override fun setValues() {

    }

}
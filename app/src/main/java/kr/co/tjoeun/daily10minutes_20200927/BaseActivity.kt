package kr.co.tjoeun.daily10minutes_20200927

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

}
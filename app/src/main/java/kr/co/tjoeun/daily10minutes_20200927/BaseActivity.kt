package kr.co.tjoeun.daily10minutes_20200927

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

//    액션바를 커스터마이징 해주는 기능 (모든 액티비티가 공유 - BaseActivity에 작성)
    fun setCustomActionBar() {

//    모든 액티비티에는 액션바가 있다고 전제하자
    val myActionBar = supportActionBar!!

//    액션바가 커스텀 화면을 보여줄 수 있게 모드 설정
    myActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//    실제로 보여질 커스텀 화면 (우리가 직접 만드는 xml)을 연결
    myActionBar.setCustomView(R.layout.my_custom_action_bar)

//    커스텀액션바 뒤의 기본 색 제거 => 액션바를 들고있는 툴바의 좌우 여백을 0으로 설정
//    androidx의 툴바 사용
    val parentToolbar = myActionBar.customView.parent as Toolbar
    parentToolbar.setContentInsetsAbsolute(0, 0)

    }

}
package kr.co.tjoeun.daily10minutes_20200927

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.tjoeun.daily10minutes_20200927.utils.ContextUtil
import kr.co.tjoeun.daily10minutes_20200927.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        autoLoginCheckBox.setOnCheckedChangeListener { buttonview, isChecked ->

//            응용문제 => 앱을 껐다 켜도 체크 여부가 유지되도록
//            (체크되면 저장-setter + 화면이 열릴 때 저장된 값 불러오기-getter)
//            Hint. 하나의 메모장에, 여러 항목을 저장할 수 있다.

            ContextUtil.setAutoLogin(mContext, isChecked)

        }

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }


        loginBtn.setOnClickListener {

            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            ServerUtil을 이용해서 실제 로그인 시도

            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    로그인 실행 결과에 따라 행동할 내용을 적는 공간
//                    code 이름으로 적힌 Int 값을 받아서, 200이냐 아니냐에 따라 다른 행동

                    val codeNum = json.getInt("code")

                    Log.d("서버가알려주는코드값", codeNum.toString())

                    if (codeNum == 200) {

//                        응용문제 : 로그인 성공 시 로그인한 사용자의 닉네임 토스트 출력
//                        json > data > user > nick_name 추출

                        val dataObj = json.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")

                        val userNickName = userObj.getString("nick_name")

//                        서버가 알려주는 토큰값을 기기에 저장하고 => 화면을 이동하자

//                        토큰값 추출 => 변수에 저장 (기기에 저장 X)
                        val token = dataObj.getString("token")
//                        SharedPreferences를 이용해 기기에 저장 (ContextUtil 클래스 활용)
                        ContextUtil.setLoginUserToken(mContext, token)


                        runOnUiThread{
                            Toast.makeText(mContext, "${userNickName}님 환영합니다!", Toast.LENGTH_SHORT).show()

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)

//                            메인화면으로 이동하면 로그인 화면은 종료처리
                            finish()
                        }

                    }
                    else {
//                        로그인 실패 => 토스트로 로그인 실패 안내
//                        토스트 : UI 동작 -> UI Thread가 실행하도록 해야함

//                        연습문제 : 로그인 실패 시 실패 사유를 서버가 알려주는 이유로 출력

                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, "message", Toast.LENGTH_SHORT).show() }

                    }

                }

            })

        }

    }

    override fun setValues() {

//        로그인 화면이 열릴 때, 자동로그인 체크 여부를 체크박스에 설정
        autoLoginCheckBox.isChecked = ContextUtil.isAutoLogin(mContext)

    }

}
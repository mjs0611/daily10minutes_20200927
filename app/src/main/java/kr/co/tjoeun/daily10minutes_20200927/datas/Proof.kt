package kr.co.tjoeun.daily10minutes_20200927.datas

import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class Proof {
    fun add(proofFromJson: Proof) {

    }

    var id = 0
    var content = ""

    //    사진 주소 목록
    val imageList = ArrayList<String>()

    //    인증시간 (String으로 내려오지만, Calendar로 보관)
    val proofTime = Calendar.getInstance()

    //    작성자 정보 - User형태로 보관
    var writer = User()

    companion object {

        fun getProofFromJson(json: JSONObject) : Proof {

            val proof = Proof()

            proof.id = json.getInt("id")
            proof.content = json.getString("content")

//            인증글 파싱할때, 작성자 정보도 파싱하자
            proof.writer = User.getUserFromJSON(json.getJSONObject("user"))

            return proof

        }

    }

}
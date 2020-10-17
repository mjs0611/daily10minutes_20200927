package kr.co.tjoeun.daily10minutes_20200927.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200927.R
import kr.co.tjoeun.daily10minutes_20200927.datas.Proof
import java.text.SimpleDateFormat

class ProofAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val proofData = mList[position]

        val writeProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writeNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val writtenDateTimeTxt = row.findViewById<TextView>(R.id.writtenDateTimeTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)

        contentTxt.text = proofData.content

        Glide.with(mContext).load(proofData.writer.profileImageList[0]).into(writeProfileImg)
        writeNickNameTxt.text = proofData.writer.nickName

//        인증글 시간정보 => 2020년 5월 8일 오후 3시 1분 양식으로 출력
        val sdf = SimpleDateFormat("yyyy년 M월 d일 a h시 m분")
        writtenDateTimeTxt.text = sdf.format(proofData.proofTime.time)

        return row
    }

}
package mcssoft.com.roomwordsample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import mcssoft.com.roomwordsample.R
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.text.TextUtils
import android.content.Intent
import android.widget.Button


class NewActivityFragment : Fragment() {

    lateinit private var editWordView: EditText
    lateinit private var rootView: View
    lateinit private var button: Button

    internal val EXTRA_REPLY: String = "com.example.android.wordlistsql.REPLY"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_new, container, false)

        editWordView = rootView.findViewById(R.id.edit_word)

        button = rootView.findViewById<Button>(R.id.button_save)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener(View.OnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.getText())) {
                activity!!.setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                activity!!.setResult(RESULT_OK, replyIntent)
            }
            activity!!.finish()
        })
    }

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity.setContentView(R.layout.activity_new_word)
//        editWordView = findViewById(R.id.edit_word)
//
//        val button = findViewById(R.id.button_save)
//        button.setOnClickListener(View.OnClickListener {
//            val replyIntent = Intent()
//            if (TextUtils.isEmpty(editWordView.getText())) {
//                setResult(RESULT_CANCELED, replyIntent)
//            } else {
//                val word = mEditWordView.getText().toString()
//                replyIntent.putExtra(EXTRA_REPLY, word)
//                setResult(RESULT_OK, replyIntent)
//            }
//            finish()
//        })
//    }
}
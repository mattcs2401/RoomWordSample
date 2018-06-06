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

    lateinit private var editText: EditText
    lateinit private var rootView: View
    lateinit private var button: Button

    internal val EXTRA_REPLY: String = "com.example.android.wordlistsql.REPLY"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        rootView =  inflater.inflate(R.layout.fragment_new, container, false)

        button = rootView.findViewById<Button>(R.id.button_save)
        editText = rootView.findViewById(R.id.edit_word)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener(View.OnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editText.getText())) {
                activity!!.setResult(RESULT_CANCELED, replyIntent)
            } else {
                //val word = editText.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, editText.getText().toString()) //word
                activity!!.setResult(RESULT_OK, replyIntent)
            }
            activity!!.finish()
        })
    }

}
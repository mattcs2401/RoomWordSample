package mcssoft.com.roomwordsample.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mcssoft.com.roomwordsample.entity.Word
import mcssoft.com.roomwordsample.model.WordViewModel
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import mcssoft.com.roomwordsample.R
import mcssoft.com.roomwordsample.adapter.WordListAdaptor
import android.widget.Toast
import android.R.attr.data
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import kotlinx.android.synthetic.main.content_new.*
import mcssoft.com.roomwordsample.activity.NewActivity
import mcssoft.com.roomwordsample.interfaces.IMainActivity


class MainActivityFragment : Fragment() {

    lateinit private var rootView: View
    lateinit private var wordViewModel: WordViewModel
    lateinit private var iMainActivity: IMainActivity
    lateinit private var adapter: WordListAdaptor

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IMainActivity) {
            iMainActivity = context
        } else {
            throw ClassCastException(context.toString() + " must implement IMainActivity.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        return  rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        Log.d("MainActivityFragment", "onActivityCreated")

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.id_rv_wordListing)
        adapter = WordListAdaptor(activity!!.getApplicationContext())
        recyclerView.setAdapter(adapter)
        recyclerView.setLayoutManager(LinearLayoutManager(activity))

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
//        Log.d("MainActivityFragment", "onStart")

        var word = iMainActivity.getWord()
        if(word != null) {
            wordViewModel.insert(word)
        }

        wordViewModel.getAllWords().observe(this, Observer<List<Word>> { words ->
            // Update the cached copy of the words in the adapter.
            if (words != null) {
                adapter.setWords(words)
            }
        })
    }

    /*** this is no good here, has to be in activity or via interface ***/
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode === NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode === RESULT_OK) {
//            val word = Word(data!!.getStringExtra("com.example.android.wordlistsql.REPLY"))
//            wordViewModel.insert(word)
//        } else {
//            Toast.makeText(
//                    activity!!.getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show()
//        }
//    }
}

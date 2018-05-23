package mcssoft.com.roomwordsample.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import android.content.Context
import mcssoft.com.roomwordsample.interfaces.IMainActivity


class MainActivityFragment : Fragment() {

    lateinit private var rootView: View
    lateinit private var wordViewModel: WordViewModel
    lateinit private var iMainActivity: IMainActivity
    lateinit private var wordListAdapter: WordListAdaptor

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

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.id_rv_wordListing)
        wordListAdapter = WordListAdaptor(activity!!.getApplicationContext())
        recyclerView.setAdapter(wordListAdapter)
        recyclerView.setLayoutManager(LinearLayoutManager(activity))

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        var word = iMainActivity.getWord()
        if(word != null) {
            wordViewModel.insert(word)
        }

        wordViewModel.getAllWords().observe(this, Observer<MutableList<Word>> { words ->
            // Update the cached copy of the words in the wordListAdapter.
            if (words != null) {
                wordListAdapter.setWords(words)
            }
        })
    }

}

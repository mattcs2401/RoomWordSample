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


class MainActivityFragment : Fragment() {

    lateinit private var rootView: View
    lateinit private var wordViewModel: WordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        return  rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.id_rv_wordListing)
        val adapter = WordListAdaptor(activity!!.getApplicationContext())
        recyclerView.setAdapter(adapter)
        recyclerView.setLayoutManager(LinearLayoutManager(activity))

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        wordViewModel.getAllWords().observe(this, Observer<List<Word>> { words ->
            // Update the cached copy of the words in the adapter.
            if (words != null) {
                adapter.setWords(words)
            }
        })
    }
}

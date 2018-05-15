package mcssoft.com.roomwordsample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mcssoft.com.roomwordsample.R
import mcssoft.com.roomwordsample.entity.Word

class WordListAdaptor(context: Context) : RecyclerView.Adapter<WordListAdaptor.WordViewHolder>() {

    private val inflator: LayoutInflater
    private var words: List<Word>

    init {
        inflator = LayoutInflater.from(context)
        this.words = ArrayList<Word>(0)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun getWord() = wordItemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder{
        var itemView = inflator.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if(words != null)
            return words.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if(words != null) {
            var current = words.get(position)
            holder.getWord().setText(current.word)
        } else {
            holder.getWord().setText("No word")
        }
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

}
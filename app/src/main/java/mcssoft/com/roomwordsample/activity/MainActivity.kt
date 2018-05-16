package mcssoft.com.roomwordsample.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import mcssoft.com.roomwordsample.R
import mcssoft.com.roomwordsample.model.WordViewModel
import android.content.Intent
import android.widget.Toast
import mcssoft.com.roomwordsample.R.id.toolbar
import mcssoft.com.roomwordsample.entity.Word
import mcssoft.com.roomwordsample.interfaces.IMainActivity


class MainActivity : AppCompatActivity(), IMainActivity {

//    lateinit private var wordViewModel: WordViewModel

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    private var word: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null)
//                    .show()
//        }

        fab.setOnClickListener() {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivityForResult(intent, 1)
        }

//        val mainActivityFragment = Fragment()
//        var fragTrans = fragmentManager.beginTransaction()
//        fragTrans.replace(R.id.fragment, mainActivityFragment)
//        fragTrans.addToBackStack(null)
//        fragTrans.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode === Activity.RESULT_OK) {
            word = Word(data!!.getStringExtra("com.example.android.wordlistsql.REPLY"))
//            wordViewModel.insert(word)
            val bp = ""
        } else {
            word = null
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG)
                 .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getWord(): Word? {
        return word
    }
}

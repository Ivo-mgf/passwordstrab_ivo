

package com.example.android.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipca.password.android.ivo.PassDeleteActivity

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var passViewModel: PassViewModel
    private val deleteWordActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PassListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        passViewModel = ViewModelProvider(this).get(PassViewModel::class.java)


        passViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        val buttonApagar = findViewById<Button>(R.id.buttonApagar)
        buttonApagar.setOnClickListener {
            val intent = Intent(this@MainActivity, PassDeleteActivity::class.java)
            startActivity(intent)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPassActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Pass(data.getStringExtra(NewPassActivity.WORD_REPLY),
                        data.getStringExtra(NewPassActivity.SITE_REPLY),
                        data.getStringExtra(NewPassActivity.DESCRICAO_REPLY))
                passViewModel.insert(word)
                Unit
            }
        }
        if (requestCode == deleteWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Pass(data.getStringExtra(PassDeleteActivity.WORD_REPLY),
                        data.getStringExtra(PassDeleteActivity.SITE_REPLY),
                        data.getStringExtra(PassDeleteActivity.DESCRICAO_REPLY))
                passViewModel.delete(word)
                Unit
            }
        }
    }
}



package com.example.android.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText



class NewPassActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editSiteView: EditText
    private lateinit var editDescicaoView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)
        editSiteView = findViewById(R.id.edit_site)
        editDescicaoView = findViewById(R.id.edit_descricao)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                val site = editSiteView.text.toString()
                val descricao = editDescicaoView.text.toString()

                replyIntent.putExtra(WORD_REPLY, word)
                replyIntent.putExtra(SITE_REPLY, site)
                replyIntent.putExtra(DESCRICAO_REPLY, descricao)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val WORD_REPLY = "word"
        const val SITE_REPLY = "site"
        const val DESCRICAO_REPLY = "descricao"
    }
}


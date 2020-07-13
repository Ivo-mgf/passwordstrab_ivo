package ipca.password.android.ivo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android.roomwordssample.NewPassActivity
import com.example.android.roomwordssample.R


class PassDeleteActivity : AppCompatActivity() {
    private lateinit var editWordView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apagar)
        editWordView = findViewById(R.id.edit_word)


        val button = findViewById<Button>(R.id.button_apagar)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                val site = ""
                val descricao = ""

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
package net.bedev.jentikapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.bedev.jentikapps.helper.See

class Dashboard : AppCompatActivity() {
    var id_kader = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val sharedPreferences = getSharedPreferences("kader", Context.MODE_PRIVATE)
        id_kader = sharedPreferences.getString("id_kader", "").toString()
        See.log("id_kader : $id_kader")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_user, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.MenuDataStatistik) {
            val intent = Intent(this, MenuStatistik::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

//            Toast.makeText(this, "dalam pengembangan", Toast.LENGTH_SHORT).show()
            return true
        }

        if (id == R.id.InputDataJentik) {

            if (id_kader.isNotEmpty()){
                val intent = Intent(this, InputJentik::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                val intent = Intent(this, Login::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

            //Toast.makeText(this, "dalam pengembangan", Toast.LENGTH_SHORT).show()
            return true
        }

        if (id == R.id.DataWilayahABJ){
            val intent = Intent(this, AbJentikActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return true
        }

        if (id == R.id.menuAbout) {

            val intent = Intent(this, About::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


            return true
        }
        if (id == R.id.menuLogOut){
            val sharedPreferences = getSharedPreferences("kader", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("id_kader")
            editor.apply()
            Toast.makeText(this, "Logout Sukses", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)

    }
}

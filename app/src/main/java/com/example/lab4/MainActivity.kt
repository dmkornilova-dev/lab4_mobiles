package com.example.lab4

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab4.fragments.FacultyFragment
import com.example.lab4.interfaces.MainActivityInterface

class MainActivity : AppCompatActivity(), MainActivityInterface {
    interface Edit{
        fun append()
        fun update()
        fun delete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showFragment()
    }

    override fun newTitle(_title: String) {
        title=_title
    }

    override fun showFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
            FacultyFragment.getInstance()).addToBackStack(null).commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.miNewFaculty -> {
                val fedit: MainActivity.Edit = FacultyFragment.getInstance()
                fedit.append()
                true
            }

            R.id.miUpdateFaculty -> {
                val fedit: MainActivity.Edit = FacultyFragment.getInstance()
                fedit.update()
                true
            }

            R.id.miDeleteFaculty -> {
                val fedit: MainActivity.Edit = FacultyFragment.getInstance()
                fedit.delete()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
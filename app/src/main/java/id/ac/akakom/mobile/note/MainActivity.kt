package id.ac.akakom.mobile.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.ac.akakom.mobile.note.databinding.ActivityMainBinding
import id.ac.akakom.mobile.note.ui.section.SectionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.container)

        setSupportActionBar(binding.toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

package id.ac.akakom.mobile.note.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val navController by lazy { Navigation.findNavController(this,
        R.id.container
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setContentView(root)
            setSupportActionBar(toolbar)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

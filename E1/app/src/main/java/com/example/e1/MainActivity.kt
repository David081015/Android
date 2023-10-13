package com.example.e1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.e1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val g1 = Game(1,"Watch Dogs",131.99, 5,"Nintendo Switch",false)
        val g2 = Game(2, "Minecraft",799.99,10,"Nintendo Switch",true)
        val g3 = Game(3,"WWE 2K18",200.57,15,"Xbox",true)
        val g4 = Game(4,"FIFA 20",389.88,20,"PlayStation",true)
        val g5 = Game(5,"GTA V",290.33,25,"Xbox",false)
        val g6 = Game(6,"Call of duty Black Ops 2",498.53,30,"PlayStation",true)
        Singleton.list.add(g1)
        Singleton.list.add(g2)
        Singleton.list.add(g3)
        Singleton.list.add(g4)
        Singleton.list.add(g5)
        Singleton.list.add(g6)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
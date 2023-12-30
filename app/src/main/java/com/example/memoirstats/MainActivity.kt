package com.example.memoirstats

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.memoirstats.databinding.ActivityMainBinding
import com.example.memoirstats.model.view.MemoirViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MemoirViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            newScenarioDialog().show()
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

    private fun newScenarioDialog(): AlertDialog {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val scenarioNameEditText = EditText(this)

        val scenarioNameDialog =
            MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_rounded)
                .setTitle(R.string.new_scenario)
                .setView(scenarioNameEditText)
                .setPositiveButton(R.string.ok) { _, _ ->
                    val name = scenarioNameEditText.text.toString()
                    if (viewModel.addScenario(name)) {
                        val bundle = ScenarioFragment.makeBundle(name)
                        navController.navigate(R.id.action_BaseFragment_to_ScenarioFragment, bundle)
                    } else {
                        nameAlreadyExistsAlert().show()
                    }
                }
                .setNegativeButton(R.string.cancel, null)
                .create()
        return scenarioNameDialog
    }

    private fun nameAlreadyExistsAlert(): AlertDialog =
        MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_rounded)
            .setTitle(R.string.name_already_exists)
            .setNegativeButton(R.string.cancel, null)
            .create()

}
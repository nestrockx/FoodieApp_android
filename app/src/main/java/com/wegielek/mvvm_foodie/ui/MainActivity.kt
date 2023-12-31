package com.wegielek.mvvm_foodie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.wegielek.mvvm_foodie.R
import com.wegielek.mvvm_foodie.databinding.ActivityMainBinding
import com.wegielek.mvvm_foodie.repository.RecipeRepository

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }
    lateinit var viewModel: RecipeViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeRepository = RecipeRepository()
        val viewModelProviderFactory = RecipeViewModelProviderFactory(application, recipeRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[RecipeViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.findFragmentById(R.id.recipeNavHostFragment)
            ?.let {
                binding.bottomNavigationView.setOnItemSelectedListener {item ->
                    NavigationUI.onNavDestinationSelected(item, it.findNavController())
                    return@setOnItemSelectedListener true
                }
            }

    }

}
package com.example.harrypotterworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotterworld.databinding.ActivityMainBinding
import com.example.harrypotterworld.usecase.GetHousesUseCase.HousesState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupWidgets()
        setupObservers()
        viewModel.getHouses()
    }

    private fun setupWidgets() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = HouseAdapter(emptyList())
        }
    }

    private fun setupObservers() {
        viewModel.housesState.observe(this) { state ->
            when(state) {
                is HousesState.Success ->
                    (binding.recyclerView.adapter as? HouseAdapter)?.submitList(state.houses)
                is HousesState.Empty ->
                    Toast.makeText(
                        this,
                        getString(R.string.no_houses_found),
                        Toast.LENGTH_SHORT
                    ).show()
                is HousesState.Error ->
                    Toast.makeText(this, state.errorMessage, Toast.LENGTH_SHORT).show()
                is HousesState.Loading -> {
                    // We should be showing a loading indicator
                }
                else -> {}
            }
        }
    }
}
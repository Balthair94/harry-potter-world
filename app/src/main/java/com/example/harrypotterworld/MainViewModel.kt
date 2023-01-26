package com.example.harrypotterworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterworld.usecase.GetHousesUseCase
import com.example.harrypotterworld.usecase.GetHousesUseCase.HousesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHousesUseCase: GetHousesUseCase
): ViewModel() {

    private val _housesState: MutableLiveData<HousesState> by lazy {
        MutableLiveData(HousesState.Idle)
    }

    val housesState: LiveData<HousesState> = _housesState

    fun getHouses() {
        viewModelScope.launch {
            _housesState.postValue(HousesState.Loading)
            _housesState.postValue(getHousesUseCase())
        }
    }
}
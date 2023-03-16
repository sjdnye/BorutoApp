package com.example.borutoapp.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {


    private var _searchQuery by mutableStateOf("")
    var searchQuery = _searchQuery

    private var _searchedHeroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchedHeroes = _searchedHeroes


    fun updateSearchQuery(query: String) {
        _searchQuery = query
    }

    fun searchHeroes(query: String) {
           viewModelScope.launch(Dispatchers.IO) {
                useCases.searchHeroesUseCase(query = query).cachedIn(viewModelScope).collect{
                   _searchedHeroes
               }
           }
    }

}
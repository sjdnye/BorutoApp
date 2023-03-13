package com.example.borutoapp.data.repository

import com.example.borutoapp.domain.repository.DataStoreOperation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperation
) {

    suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }
}
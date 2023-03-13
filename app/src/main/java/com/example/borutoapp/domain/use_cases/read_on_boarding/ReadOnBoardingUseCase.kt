package com.example.borutoapp.domain.use_cases.read_on_boarding

import com.example.borutoapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}
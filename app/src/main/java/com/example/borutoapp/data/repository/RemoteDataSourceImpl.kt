package com.example.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.paging_source.HeroRemoteMediator
import com.example.borutoapp.data.paging_source.SearchHeroesSource
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.RemoteDataSource
import com.example.borutoapp.utils.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {
    private val heroDao = borutoDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllData(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(
                    borutoApi = borutoApi,
                    query = query
                )
            }
        ).flow
    }
}
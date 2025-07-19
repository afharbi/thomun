package com.example.thomun.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.thomun.data.api.ThomunApi
import com.example.thomun.data.mapper.toSection
import com.example.thomun.domain.models.home.Section
import com.example.thomun.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import okio.IOException

class SectionPagingSource(
    private val api: ThomunApi
) : PagingSource<Int, Section>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> {
        val page = params.key ?: 1
        return try {
            val response = api.getHomeSections(page)
            val sections = response.sections?.mapNotNull { it?.toSection() } ?: emptyList()
            val nextPage = response.pagination?.next_page?.toIntOrNull()
            LoadResult.Page(
                data = sections,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Section>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

class HomeRepositoryImpl(
    private val api: ThomunApi
): HomeRepository {
    override fun getPagedSections(): Flow<PagingData<Section>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { SectionPagingSource(api) }
        ).flow
    }
}
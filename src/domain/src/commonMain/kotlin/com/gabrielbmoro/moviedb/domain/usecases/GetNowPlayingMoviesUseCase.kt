package com.gabrielbmoro.moviedb.domain.usecases

import com.gabrielbmoro.moviedb.domain.MoviesRepository
import com.gabrielbmoro.moviedb.domain.entities.Movie

interface GetNowPlayingMoviesUseCase : UseCase<GetNowPlayingMoviesUseCase.Params, List<Movie>> {
    data class Params(
        val page: Int
    )
}

class GetNowPlayingMoviesUseCaseImpl(
    private val repository: MoviesRepository
) : GetNowPlayingMoviesUseCase {
    override suspend fun execute(input: GetNowPlayingMoviesUseCase.Params): List<Movie> {
        return repository.getNowPlayingMovies(
            page = input.page
        )
    }
}

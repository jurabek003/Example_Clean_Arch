package uz.turgunboyevjurabek.examplecleanearch.data.repositoryImpl

import uz.turgunboyevjurabek.examplecleanearch.core.Result
import uz.turgunboyevjurabek.examplecleanearch.data.api.ApiService
import uz.turgunboyevjurabek.examplecleanearch.data.mappers.toDomain
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponse
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem
import uz.turgunboyevjurabek.examplecleanearch.domein.models.Leagues
import uz.turgunboyevjurabek.examplecleanearch.domein.repozotory.MyRepository

class MyRepositoryImpl(private val apiService: ApiService):MyRepository {

    override suspend fun getCats(): Result<List<GetImageResponseItem>> {
        return try {
            Result.Success(apiService.getCatsApiService().toDomain())
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }


}
//return try {
//    Result.Success(apiService.getLeagues().toDomain())
//} catch (exception: Exception) {
//    Result.Error(exception)
//}
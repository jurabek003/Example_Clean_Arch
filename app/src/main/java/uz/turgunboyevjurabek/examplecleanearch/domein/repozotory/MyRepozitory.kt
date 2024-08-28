package uz.turgunboyevjurabek.examplecleanearch.domein.repozotory

import uz.turgunboyevjurabek.examplecleanearch.core.Result
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem


interface MyRepository {
//    suspend fun getLeagues(): Result<List<Leagues>>
    suspend fun getCats(): Result<List<GetImageResponseItem>>

}
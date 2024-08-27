package uz.turgunboyevjurabek.examplecleanearch.domein.useCase

import uz.turgunboyevjurabek.examplecleanearch.domein.repozotory.MyRepository
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private  val myRepository: MyRepository
){
    suspend fun execute()=myRepository.getCats()
}
package uz.turgunboyevjurabek.examplecleanearch.prezentation

import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem
import uz.turgunboyevjurabek.examplecleanearch.domein.models.Leagues

sealed class CatsListState {
    object Loading : CatsListState()
    data class Success(val listOfCats: List<GetImageResponseItem>) : CatsListState()
    data class Error(val exception: Exception) :CatsListState()
}
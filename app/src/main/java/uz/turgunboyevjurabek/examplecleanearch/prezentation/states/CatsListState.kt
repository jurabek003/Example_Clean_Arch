package uz.turgunboyevjurabek.examplecleanearch.prezentation.states

import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem

sealed class CatsListState {
    object Loading : CatsListState()
    data class Success(val listOfCats: List<GetImageResponseItem>) : CatsListState()
    data class Error(val exception: Exception) : CatsListState()
}
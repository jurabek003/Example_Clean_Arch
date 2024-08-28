package uz.turgunboyevjurabek.examplecleanearch.prezentation.viewModels

import uz.turgunboyevjurabek.examplecleanearch.core.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem
import uz.turgunboyevjurabek.examplecleanearch.domein.useCase.GetCatsUseCase
import uz.turgunboyevjurabek.examplecleanearch.prezentation.CatsListState
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
):ViewModel() {
    private val _state = MutableStateFlow<CatsListState>(CatsListState.Loading)

    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CatsListState.Loading
    )

    fun fetchCats(){
        viewModelScope.launch {
            val result = getCatsUseCase.execute()
            handleGetAllProductResponse(result)
        }
    }

    private fun handleGetAllProductResponse(response: Result<List<GetImageResponseItem>>) {
        when (response) {
            is Result.Success -> setProductListState(CatsListState.Success(response.data))
            is Result.Error -> setProductListState(CatsListState.Error(response.exception))
        }
    }

    private fun setProductListState(state: CatsListState) {
        _state.update {
            state
        }
    }



}
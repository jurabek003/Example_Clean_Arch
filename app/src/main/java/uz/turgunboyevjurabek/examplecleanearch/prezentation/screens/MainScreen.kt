package uz.turgunboyevjurabek.examplecleanearch.prezentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem
import uz.turgunboyevjurabek.examplecleanearch.domein.models.Leagues
import uz.turgunboyevjurabek.examplecleanearch.prezentation.CatsListState
import uz.turgunboyevjurabek.examplecleanearch.prezentation.viewModels.LeaguesViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: LeaguesViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    viewModel.fetchCats()
    CatsGetContent(state =state.value)
}

@Composable
fun CatsGetContent(modifier: Modifier = Modifier,state: CatsListState) {
    val context= LocalContext.current

    when(state){
        is CatsListState.Loading->{
            LoadingView()
        }
        is CatsListState.Success->{
            ProductListSuccess(listOfProducts = state.listOfCats)
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        is CatsListState.Error->{
            ProductListError(exception = state.exception.message)
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

    }

}
@Composable
fun ProductListSuccess(listOfProducts: List<GetImageResponseItem>) {
    val context= LocalContext.current
    Toast.makeText(context, listOfProducts[0].url.toString(), Toast.LENGTH_SHORT).show()
}


@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}
@Composable
fun ProductListError(exception: String?) {
    exception?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = it)
        }
    }
}


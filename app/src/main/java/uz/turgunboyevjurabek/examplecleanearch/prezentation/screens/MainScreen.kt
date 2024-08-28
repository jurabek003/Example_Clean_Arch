@file:OptIn(ExperimentalGlideComposeApi::class)

package uz.turgunboyevjurabek.examplecleanearch.prezentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import uz.turgunboyevjurabek.examplecleanearch.R
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem
import uz.turgunboyevjurabek.examplecleanearch.prezentation.states.CatsListState
import uz.turgunboyevjurabek.examplecleanearch.prezentation.viewModels.MyViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    viewModel: MyViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchCats()
    }
//    viewModel.fetchCats()
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
            val list=ArrayList<GetImageResponseItem>()
            list.addAll(state.listOfCats)
            ProductListSuccess(listOfProducts = list)
        }
        is CatsListState.Error->{
            ProductListError(exception = state.exception.message)
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            state.exception.printStackTrace()
        }

    }

}

@Composable
fun ListUI(arrayList:ArrayList<GetImageResponseItem>) {
    LazyColumn {
        items(arrayList.size){
            CardUI(arrayList[it])
        }
    }
}

@Composable
fun CardUI(getImageResponseItem: GetImageResponseItem) {
    Card(
        modifier = Modifier
            .height(getImageResponseItem.height.dp)
            .width(getImageResponseItem.width.dp)
            .padding(12.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(getImageResponseItem.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}

@Composable
fun ProductListSuccess(listOfProducts: ArrayList<GetImageResponseItem>) {
    val context= LocalContext.current
    ListUI(arrayList = listOfProducts)
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


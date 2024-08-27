package uz.turgunboyevjurabek.examplecleanearch.data.dto

import com.google.gson.annotations.SerializedName
import uz.turgunboyevjurabek.examplecleanearch.domein.models.Favourite

data class GetCatsDto(
    @SerializedName("breeds")
    val breeds: List<Any>,
    @SerializedName("favourite")
    val favourite: Favourite,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class GetCatsDtoList(
    val catsDto: List<GetCatsDto>
)

package uz.turgunboyevjurabek.examplecleanearch.data.mappers

import com.google.gson.annotations.SerializedName
import uz.turgunboyevjurabek.examplecleanearch.data.dto.GetCatsDto
import uz.turgunboyevjurabek.examplecleanearch.data.dto.GetCatsDtoList
import uz.turgunboyevjurabek.examplecleanearch.domein.models.Favourite
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem

fun GetCatsDto.toDomain() = GetImageResponseItem(
    breeds=breeds,
    favourite=favourite?: Favourite(), // Null bo'lsa, default obyektni berish
    height=height,
    id=id,
    url=url,
    width=width
)
fun List<GetCatsDto>.toDomain(): List<GetImageResponseItem> = this.map { it.toDomain() }

//fun GetCatsDtoList.toDomain(): List<GetImageResponseItem>  = this.catsDto.map { it.toDomain() }

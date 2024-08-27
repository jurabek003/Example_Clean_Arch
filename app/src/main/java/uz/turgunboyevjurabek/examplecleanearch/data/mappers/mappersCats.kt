package uz.turgunboyevjurabek.examplecleanearch.data.mappers

import com.google.gson.annotations.SerializedName
import uz.turgunboyevjurabek.examplecleanearch.data.dto.GetCatsDto
import uz.turgunboyevjurabek.examplecleanearch.data.dto.GetCatsDtoList
import uz.turgunboyevjurabek.examplecleanearch.domein.models.Favourite
import uz.turgunboyevjurabek.examplecleanearch.domein.models.GetImageResponseItem

fun GetCatsDtoList.toDomain() = this.catsDto.map { it.toDomain() }

fun GetCatsDto.toDomain() = GetImageResponseItem(
    breeds=breeds,
    favourite=favourite,
    height=height,
    id=id,
    url=url,
    width=width
)
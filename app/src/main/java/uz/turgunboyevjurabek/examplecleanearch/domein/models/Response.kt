package uz.turgunboyevjurabek.examplecleanearch.domein.models

data class Response(
    val country: Country,
    val league: League,
    val seasons: List<Season>
)
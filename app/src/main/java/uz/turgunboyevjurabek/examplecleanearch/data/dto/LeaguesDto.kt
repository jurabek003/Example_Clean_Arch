package uz.turgunboyevjurabek.examplecleanearch.data.dto

data class LeaguesDtoList(
    val leaguesDto:List<LeaguesDto>,
)
data class LeaguesDto(
    val errors: List<Any>? = null, // List could be null
    val get: String? = null,        // String could be null
    val paging: Paging? =null,      // Paging object could be null
    val parameters: List<Any>? = null, // List could be null
    val response: List<Response>? = null, // List could be null
    val results: Int? = null          // Int could be null
)


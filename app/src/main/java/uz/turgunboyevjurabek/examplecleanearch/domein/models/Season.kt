package uz.turgunboyevjurabek.examplecleanearch.domein.models

data class Season(
    val coverage: Coverage,
    val current: Boolean,
    val end: String,
    val start: String,
    val year: Int
)
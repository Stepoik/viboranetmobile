package stepan.gorokhov.viboranet.tests.api.models

enum class Vote {
    POSITIVE,
    NEGATIVE;

    fun toInt(): Int {
        return when (this) {
            POSITIVE -> 1
            NEGATIVE -> -1
        }
    }
}
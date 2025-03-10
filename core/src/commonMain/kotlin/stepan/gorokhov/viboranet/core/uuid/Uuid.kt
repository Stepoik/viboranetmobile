package stepan.gorokhov.viboranet.core.uuid

expect class Uuid {
    companion object {
        fun generate(): String
    }
}
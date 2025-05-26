package stepan.gorokhov.viboranet.core.list

operator fun <T : Any?> T.plus(list: List<T>): List<T> {
    return list.toMutableList().apply {
        add(0, this@plus)
    }
}
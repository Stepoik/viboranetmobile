package stepan.gorokhov.viboranet

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
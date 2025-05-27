package stepan.gorokhov.viboranet.coreui.coil

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.network.ktor2.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient

fun authorizedImageLoader(httpClient: HttpClient, context: PlatformContext): ImageLoader {
    return ImageLoader.Builder(context).components {
        add(KtorNetworkFetcherFactory(httpClient))
    }.build()
}
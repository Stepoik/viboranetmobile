package stepan.gorokhov.viboranet.common.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import stepan.gorokhov.viboranet.common.api.repositories.ImageRepository
import stepan.gorokhov.viboranet.common.data.network.UploadImageResponse
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants

private const val BASE_IMAGE_URL = "${NetworkConstants.BASE_URL}/images"

class ImageRepositoryImpl(
    private val httpClient: HttpClient
) : ImageRepository {
    override suspend fun uploadImage(image: ByteArray): Result<String> {
        return runCatching {
            val response = httpClient.post(BASE_IMAGE_URL) {
                val body = MultiPartFormDataContent(
                    formData {
                        append("image", image, Headers.build {
                            append(HttpHeaders.ContentType, "image/jpeg")
                            append(
                                HttpHeaders.ContentDisposition, "filename=\"image.jpg\""
                            )
                        })
                    }
                )
                setBody(body)
            }.body<UploadImageResponse>()
            response.imageId
        }
    }
}
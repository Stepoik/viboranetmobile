package stepan.gorokhov.viboranet.tests.data.preview.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.tests.data.preview.network.models.ResultDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.SaveResultRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.models.SaveResultResponse

private const val BASE_URL = "${NetworkConstants.BASE_URL}/results"

class ResultApi(
    private val httpClient: HttpClient
) {
    suspend fun saveResult(request: SaveResultRequest): SaveResultResponse {
        return httpClient.post(BASE_URL) {
            setBody(request)
        }.body()
    }

    suspend fun getResult(id: String): ResultDto {
        return httpClient.get("$BASE_URL/$id").body()
    }
}
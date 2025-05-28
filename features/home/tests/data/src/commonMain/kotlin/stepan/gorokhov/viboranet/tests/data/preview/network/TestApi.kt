package stepan.gorokhov.viboranet.tests.data.preview.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants.BASE_URL
import stepan.gorokhov.viboranet.tests.data.preview.network.models.CreateTestRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.models.CreateTestResponse
import stepan.gorokhov.viboranet.tests.data.preview.network.models.SaveTestResultRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.UpdateTestRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.models.VoteForTestRequest

private const val BASE_TEST_URL = "$BASE_URL/tests"

class TestApi(
    private val httpClient: HttpClient
) {
    suspend fun getTests(
        offset: Long,
        limit: Long,
        title: String? = null,
        authorId: String? = null
    ): List<TestDto> {
        return httpClient.get(BASE_TEST_URL) {
            parameter("offset", offset)
            parameter("limit", limit)
            title?.let {
                parameter("title", it)
            }
            authorId?.let {
                parameter("author_id", it)
            }
        }.body()
    }

    suspend fun getTest(id: String): TestDto {
        return httpClient.get("$BASE_TEST_URL/$id").body()
    }

    suspend fun voteForTest(id: String, request: VoteForTestRequest) {
        httpClient.post("$BASE_TEST_URL/$id/votes") {
            setBody(request)
        }
    }

    suspend fun createTest(request: CreateTestRequest): CreateTestResponse {
        return httpClient.post(BASE_TEST_URL) {
            setBody(request)
        }.body()
    }

    suspend fun updateTest(testId: String, request: UpdateTestRequest): CreateTestResponse {
        return httpClient.put("$BASE_TEST_URL/$testId") {
            setBody(request)
        }.body()
    }
}
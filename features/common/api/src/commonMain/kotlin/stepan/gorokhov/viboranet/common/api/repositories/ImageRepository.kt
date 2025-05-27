package stepan.gorokhov.viboranet.common.api.repositories

interface ImageRepository {
    suspend fun uploadImage(image: ByteArray): Result<String>
}
package stepan.gorokhov.viboranet.coredata.network

interface TokenHolder {
    suspend fun getTokens(): BearerTokens?
}
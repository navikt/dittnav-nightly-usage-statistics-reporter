package no.nav.personbruker.dittnav.metrics.config

import io.ktor.client.HttpClient
import io.ktor.client.features.timeout
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

suspend inline fun <reified T> HttpClient.get(url: URL, azureTokenFetcher: AzureTokenFetcher): T = withContext(
    Dispatchers.IO) {
    request<T> {
        url(url)
        method = HttpMethod.Get
        header(HttpHeaders.Authorization, "Bearer ${azureTokenFetcher.getAccessToken()}")
        timeout {
            socketTimeoutMillis = 60 * 1000
            connectTimeoutMillis = 10 * 1000
            requestTimeoutMillis = 120 * 1000
        }
    }
}

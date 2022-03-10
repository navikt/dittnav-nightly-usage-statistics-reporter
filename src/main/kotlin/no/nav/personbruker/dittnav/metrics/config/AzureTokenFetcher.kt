package no.nav.personbruker.dittnav.metrics.config

import no.nav.tms.token.support.azure.exchange.AzureServiceBuilder

class AzureTokenFetcher(private val eventHandlerAppEnvironmentDetails: String) {
    private val azureService = AzureServiceBuilder.buildAzureService(
        cachingEnabled = true,
        maxCachedEntries = 100,
        cacheExpiryMarginSeconds = 10,
        enableDefaultProxy = false
    )

    suspend fun getAccessToken(): String {
        return azureService.getAccessToken(eventHandlerAppEnvironmentDetails)
    }
}

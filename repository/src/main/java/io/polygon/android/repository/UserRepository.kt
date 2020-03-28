package io.polygon.android.repository

import io.polygon.kotlin.persistence.sharedprefs.PolygonKeySPAO
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepository : KoinComponent {

    private val polygonKeySPAO by inject<PolygonKeySPAO>()

    val hasApiKey
        get() = polygonKeySPAO.polygonApiKey != null

    fun setApiKey(key: String) {
        polygonKeySPAO.polygonApiKey = key
    }
}
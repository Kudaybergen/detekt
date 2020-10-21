package io.gitlab.arturbosch.detekt.core.config

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Notification
import io.gitlab.arturbosch.detekt.api.internal.ValidatableConfiguration
import io.gitlab.arturbosch.detekt.api.internal.validateConfig

@Suppress("UNCHECKED_CAST")
class DisabledAutoCorrectConfig(private val wrapped: Config) : Config, ValidatableConfiguration {

    override fun subConfig(key: String): Config = DisabledAutoCorrectConfig(wrapped.subConfig(key))

    override fun <T : Any> valueOrDefault(key: String, default: T): T = when (key) {
        "autoCorrect" -> false as T
        else -> wrapped.valueOrDefault(key, default)
    }

    override fun <T : Any> valueOrNull(key: String): T? = when (key) {
        "autoCorrect" -> false as T
        else -> wrapped.valueOrNull(key)
    }

    override fun validate(baseline: Config, excludePatterns: Set<Regex>): List<Notification> =
        validateConfig(wrapped, baseline, excludePatterns)
}
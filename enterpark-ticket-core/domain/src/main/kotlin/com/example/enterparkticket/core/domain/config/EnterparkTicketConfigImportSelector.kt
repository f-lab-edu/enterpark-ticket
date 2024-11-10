package com.example.enterparkticket.core.domain.config

import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class EnterparkTicketConfigImportSelector : DeferredImportSelector {

    override fun selectImports(metadata: AnnotationMetadata): Array<String> {
        return getValues(metadata)
            .map { it.configClass.name }
            .toTypedArray()
    }

    private fun getValues(metadata: AnnotationMetadata): Array<EnterparkTicketConfigGroup> {
        val attributes =
            metadata.getAnnotationAttributes(EnableEnterparkTicketConfig::class.java.name)
        val values = attributes?.get("value") as? Array<*>
        return values?.filterIsInstance<EnterparkTicketConfigGroup>()?.toTypedArray()
            ?: emptyArray()
    }
}

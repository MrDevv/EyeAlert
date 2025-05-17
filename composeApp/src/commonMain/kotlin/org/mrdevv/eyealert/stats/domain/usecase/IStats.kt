package org.mrdevv.eyealert.stats.domain.usecase

import org.mrdevv.eyealert.evaluation.model.dto.CrearEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionDTO
import org.mrdevv.eyealert.stats.domain.dto.ResponseIndiceConocimientoDTO
import org.mrdevv.eyealert.stats.domain.dto.ResponseTasaCiertoDTO
import org.mrdevv.eyealert.stats.domain.dto.ResponseTiempoPromedioDTO

interface IStats {
    public fun obtenerTasaAcierto(onResponse: (ResponseTasaCiertoDTO?) -> Unit)
    public fun obtenerTiempoPromedio(onResponse: (ResponseTiempoPromedioDTO?) -> Unit)
    public fun obtenerIndiceConocimiento(onResponse: (ResponseIndiceConocimientoDTO?) -> Unit)
}
package org.mrdevv.eyealert.configCuestionario.model.usecase

import org.mrdevv.eyealert.configCuestionario.model.dto.CreateCuestionarioConocimientos
import org.mrdevv.eyealert.configCuestionario.model.dto.ResponseConfigCuestionario
import org.mrdevv.eyealert.configCuestionario.model.dto.ResponseCuestionarioConocimientosDTO
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionByUser

interface IConfigCuestionario {

    fun getDiasEspera(onResponse: (ResponseConfigCuestionario?) -> Unit)

    fun saveCuestionarioConocimientos(cuestionarioConocimientos: CreateCuestionarioConocimientos ,onResponse: (ResponseCuestionarioConocimientosDTO?) -> Unit)
}
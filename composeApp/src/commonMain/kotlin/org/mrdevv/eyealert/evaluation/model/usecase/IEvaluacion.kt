package org.mrdevv.eyealert.evaluation.model.usecase

import org.mrdevv.eyealert.evaluation.model.dto.CrearEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.RequestRespuestasCuestionario
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDetailEvaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionByUser
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.ResponseNivelRiesgo
import org.mrdevv.eyealert.evaluation.model.dto.ResultadoEspecialista
import org.mrdevv.eyealert.evaluation.model.dto.ResponseResultadoEspecialista

interface IEvaluacion {

    fun getEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
    fun getLastEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
    fun getLastWeekEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
    fun getLastMonthEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)

    fun getDetailEvaluacion(idUser: Long, onResponse: (ResponseDetailEvaluacion?) -> Unit)

    fun getNivelRiesgoEvaluacion(respuestasCuestionario: RequestRespuestasCuestionario, onResponse: (ResponseNivelRiesgo?) -> Unit )

    fun crearEvaluacion(evaluacionDTO: CrearEvaluacionDTO, onResponse: (ResponseEvaluacionDTO?) -> Unit)
    fun actualizarResultadoEspecialista(id: Long, resultadoEspecialistaDto: ResultadoEspecialista, onResponse: (ResponseResultadoEspecialista?) -> Unit)
}
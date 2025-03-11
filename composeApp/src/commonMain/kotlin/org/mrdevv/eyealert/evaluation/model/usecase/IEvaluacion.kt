package org.mrdevv.eyealert.evaluation.model.usecase

import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionByUser

interface IEvaluacion {

    fun getEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
    fun getLastEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
    fun getLastWeekEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
}
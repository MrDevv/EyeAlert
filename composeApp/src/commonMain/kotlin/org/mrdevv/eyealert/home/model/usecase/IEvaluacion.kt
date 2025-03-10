package org.mrdevv.eyealert.home.model.usecase

import org.mrdevv.eyealert.home.model.dto.ResponseEvaluacionByUser

interface IEvaluacion {

    fun getEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)
    fun getLastEvaluacionesByUser(idUser: Long, onResponse: (ResponseEvaluacionByUser?) -> Unit)

}
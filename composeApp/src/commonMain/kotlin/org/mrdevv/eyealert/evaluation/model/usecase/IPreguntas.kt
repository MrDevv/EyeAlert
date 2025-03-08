package org.mrdevv.eyealert.evaluation.model.usecase

import org.mrdevv.eyealert.evaluation.model.dto.ResponsePreguntas

interface IPreguntas {
    fun getListPreguntas(onResponse: (ResponsePreguntas?) -> Unit)
}
package org.mrdevv.eyealert.InformativeData.model.usecase

import org.mrdevv.eyealert.InformativeData.model.dto.ResponseDatosInformativos

interface IDatoInformativo {

    fun getTresDatosInformativosAleatorios(onResponse: (ResponseDatosInformativos?) -> Unit)

    fun getAllDatosInformativos(onResponse: (ResponseDatosInformativos?) -> Unit)
}
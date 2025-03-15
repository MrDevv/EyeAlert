package org.mrdevv.eyealert

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.ktor.client.HttpClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.InformativeData.data.DatoInformativoImpl

import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo

class NotificationWorker (
    appContext: Context, workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val datosInformativo = DatoInformativoImpl()

        var tituloDato: String = ""
        var mensajeDato: String = ""
        var urlFuente: String = ""

        val data = datosInformativo.getDatoInformativoAleatorio { response ->
            if (response != null) {
                if (response.code == 200){
                    tituloDato = response.data.titulo
                    mensajeDato = response.data.descripcion
                    urlFuente = response.data.fuente

                    showNotificacion(applicationContext, "¿Sabías que...? \uD83D\uDC40", mensajeDato, urlFuente)
                }
            }else{
                println("Ocurrió un error al momento de traer el dato informativo aleatorio para la notificacion")
            }
        }
            return Result.success()
    }

    @SuppressLint("MissingPermission", "NotificationPermission")
    private fun showNotificacion(context: Context, title: String, message: String, urlFuente: String){
        val channelId = "daily_notifications"
        val notificationId = 1

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlFuente))

        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager = context.getSystemService(NotificationManager::class.java)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.deleteNotificationChannel(channelId)


            val channel = NotificationChannel(
                channelId,
                "Daily Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal para notificaciones flotantes"
                enableLights(true)
                lightColor = android.graphics.Color.RED // 🔥 Habilita luces
                enableVibration(true) // 🔥 Asegura que haya vibración
                setSound(null, null)
            }

            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle(title)
            .setContentText(message)
            .setSubText("Presiona para ver más")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

}
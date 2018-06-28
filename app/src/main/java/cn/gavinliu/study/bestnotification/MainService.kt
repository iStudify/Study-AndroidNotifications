package cn.gavinliu.study.bestnotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.support.v4.app.NotificationCompat

class MainService : Service() {

    lateinit var notificationManager: NotificationManager

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val activityIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        val notification = NotificationCompat.Builder(this, MainActivity.notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .setColorized(true)
                .addAction(R.drawable.ic_launcher_foreground, "123", pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "456", pendingIntent)
                .build()

        startForeground(102, notification)

        return super.onStartCommand(intent, flags, startId)
    }

}
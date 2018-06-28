package cn.gavinliu.study.bestnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.Person
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
                notificationChannelId,
                notificationChannelName,
                NotificationManager.IMPORTANCE_DEFAULT)

        notificationManager.createNotificationChannel(channel)
    }

    fun notification(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED)
                .setColorized(false)
                .setAutoCancel(true)
                .setShowWhen(true)
                .addAction(R.drawable.ic_launcher_foreground, "123", pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "456", pendingIntent)
                .build()

        notificationManager.notify(101, notification)
    }

    fun notificationService(view: View) {
        startService(Intent(this, MainService::class.java))
    }

    fun notificationLargeImage(view: View) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.images)

        val notification = NotificationCompat.Builder(this, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED)
                .setColorized(false)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setLargeIcon(bitmap)
                .setStyle(NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null))
                .build()

        notificationManager.notify(101, notification)
    }

    fun notificationLargeText(view: View) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.images)
        val text = "6月25日，陕西西安。一高校毕业散伙饭上，全班同学制作了一条横幅，祝贺学霸李满月单身五年毕业。当事人满月不仅不觉得扎心，还开玩笑说这都是同学们对她满满的爱。"

        val notification = NotificationCompat.Builder(this, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED)
                .setColorized(false)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setLargeIcon(bitmap)
                .setStyle( NotificationCompat.BigTextStyle()
                        .setSummaryText("456")
                        .bigText(text))
                .build()

        notificationManager.notify(101, notification)
    }

    fun notificationInbox(view: View) {

        val notification = NotificationCompat.Builder(this, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED)
                .setColorized(false)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setStyle(NotificationCompat.InboxStyle()
                        .addLine("Hello")
                        .addLine("你好"))
                .build()

        notificationManager.notify(101, notification)
    }

    fun notificationMessage(view: View) {
        val message1 = NotificationCompat.MessagingStyle.Message("Hello",
                System.currentTimeMillis(),
                Person.Builder()
                        .setName("You")
                        .build())

        val message2 = NotificationCompat.MessagingStyle.Message("你好",
                System.currentTimeMillis(),
                Person.Builder()
                        .setName("Me")
                        .build())

        val notification = NotificationCompat.Builder(this, notificationChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED)
                .setColorized(false)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setStyle(NotificationCompat.MessagingStyle("Me1")
                        .addMessage(message1)
                        .addMessage(message2))
                .build()

        notificationManager.notify(101, notification)
    }

    companion object {
        val notificationChannelId: String = "default"
        val notificationChannelName: String = "默认"
    }
}

package egolabsapps.basicodemine.videolayout

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    lateinit var videoLayout: VideoLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        videoLayout = findViewById(R.id.videoLayout)
        Handler().postDelayed({

            videoLayout.setPathOrUrl("loginvideotype4.mp4")

        }, 4000)
    }

    fun thanks(v: View) {
        startActivity(Intent(this, Main2Activity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        videoLayout.onDestroyVideoLayout()
    }

    override fun onPause() {
        super.onPause()
        videoLayout.onPauseVideoLayout()
    }

    override fun onResume() {
        super.onResume()
        videoLayout.onResumeVideoLayout()
    }
}

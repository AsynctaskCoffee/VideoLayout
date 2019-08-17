package egolabsapps.basicodemine.videolayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class Main2Activity : AppCompatActivity() {
    lateinit var frameLayout: FrameLayout
    lateinit var videoLayout: VideoLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initViews()
    }


    fun initViews() {
        frameLayout = findViewById(R.id.frameLayout)
        videoLayout = VideoLayout(this)
        videoLayout.setGravity(VideoLayout.VGravity.centerCrop)
        videoLayout.setIsLoop(true)
        videoLayout.setPathOrUrl("loginvideotype3.mp4")
        frameLayout.addView(videoLayout)
    }
}

package egolabsapps.basicodemine.videolayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fix_test.*

class FixTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fix_test)
        test?.setSound(true)
        test?.setPathOrUrl("lp_chesterr.mp4")
    }
}
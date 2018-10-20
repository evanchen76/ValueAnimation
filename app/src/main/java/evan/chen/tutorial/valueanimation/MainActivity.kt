package evan.chen.tutorial.valueanimation

import android.animation.AnimatorInflater
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.ValueAnimator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.Keyframe


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animButton.setOnClickListener {
            //animationByCodeValue()
            animationWithFrame()
        }
    }


    private fun animationByCode() {
        val anim = ObjectAnimator.ofFloat(textView, "rotation", 0.0f, 270.0f)
        anim.duration = 1000
        anim.start()
    }

    private fun animationWithFrame() {
        val frame0 = Keyframe.ofFloat(0f, 0f)
        val frame1 = Keyframe.ofFloat(0.1f, -30f)
        val frame2 = Keyframe.ofFloat(0.3f, 30f)
        val frame3 = Keyframe.ofFloat(0.6f, -30f)
        val frame4 = Keyframe.ofFloat(0.8f, 30f)
        val frame5 = Keyframe.ofFloat(1f, 0f)
        val frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5)
        val animator = ObjectAnimator.ofPropertyValuesHolder(textView, frameHolder)
        animator.duration = 500
        animator.start()

    }

    private fun animation() {
        val animation: ValueAnimator = AnimatorInflater.loadAnimator(this, R.animator.anim_value) as ValueAnimator

        animation.addUpdateListener { animation ->
            val value = animation.animatedValue as? Int
            if (value != null) {
                textView.width = value
                textView.requestLayout()
            }
        }

        animation.start()
    }

    private fun animationByCodeValue() {
        //建立一個1.0~0.1之間漸變的動畫
        val animation = ValueAnimator.ofFloat(1.0f, 0.1f)
        //監聽動畫值的改變
        animation.addUpdateListener { animation ->
            //在這裡取得從1.0~0.1之間的每個值
            textView.alpha = animation.animatedValue as Float
            textView.requestLayout()
        }
        //動畫期間
        animation.duration = 2000
        animation.start()
    }
}

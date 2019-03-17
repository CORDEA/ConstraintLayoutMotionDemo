package jp.cordea.constraintlayoutmotiondemo

import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import jp.cordea.constraintlayoutmotiondemo.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySubBinding>(
            this,
            R.layout.activity_sub
        )

        var isOpen = false
        val container = binding.content.container
        val start = ConstraintSet().also { it.clone(container) }
        val end = ConstraintSet().also { it.clone(this, R.layout.content_sub_end) }
        val transition = ChangeBounds().apply {
            duration = 500
            interpolator = BounceInterpolator()
        }
        binding.content.fab.setOnClickListener {
            (if (isOpen) start else end).applyTo(container)
            TransitionManager.beginDelayedTransition(container, transition)
            isOpen = !isOpen
        }
    }
}

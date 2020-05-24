package com.shway.character

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import com.divyanshu.draw.widget.DrawView

class MainActivity : AppCompatActivity() {

    private var drawView: DrawView? = null
    private var clearButton: Button? = null
    private var predictedTextView: TextView? = null
    private var characterClassifier = CharacterClassifier(this)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup view instances
        drawView = findViewById(R.id.draw_view)
        drawView?.setStrokeWidth(70.0f)
        drawView?.setColor(Color.BLACK)
        drawView?.setBackgroundColor(Color.WHITE)
        clearButton = findViewById(R.id.clear_button)
        predictedTextView = findViewById(R.id.predicted_text)

        // Setup clear drawing button
        clearButton?.setOnClickListener {
            drawView?.clearCanvas()
            predictedTextView?.text = "Draw A Character (0 to 9 or A - Z)"
        }

        // Setup classification trigger so that it classify after every stroke drew
        drawView?.setOnTouchListener { _, event ->
            // As we have interrupted DrawView's touch event,
            // we first need to pass touch events through to the instance for the drawing to show up
            drawView?.onTouchEvent(event)

            // Then if user finished a touch event, run classification
            if (event.action == MotionEvent.ACTION_UP) {
                classifyDrawing()
            }

            true
        }

        // Setup digit classifier
        characterClassifier
                .initialize()
                .addOnFailureListener { e -> Log.e(TAG, "Error to setting up Character classifier.", e) }
    }

    override fun onDestroy() {
        characterClassifier.close()
        super.onDestroy()
    }

    private fun classifyDrawing() {
        val bitmap = drawView?.getBitmap()

        if ((bitmap != null) && (characterClassifier.isInitialized)) {
            characterClassifier
                    .classifyAsync(bitmap)
                    .addOnSuccessListener { resultText -> predictedTextView?.text = resultText }
                    .addOnFailureListener { e ->
                        predictedTextView?.text = "Error classifying drawing:" + e.localizedMessage
                        Log.e(TAG, "Error classifying drawing.", e)
                    }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
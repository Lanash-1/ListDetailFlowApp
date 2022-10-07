package com.example.listdetailflowapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

class ItemCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var paintText: Paint

    init {
        setUpPaint()
    }

    private fun setUpPaint() {
        paintText = Paint()
        paintText.color = Color.RED
        paintText.textSize = 30F
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText("Date created: ", 0F, 30F, paintText)
        canvas.drawText("Date created: ", 200F, 30F, paintText)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

    }
}

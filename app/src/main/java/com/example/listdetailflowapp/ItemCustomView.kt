package com.example.listdetailflowapp

import android.content.Context
import android.icu.util.Measure
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup

class ItemCustomView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {

//        val childCount = childCount
//        var prevChildRight = 0
//        val prevChildBottom = 0
//        val maxWidth = measuredWidth
//
//
//        for(i in 0 until childCount){
//            val child: View = getChildAt(i)
//
//            if(prevChildRight+child.measuredWidth < maxWidth) {
//
//                if(i != 0){
//                    child.setPadding(20, 0,0,0)
//                }
//
//                child.layout(
//                    prevChildRight,
//                    prevChildBottom,
//                    prevChildRight + child.measuredWidth,
//                    prevChildBottom + child.measuredHeight
//                )
//                prevChildRight += child.measuredWidth
//            }
//        }

        val childCount = childCount

        var childLeft = 0
        var childTop = 10
        var childRight = 0
        var childBottom = 0

        val requiredWidth = measuredWidth

        for(i in 0 until childCount){
            val child: View = getChildAt(i)

            if(child.visibility != View.GONE) {


                childRight += child.measuredWidth
                childBottom = child.measuredHeight

                println("CHILD RIGHT = $childRight")

                child.layout(
                    childLeft,
                    childTop,
                    childRight,
                    childBottom
                )
                childLeft = childRight
            }

        }

        println("OUTSIDE RIGHT: $childRight")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

//        val givenWidth = MeasureSpec.getSize(widthMeasureSpec)
//
//        var totalWidth = 0
//        var totalHeight = 0
//        val childCount = childCount
//
//        for(i in 0 until childCount){
//            val child: View = getChildAt(i)
//            if(i != 0){
//                child.setPadding(20,0,0,0)
//            }
//            measureChild(child, widthMeasureSpec, heightMeasureSpec)
//            totalWidth += child.measuredWidth
//
//            if(totalWidth > givenWidth){
//                totalWidth -= child.measuredWidth
//                child.visibility = View.GONE
//            }else{
//                if(child.measuredHeight > totalHeight){
//                    totalHeight = child.measuredHeight
//                }
//            }
//        }
//
//        setMeasuredDimension(givenWidth,totalHeight)

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        Log.d(null, "parent width: $parentWidth")


        var requiredWidth = 0
        var requiredHeight = 0
        var childCount = childCount

        for(i in 0 until childCount){
            val child: View = getChildAt(i)
            if(child.visibility != View.GONE){
                child.setPadding(0,0,20,0)
                measureChild(child, widthMeasureSpec, heightMeasureSpec)

                Log.d(null, "Measured child width at position - $i == ${child.measuredWidth} and padding - ${child.paddingRight}")

                requiredWidth += child.measuredWidth + child.paddingRight
                if(requiredWidth > parentWidth){
                    requiredWidth -= child.measuredWidth + child.paddingRight
                    child.visibility = View.GONE
                }else{
                    if(requiredHeight < child.measuredHeight) requiredHeight = child.measuredHeight + 20
                }
            }
        }

        Log.d(null, "Required width:  $requiredWidth")

        setMeasuredDimension(requiredWidth, requiredHeight)

    }
}

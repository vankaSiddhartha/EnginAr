package com.vanka.enginar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.localScale
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.math.Position


class MainActivity : AppCompatActivity() {
    lateinit var sceneView:ArSceneView
    lateinit var placeButton:ExtendedFloatingActionButton
    lateinit var modelNode:ArModelNode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sceneView = findViewById<ArSceneView>(R.id.sceneView)
        placeButton = findViewById(R.id.fab)
        modelNode=  ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "models/earth.glb"
            ){
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged={
                placeButton.isGone
            }
            scale = Float3(0.1f, 0.1f, 0.1f)


        }
        sceneView.addChild(modelNode)
        placeButton.setOnClickListener {
            modelNode?.anchor()
            sceneView.planeRenderer.isVisible = true
        }
    }
}
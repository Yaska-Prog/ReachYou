package com.example.reachyou.ui.screen.scannerBISINDO

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.reachyou.R
import com.example.reachyou.ui.component.button.BackButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun ScannerBISINDOScreen(
    modifier: Modifier = Modifier,
    outputDirectory: File,
    onError: (ImageCaptureException) -> Unit,
    index: Int,
    navigateToHome: () -> Unit
) {
    //0: BISINDO
    //1: Warna
    //2: Uang
    //3: Objek
    var currentIndex by rememberSaveable {
        mutableIntStateOf(index)
    }
    val listIcon = listOf<Int>(R.drawable.scanner_bisindo, R.drawable.scanner_color, R.drawable.scanner_uang, R.drawable.scanner_objek)
    val lensFacing = CameraSelector.LENS_FACING_FRONT
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val preview = Preview.Builder().build()
    val previewView = remember {PreviewView(context)}
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector: MutableState<CameraSelector> = remember {
        mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA)
    }
    var selectedImageByUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val executor = Executors.newSingleThreadExecutor()
    BackHandler {
        if(selectedImageByUri != null){
            selectedImageByUri = null
        }
        else{
            navigateToHome()
        }
    }
    var leftButtonIcon by rememberSaveable {
        mutableIntStateOf(if(currentIndex >= 1) currentIndex-1 else 10)
    }
    var rightButtonIcon by rememberSaveable {
        mutableIntStateOf(if(currentIndex < 3) currentIndex+1 else 10)
    }
    val leftButtonScale by animateFloatAsState(
        targetValue = 0.8f,
        animationSpec = tween(durationMillis = 300)
    )

    val rightButtonScale by animateFloatAsState(
        targetValue = 0.8f,
        animationSpec = tween(durationMillis = 300)
    )
    if(selectedImageByUri == null){
        LaunchedEffect(lensFacing) {
            val cameraProvider = context.getCameraProvider()
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector.value,
                preview,
                imageCapture
            )

            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
        takePhoto(
            filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
            imageCapture = imageCapture,
            outputDirectory = outputDirectory,
            executor = executor,
            onImageCaptured = {uri ->
            },
            onError = onError
        )
        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        if (dragAmount.x < -80 && currentIndex <= 2) {
                            currentIndex += 1
                            rightButtonIcon = if (currentIndex < 3) currentIndex + 1 else 10
                            leftButtonIcon = if (currentIndex >= 1) currentIndex - 1 else 10
                        }
                        if (dragAmount.x > 80 && currentIndex <= 3 && currentIndex > 0) {
                            currentIndex -= 1
                            rightButtonIcon = if (currentIndex < 3) currentIndex + 1 else 10
                            leftButtonIcon = if (currentIndex >= 1) currentIndex - 1 else 10
                        }
                    }
                }
        ) {
            AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                BackButton(onClick = navigateToHome, modifier = Modifier.padding(20.dp))
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd){
                IconButton(onClick = {
                    cameraSelector.value =
                        if(cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
                        else CameraSelector.DEFAULT_BACK_CAMERA
                    lifecycleOwner.lifecycleScope.launch {
                        val cameraProvider = context.getCameraProvider()
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector.value,
                            preview,
                            imageCapture
                        )
                    }
                },  modifier = Modifier.padding(20.dp)) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.switch_camera),
                        contentDescription = "Switch Camera",
                        tint = Color.Unspecified,
                    )
                }
            }
            if(rightButtonIcon != 10){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
                    IconButton(
                        modifier = modifier
                            .padding(20.dp)
                            .size(70.dp)
                            .scale(rightButtonScale),
                        onClick = {
                            currentIndex += 1
                            rightButtonIcon = if(currentIndex < 3) currentIndex + 1 else 10
                            leftButtonIcon = if(currentIndex >= 1) currentIndex -1 else 10
                        }) {
                        Icon( imageVector = ImageVector.vectorResource(id = listIcon[rightButtonIcon as Int]),
                            contentDescription = "Right Buttton",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
            if(leftButtonIcon != 10){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
                    IconButton(
                        modifier = modifier
                            .padding(20.dp)
                            .size(70.dp)
                            .scale(rightButtonScale),
                        onClick = {
                            currentIndex -= 1
                            rightButtonIcon = if(currentIndex < 3) currentIndex + 1 else 10
                            leftButtonIcon = if(currentIndex >= 1) currentIndex -1 else 10
                        }) {
                        Icon( imageVector = ImageVector.vectorResource(id = listIcon[leftButtonIcon as Int]),
                            contentDescription = "Right Buttton",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
            IconButton(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(91.dp),
                onClick = {
                    takePhoto(
                        filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
                        imageCapture = imageCapture,
                        outputDirectory = outputDirectory,
                        executor = executor,
                        onImageCaptured = {uri ->
                            selectedImageByUri = uri
                        },
                        onError = onError
                    )
                },
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = listIcon[currentIndex]),
                        contentDescription = "Take picture",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(700.dp)
//                            .padding(1.dp)
//                            .border(1.dp, Color.White, CircleShape)
                    )
                }
            )
        }
    }
    else{
        AsyncImage(model = selectedImageByUri, contentDescription = "Photo Taken")
    }
}

private fun takePhoto(
    filenameFormat: String,
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {

    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(outputOptions, executor, object: ImageCapture.OnImageSavedCallback {
        override fun onError(exception: ImageCaptureException) {
            Log.e("kilo", "Take photo error:", exception)
            onError(exception)
        }

        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val savedUri = Uri.fromFile(photoFile)

            onImageCaptured(savedUri)
        }
    })
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}
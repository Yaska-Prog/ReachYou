package com.example.reachyou

import android.Manifest
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.reachyou.ui.theme.ReachYouTheme
import java.io.File
import java.util.concurrent.ExecutorService

class MainActivity : ComponentActivity() {
    private lateinit var outputDirectory: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        outputDirectory = getOutputDirectory()
        setContent {
            ReachYouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetReachYouApp(outputDirectory = outputDirectory)
                }
            }
        }
    }
    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    private val requestMultiplePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){resultMaps ->
        var permisionGranted = false
        resultMaps.forEach{
            if(it.value == true){
                permisionGranted = it.value
            } else{
                Toast.makeText(
                    applicationContext, "Tidak mendapatkan permission, aplikasi akan dimatikan.", Toast.LENGTH_SHORT)
                finish()
            }
        }

    }
    private var granted = false
    private fun checkPermission(){
        if(hasCameraPermission() == PERMISSION_GRANTED && hasFilePermission() == PERMISSION_GRANTED){
            granted = true
        }
        else{
            requestMultiplePermissionLauncher.launch(arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ))
        }
    }
    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    private fun hasFilePermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReachYouTheme {
        Greeting("Android")
    }
}
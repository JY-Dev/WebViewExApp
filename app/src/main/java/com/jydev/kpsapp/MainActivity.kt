package com.jydev.kpsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val baseUrl = "https://fake.com/"
    private var BACK_BTN_TIME = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWeb()
    }

    private fun initWeb(){
        web_view.webViewClient = WebViewClient() // 새창 안뜨게
        web_view.loadUrl(baseUrl)
        with(web_view.settings){
            javaScriptEnabled = true
            setSupportMultipleWindows(false)
            javaScriptCanOpenWindowsAutomatically = false
            loadWithOverviewMode = true
            loadWithOverviewMode = true
            setSupportZoom(true)
            builtInZoomControls = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }
    }

    private fun gapTimeCheck() : Boolean = getGapTime() in 0..2000
    private fun getGapTime() : Long = System.currentTimeMillis() - BACK_BTN_TIME
    override fun onBackPressed() {
        when {
            web_view.canGoBack() -> web_view.goBack()
            gapTimeCheck() -> super.onBackPressed()
            else -> {
                BACK_BTN_TIME = System.currentTimeMillis()
                Toast.makeText(applicationContext,"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
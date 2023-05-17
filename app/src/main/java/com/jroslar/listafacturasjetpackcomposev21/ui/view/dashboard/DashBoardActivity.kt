package com.jroslar.listafacturasjetpackcomposev21.ui.view.dashboard


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jroslar.listafacturasjetpackcomposev21.ui.screens.dashboard.DashBoardScreen

class DashBoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashBoardScreen()
        }
    }
}

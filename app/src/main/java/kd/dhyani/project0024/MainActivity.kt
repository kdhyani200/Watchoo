package kd.dhyani.project0024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kd.dhyani.project0024.navigation.AppNavigation
import kd.dhyani.project0024.di.appModule
import kd.dhyani.project0024.ui.theme.Project0024Theme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            Project0024Theme {
                AppNavigation()
            }
        }
    }
}

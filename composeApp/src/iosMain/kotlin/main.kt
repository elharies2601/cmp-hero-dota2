import androidx.compose.ui.window.ComposeUIViewController
import id.elharies.herodota.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }

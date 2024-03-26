import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.window.singleWindowApplication
import ke.dennotech.composebook.App
import ke.dennotech.composebook.ui.ListControl.Companion.rememberListControl
import ke.dennotech.composebook.ui.decorators.DensityDecorator
import ke.dennotech.composebook.ui.decorators.devicepreview.DevicePreviewDecorator
import ke.dennotech.composebook.ui.decorators.devicepreview.DeviceSpec

fun main() = singleWindowApplication {

    MaterialTheme {
        App(
            listOf(
                DevicePreviewDecorator(DeviceSpec.All),
                DensityDecorator()
            )
        ) {
            group("Desktop") {
                story("kek") {
                    val list = rememberListControl("Bar text", listOf("Yo", "Sup", "Huh"))
                    TopAppBar(
                        title = { Text(list.value) }
                    )
                }
            }
        }
    }
}
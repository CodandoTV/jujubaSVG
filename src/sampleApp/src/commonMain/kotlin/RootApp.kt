import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gabrielbmoro.jujubasvg.core.JujubaSVG

@Composable
fun RootApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        JujubaSVG()
    }
}
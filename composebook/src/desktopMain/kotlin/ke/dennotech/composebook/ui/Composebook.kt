package ke.dennotech.composebook.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import ke.dennotech.composebook.model.Story
import ke.dennotech.composebook.model.Composebook
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
actual fun Composebook(composebook: Composebook){
    val state = rememberSplitPaneState()
    val selected = remember(composebook) { mutableStateOf<Story>(composebook.groups.first().stories.first())}
    HorizontalSplitPane(splitPaneState = state) {
        first(200.dp) {
            GroupList(composebook,selected)
        }
        second(50.dp) {
            StoryPreview(selected.value,composebook.decorators)
        }
    }
}
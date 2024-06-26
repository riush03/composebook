package ke.dennotech.composebook.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.dennotech.composebook.model.ControlType
import ke.dennotech.composebook.model.Decorator
import ke.dennotech.composebook.model.Story
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.VerticalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun StoryPreview(story: Story, decorators: List<Decorator>) {
    val state = rememberSplitPaneState(initialPositionPercentage = 1f)

    VerticalSplitPane(splitPaneState = state) {
        first(300.dp) {
            Surface(
                modifier = Modifier.clipToBounds().fillMaxSize()
            ) {
                Column {
                    Row(Modifier.horizontalScroll(rememberScrollState())) {
                        decorators.forEach { it.controlUi() }
                    }
                    Decorate(decorators) {
                        Box(
                            Modifier.fillMaxSize()
                        ) {
                            story.ui(story, Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
        second(150.dp) {
            ControlsAndActions(story)
        }
    }
}

@Composable
fun ActionList(actions: List<String>) {
    LazyColumn(Modifier.fillMaxHeight()) {
        items(actions) { item ->
            Text(item)
        }
    }
}

@Composable
private fun Controls(controls: List<ControlType>) {
    LazyColumn(Modifier.fillMaxHeight()) {
        items(controls) {
            it.ui()
        }
    }
}

@Composable
fun ControlsAndActions(story: Story) {
    val selectedTab = remember { mutableStateOf(0) }

    Column(Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = selectedTab.value) {
            Tab(selectedTab.value == 0, { selectedTab.value = 0 }) {
                Text("Actions")
            }
            Tab(selectedTab.value == 1, { selectedTab.value = 1 }) {
                Text("Controls")
            }
        }
        when (selectedTab.value) {
            0 -> {
                Controls(story.controls.value)
            }
            1 -> {
                ActionList(story.actions)
            }
        }
    }
}


@Composable
fun Decorate(decorators: List<Decorator>, content: @Composable () -> Unit) {
    if (decorators.isNotEmpty()) {
        val first = decorators.first()
        first.decorate(decorators.drop(1), content)
    } else
        content()
}
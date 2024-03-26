package ke.dennotech.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ke.dennotech.composebook.model.ControlType
import ke.dennotech.composebook.model.Story
import ke.dennotech.composebook.model.Storybook
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, android.compose.foundation.ExperimentalFoundationApi::class)
@Composable
actual fun Composebook(composebook: Composebook) {
    val scope = rememberCoroutineScope()
    var selected by remember(composebook) { mutableStateOf<Story>(composebook.groups.first().stories.first())}

    val scaffoldState = rememberBottomSheetScaffoldState()
}
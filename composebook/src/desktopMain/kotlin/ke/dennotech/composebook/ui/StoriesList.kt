package ke.dennotech.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.dennotech.composebook.model.Composebook
import ke.dennotech.composebook.model.Story

@Composable
fun GroupList(composebook: Composebook, selected: MutableState<Story>){
    LazyColumn{
        composebook.groups.forEach {
            group -> item(group.label) { GroupItem(group.label)}

            group.stories.forEach {
                story -> item(story.label + group.label){
                    StoryItem(story.label,story == selected.value){
                        selected.value = story
                    }
            }
            }
        }
    }
}

@Composable
fun GroupItem(label:String) {
    val style = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

    Text(
        text = label,
        style = style,
        modifier = Modifier
            .fillMaxWidth()
            .hoverBackground()
            .padding(
                horizontal = 4.dp,
                vertical = 2.dp,
            )
    )
}

@Composable
fun StoryItem(label:String,isSelected: Boolean,onClick: () -> Unit) {
    val style = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )

    Text(
        text = label,
        style = style,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(if (isSelected) Color.Blue.copy(alpha=0.2f) else Color.Transparent)
            .hoverBackground()
            .padding(
                vertical = 2.dp,
            )
            .padding(
                start = 12.dp,
                end = 4.dp
            )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.hoverBackground(): Modifier = composed {
    var isHovered by remember { mutableStateOf(false)}
    val color by derivedStateOf {
        if(isHovered)
            Color.Black.copy(alpha = 0.1f)
        else
            Color.Transparent
    }

    this.background(color)
        .pointerMoveFilter(
            onEnter = {
                isHovered = true
                false
            },
            onExit = {
                isHovered = false
                false
            }
        )
}
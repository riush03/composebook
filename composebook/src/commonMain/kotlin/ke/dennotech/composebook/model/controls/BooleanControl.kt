package ke.dennotech.composebook.model.controls

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ke.dennotech.composebook.model.Control
import ke.dennotech.composebook.model.Story

class BooleanControl(label: String,initialValue:Boolean): Control<Boolean>(label,initialValue) {
    @Composable
    override fun ui(){
        Column {
            Text(label)
            Checkbox(
                checked = value,
                onCheckedChange = { value = it}
            )
        }
    }

    companion object{
        @Composable
        fun Story.rememberBooleanControl(label: String,initialValue: Boolean) : BooleanControl =
            remember { addControl(label,BooleanControl(label,initialValue))}
    }
}
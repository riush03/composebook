package ke.dennotech.composebook.model

class Composebook {
    val groups: MutableList<StoryGroup> = mutableListOf()
    val decorators: List<Decorator> = listOf()
    fun group(label: String,block: StoryGroup.() -> Unit){
        val group = StoryGroup(label)
        group.block()
        groups.add(group)
    }
}

fun composebook(block: Composebook.() -> Unit): Composebook{
    val composebook = Composebook()
    composebook.block()
    return composebook
}
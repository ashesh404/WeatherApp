import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flowOf("A", "B", "C","D")

    flow2.combine(flow1) { number, letter ->
        "$number -> $letter" // Transform the paired values
    }.collect { result ->
        println(result)
    }
}
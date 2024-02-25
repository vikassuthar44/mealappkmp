package navigation

import com.arkivanov.decompose.ComponentContext
import screens.trackorder.TrackOrderEvent

class TrackOrderComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val newYear: () -> Unit
) : ComponentContext by componentContext {
    fun onEvent(event: TrackOrderEvent) {
        when(event) {
            TrackOrderEvent.MoveToBack -> {
                onBack()
            }

            TrackOrderEvent.NewYear -> {
                newYear()
            }
        }
    }
}
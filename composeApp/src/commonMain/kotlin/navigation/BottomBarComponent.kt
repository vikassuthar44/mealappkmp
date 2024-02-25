package navigation

import com.arkivanov.decompose.ComponentContext
import screens.order.OrderScreenEvent

class BottomBarComponent(
    componentContext: ComponentContext,
    private val onTrackOrder: () -> Unit
): ComponentContext by componentContext {

    fun onEventOrder(event: OrderScreenEvent) {
        when(event) {
            OrderScreenEvent.TrackOrder -> {
                onTrackOrder()
            }
        }
    }

    fun onEventHome() {

    }

}
package screens.order

sealed interface OrderScreenEvent {

    data object TrackOrder: OrderScreenEvent
}
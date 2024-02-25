package screens.trackorder

sealed interface TrackOrderEvent {

    data object MoveToBack: TrackOrderEvent
    data object NewYear: TrackOrderEvent
}
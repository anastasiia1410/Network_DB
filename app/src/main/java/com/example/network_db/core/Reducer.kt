package com.example.network_db.core


interface Reducer<Event, State> {
    fun reduce(event: Event, state: State): State
}

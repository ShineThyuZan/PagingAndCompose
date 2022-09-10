package klt.mdy.offlinesupportwithpaging.internet

sealed class ConnectionState{
    object  Available : ConnectionState()
    object UnAvailable : ConnectionState()
}

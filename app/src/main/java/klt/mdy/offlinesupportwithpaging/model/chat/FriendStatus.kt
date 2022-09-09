package klt.mdy.offlinesupportwithpaging.model.chat

enum class FriendStatus(val status: Int) {
    OTHER_CASE(0),
    NOT_FRIEND(1),
    REQUESTED(2),
    PENDING(3),
    FRIENDED(4),
    BLOCKED_TO(5),
    BEING_BLOCKED(6),
    BLOCKED_BOTH(7),
}
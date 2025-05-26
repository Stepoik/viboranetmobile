package stepan.gorokhov.viboranet.chat

import stepan.gorokhov.viboranet.chat.api.models.Message

fun Message.toVO(userId: String): MessageVO {
    return MessageVO(
        id = id,
        text = text,
        isFromMe = author.id == userId
    )
}
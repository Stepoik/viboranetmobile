package stepan.gorokhov.viboranet.coreui.models

import stepan.gorokhov.viboranet.core.uuid.Uuid

data class ErrorMessage(
    val message: String,
    val uuid: String = Uuid.generate()
)

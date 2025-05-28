package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.coreui.validation.ValidationError
import stepan.gorokhov.viboranet.coreui.validation.ValidationResult

fun CreateTestViewModelState.validate(): ValidationResult<CreateError> {
    // Проверка на пустые поля
    if (title.isBlank() || description.isBlank() || testImageId.isNullOrBlank()) {
        return ValidationResult.failed(CreateError.BlankFields)
    }

    // Проверка количества вариантов ответов
    val optionsCount = answerOptions.size
    if (optionsCount < 8 || !isPowerOfTwo(optionsCount)) {
        return ValidationResult.failed(CreateError.IncorrectOptionsCount)
    }

    return ValidationResult.success()
}

private fun isPowerOfTwo(n: Int): Boolean {
    return n > 0 && (n and (n - 1)) == 0
}

sealed class CreateError : ValidationError {
    data object BlankFields: CreateError()
    data object IncorrectOptionsCount: CreateError()
}
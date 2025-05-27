package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.tests.api.models.CreateTestOption
import stepan.gorokhov.viboranet.tests.api.models.CreateTournamentTest

fun CreateTestViewModelState.toCreateTest(): CreateTournamentTest {
    return CreateTournamentTest(
        title = title,
        description = description,
        imageId = testImageId ?: "",
        options = answerOptions.mapIndexed { index, answerOption -> answerOption.toTestOption(index) },
        tags = tags
    )
}

fun AnswerOption.toTestOption(index: Int): CreateTestOption {
    return CreateTestOption(
        index = index,
        title = title,
        description = description,
        imageId = imageId
    )
}
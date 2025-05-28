package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.tests.api.models.CreateTestOption
import stepan.gorokhov.viboranet.tests.api.models.CreateTournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption

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

fun TournamentTest.toCreateTestViewModelState(state: CreateTestViewModelState): CreateTestViewModelState {
    return state.copy(
        title = title,
        description = description,
        tags = tags,
        testImageId = imageId,
        answerOptions = options.map { it.toAnswerOption() }
    )
}

fun TournamentTestOption.toAnswerOption(): AnswerOption {
    return AnswerOption(
        title = title,
        description = description,
        imageUrl = image,
        imageId = imageId
    )
}
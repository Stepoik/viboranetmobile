package stepan.gorokhov.viboranet.tests.data.preview.mappers

import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.tests.api.models.CreateTestOption
import stepan.gorokhov.viboranet.tests.api.models.CreateTournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TestAuthor
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption
import stepan.gorokhov.viboranet.tests.data.preview.network.models.CreateTestRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.models.NewOptionDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestAuthorDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestOptionDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.UpdateTestRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.models.UpdatedOptionDto

fun TestDto.toTestPreview(): TestPreview {
    return TestPreview(
        id = id,
        title = title,
        description = description,
        image = "${NetworkConstants.BASE_URL}/images/$imageId",
        author = author.toAuthor(),
        localRating = localRating,
        globalRating = globalRating
    )
}

fun TestAuthorDto.toAuthor(): TestAuthor {
    return TestAuthor(
        id = userId,
        name = username,
        image = imageUrl
    )
}

fun TestDto.toTournament(): TournamentTest {
    return TournamentTest(
        id = id,
        title = title,
        author = author.toAuthor(),
        description = description,
        tags = tags,
        imageId = imageId,
        options = options.map { it.toTournamentTestOption() }
    )
}

fun TestOptionDto.toTournamentTestOption(): TournamentTestOption {
    return TournamentTestOption(
        index = index,
        title = title,
        description = description,
        imageId = imageId,
        image = "${NetworkConstants.BASE_URL}/images/$imageId"
    )
}

fun CreateTournamentTest.toCreateRequest(): CreateTestRequest {
    return CreateTestRequest(
        title = title,
        description = description,
        imageId = imageId,
        tags = tags,
        options = options.map { it.toDto() }
    )
}

fun CreateTournamentTest.toUpdateRequest(): UpdateTestRequest {
    return UpdateTestRequest(
        title = title,
        description = description,
        imageId = imageId,
        tags = tags,
        options = options.map { it.toUpdateDto() }
    )
}

fun CreateTestOption.toDto(): NewOptionDto {
    return NewOptionDto(
        title = title,
        description = description,
        imageId = imageId
    )
}

fun CreateTestOption.toUpdateDto(): UpdatedOptionDto {
    return UpdatedOptionDto(
        index = index,
        title = title,
        description = description,
        imageId = imageId
    )
}
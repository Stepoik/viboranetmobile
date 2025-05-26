package stepan.gorokhov.viboranet.tests.data.preview

import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.tests.api.models.TestAuthor
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestAuthorDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.TestOptionDto

fun TestDto.toTestPreview(): TestPreview {
    return TestPreview(
        id = id,
        title = title,
        description = description,
        image = "${NetworkConstants.BASE_URL}/images/$imageId",
        author = author.toAuthor(),
        rating = rating
    )
}

fun TestAuthorDto.toAuthor(): TestAuthor {
    return TestAuthor(
        id = userId,
        name = username,
        image = "${NetworkConstants.BASE_URL}/images/$imageId"
    )
}

fun TestDto.toTournament(): TournamentTest {
    return TournamentTest(
        id = id,
        title = title,
        author = author.toAuthor(),
        options = options.map { it.toTournamentTestOption() }
    )
}

fun TestOptionDto.toTournamentTestOption(): TournamentTestOption {
    return TournamentTestOption(
        id = index.toString(),
        title = title,
        description = description,
        image = "${NetworkConstants.BASE_URL}/images/$imageId"
    )
}
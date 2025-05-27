package stepan.gorokhov.viboranet.tests.presentation.main

import stepan.gorokhov.viboranet.tests.api.models.TestPreview

fun TestPreview.toVO() = TestPreviewVO(
    id = id,
    title = title,
    description = description,
    image = image,
    rating = globalRating,
    localRating = localRating,
    author = TestAuthorVO(
        id = author.id,
        name = author.name,
        image = author.image
    )
)
package io.github.coderbuck.boring.bean

class ZhihuBean : ArrayList<ZhihuBeanItem>()

data class ZhihuBeanItem(
    val attachedInfo: String,
    val cardId: String,
    val feedSpecific: FeedSpecific,
    val id: String,
    val styleType: String,
    val target: Target,
    val type: String
)

data class FeedSpecific(
    val answerCount: Int,
    val debut: Boolean,
    val score: Double,
    val trend: Int
)

data class Target(
    val excerptArea: ExcerptArea,
    val imageArea: ImageArea,
    val labelArea: LabelArea,
    val link: Link,
    val metricsArea: MetricsArea,
    val titleArea: TitleArea
)

data class ExcerptArea(
    val text: String
)

data class ImageArea(
    val url: String
)

data class LabelArea(
    val nightColor: String,
    val normalColor: String,
    val trend: Int,
    val type: String
)

data class Link(
    val url: String
)

data class MetricsArea(
    val text: String
)

data class TitleArea(
    val text: String
)
package com.example.newsapp

import com.example.newsapp.data.remote.models.Article
import com.example.newsapp.data.remote.models.Source

object TestData {

    val source1 = Source(id = "bbc-news", name = "BBC News")
    val source2 = Source(id = "cnn", name = "CNN")
    val source3 = Source(id = null, name = "The Verge")

    val article1 = Article(
        source = source1,
        author = "John Doe",
        title = "Breaking: Major Earthquake Hits Pacific Region",
        description = "A 7.2 magnitude earthquake struck the Pacific coast causing widespread damage.",
        url = "https://bbc.com/news/earthquake-pacific",
        urlToImage = "https://picsum.photos/seed/article1/400/200",
        publishedAt = "2024-01-15T10:30:00Z",
        content = "Full content of the earthquake article goes here."
    )

    val article2 = Article(
        source = source2,
        author = "Jane Smith",
        title = "Tech Giants Report Record Profits in Q4",
        description = "Major technology companies have reported unprecedented earnings for the fourth quarter.",
        url = "https://cnn.com/tech/profits-q4",
        urlToImage = "https://picsum.photos/seed/article2/400/200",
        publishedAt = "2024-01-15T08:00:00Z",
        content = "Full content of the tech profits article goes here."
    )

    val article3 = Article(
        source = source3,
        author = null,
        title = "New AI Model Surpasses Human Performance on Benchmarks",
        description = "Researchers announce a breakthrough AI model that outperforms humans on multiple tasks.",
        url = "https://theverge.com/ai/new-model-benchmarks",
        urlToImage = "https://picsum.photos/seed/article3/400/200",
        publishedAt = "2024-01-14T18:45:00Z",
        content = "Full content of the AI article goes here."
    )

    val article4 = Article(
        source = source1,
        author = "Alice Brown",
        title = "Climate Summit Reaches Historic Agreement",
        description = "World leaders sign a landmark deal to reduce carbon emissions by 50% by 2035.",
        url = "https://bbc.com/news/climate-summit",
        urlToImage = null,
        publishedAt = "2024-01-14T12:00:00Z",
        content = "Full content of the climate article goes here."
    )

    val article5 = Article(
        source = source2,
        author = "Bob Wilson",
        title = "Stock Markets Reach All-Time High",
        description = "Global stock markets hit record levels amid strong economic data.",
        url = "https://cnn.com/markets/all-time-high",
        urlToImage = "https://picsum.photos/seed/article5/400/200",
        publishedAt = "2024-01-13T09:15:00Z",
        content = null
    )

    val articleList = listOf(article1, article2, article3, article4, article5)

    // Edge case: article with all nullable fields set to null
    val articleWithNulls = Article(
        source = Source(id = null, name = "Unknown"),
        author = null,
        title = null,
        description = null,
        url = "https://example.com/no-data",
        urlToImage = null,
        publishedAt = "2024-01-01T00:00:00Z",
        content = null
    )

    // Edge case: article with very long title/description for overflow testing
    val articleWithLongTextList = listOf(Article(
        source = source1,
        author = "Long Author Name That Goes On And On",
        title = "This Is An Extremely Long Title That Should Be Truncated In The UI Because It Exceeds The Maximum Lines Allowed",
        description = "This is an extremely long description that should also be truncated in the news item composable because it exceeds the three line maximum that has been set on the text component in the UI layer of the application.",
        url = "https://bbc.com/news/long-article",
        urlToImage = "https://picsum.photos/seed/long/400/200",
        publishedAt = "2024-01-12T07:00:00Z",
        content = "Full content here."
    ))
}
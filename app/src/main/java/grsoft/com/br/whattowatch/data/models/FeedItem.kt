package grsoft.com.br.whattowatch.data.models

import grsoft.com.br.whattowatch.data.entities.TVShow

data class FeedItem(
        val id: Long,
        val genre: String,
        val tvShows: List<TVShow>
)
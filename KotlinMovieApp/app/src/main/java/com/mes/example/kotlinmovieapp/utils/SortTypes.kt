package com.mes.example.kotlinmovieapp.utils

enum class SortTypes(val value: String) {
    PopularAsc("popularity.asc"),
    PopularDec("popularity.desc"),
    VoteAverageAsc("vote_average.asc"),
    VoteAverageDec("vote_average.desc"),
    ReleaseDateAsc("release_date.asc"),
    ReleaseDateDec("release_date.desc"),
    PrimaryReleaseDateAsc("primary_release_date.asc"),
    PrimaryReleaseDateDec("primary_release_date.desc"),
    RevenueAsc("revenue.asc"),
    RevenueDec("revenue.desc"),
    OriginalTitleAsc("original_title.asc"),
    OriginalTitleDec("original_title.desc"),
    VoteCountAsc("vote_count.asc"),
    VoteCountDec("vote_count.desc"),
    None("None")
}
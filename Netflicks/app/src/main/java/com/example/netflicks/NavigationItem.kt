package com.example.netflicks

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Notification : NavigationItem("notification", R.drawable.ic_bell, "Notification")
    object Search : NavigationItem("search", R.drawable.ic_search, "Search")
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Favorites : NavigationItem("favorites", R.drawable.ic_heart, "Favorites")
    object More : NavigationItem("more", R.drawable.ic_more, "More")
}

package com.example.netflicks.model

data class Movie(
    val id: Int,
    val name: String,
    val rating: Float,
    val imageUrl: String,
    val videoUrl: String,
    val year: Int,
    val duration: String,
    val genre: List<String>,
    val description: String
)

val movies = arrayListOf(
    //region Videos
    Movie(
        0, "The Lord of the Rings: The Fellowship of the Ring",
        8.8F,
        "https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_FMjpg_UX1000_.jpg",
        "https://www.youtube.com/watch?v=V75dMMIW2B4",
        2001,
        "2h 58min",
        arrayListOf("Adventure, Fantasy"),
        "An ancient Ring thought lost for centuries has been found, and through a strange twist of fate has been given to a small Hobbit named Frodo. When Gandalf discovers the Ring is in fact the One Ring of the Dark Lord Sauron, Frodo must make an epic quest to the Cracks of Doom in order to destroy it. However, he does not go alone. He is joined by Gandalf, Legolas the elf, Gimli the Dwarf, Aragorn, Boromir, and his three Hobbit friends Merry, Pippin, and Samwise. Through mountains, snow, darkness, forests, rivers and plains, facing evil and danger at every corner the Fellowship of the Ring must go. Their quest to destroy the One Ring is the only hope for the end of the Dark Lords reign"
    ),
    Movie(
        1, "Insidious",
        6.8F,
        "https://m.media-amazon.com/images/M/MV5BMTYyOTAxMDA0OF5BMl5BanBnXkFtZTcwNzgwNTc1NA@@._V1_FMjpg_UX1000_.jpg",
        "https://www.youtube.com/watch?v=zuZnRUcoWos",
        2010,
        "1h 43min",
        arrayListOf("Horror, Mystery"),
        "A gripping story of a family in search of help for their son, Dalton, who fell into a coma after a mysterious incident in the attic. Little do they know that there is much more to this endless sleep than meets the eye as they explore the paranormal, and rediscover the past; the key to getting their son back once and for all."
    ),
    //endregion

    //region Now in cinemas
    Movie(
        2, "Doctor Strange in the Multiverse of Madness",
        7.3F,
        "https://static.cinemagia.ro/img/db/movie/27/73/017/doctor-strange-in-the-multiverse-of-madness-676611l.jpg",
        "https://www.youtube.com/watch?v=aWzlQ2N6qqg",
        2022,
        "2h 6min",
        arrayListOf("Fantasy, Sci-Fi"),
        "Doctor Strange teams up with a mysterious teenage girl from his dreams who can travel across multiverses, to battle multiple threats, including other-universe versions of himself, which threaten to wipe out millions across the multiverse. They seek help from Wanda the Scarlet Witch, Wong and others."
    ),
    Movie(
        3, "Jujutsu Kaisen 0",
        7.9F,
        "https://media.services.cinergy.ch/media/box1600/f42fb94b711da5a0e9c3fc4c8a50e5f7e4ee995c.jpg",
        "https://www.youtube.com/watch?v=-iun6KPT4SM",
        2021,
        "1h 45min",
        arrayListOf("Animation, Fantasy"),
        "Yuta Okkotsu, a high schooler who gains control of an extremely powerful Cursed Spirit and gets enrolled in the Tokyo Prefectural Jujutsu High School by Jujutsu Sorcerers to help him control his power and keep an eye on him."
    ),
    Movie(
        4, "Jurassic World Dominion",
        6.3F,
        "https://static.cinemagia.ro/img/db/movie/25/67/321/jurassic-world-dominion-621426l.jpg",
        "https://www.youtube.com/watch?v=fb5ELWi-ekk",
        2022,
        "2h 26min",
        arrayListOf("Action, Sci-Fi"),
        "Four years after the destruction of Isla Nublar, dinosaurs now live--and hunt--alongside humans all over the world. This fragile balance will reshape the future and determine, once and for all, whether human beings are to remain the apex predators on a planet they now share with history's most fearsome creatures in a new Era."
    ),
    Movie(
        6, "IT Chapter 2",
        6.5F,
        "https://m.media-amazon.com/images/M/MV5BYTJlNjlkZTktNjEwOS00NzI5LTlkNDAtZmEwZDFmYmM2MjU2XkEyXkFqcGdeQXVyNjg2NjQwMDQ@._V1_FMjpg_UX1000_.jpg",
        "https://www.youtube.com/watch?v=bKL1ImsN-DU",
        2019,
        "2h 49min",
        arrayListOf("Horror, Drama"),
        "Twenty-seven years after their first encounter with the terrifying Pennywise, the Losers Club have grown up and moved away, until a devastating phone call brings them back."
    ),
    //endregion

    //region Coming soon
    Movie(
        5, "Thor: Love and Thunder",
        0F,
        "https://lumiere-a.akamaihd.net/v1/images/fb83262af061c44d518c601e3956c3b4_2764x4096_e4f0eee2.jpeg",
        "https://www.youtube.com/watch?v=Go8nTmfrQd8",
        2022,
        "Unkown",
        arrayListOf("Action, Comedy"),
        "Thor enlists the help of Valkyrie, Korg and ex-girlfriend Jane Foster to fight Gorr the God Butcher, who intends to make the gods extinct."
    ),
    Movie(
        7, "Avatar: They Way of Water",
        0F,
        "https://m.media-amazon.com/images/M/MV5BMWFmYmRiYzMtMTQ4YS00NjA5LTliYTgtMmM3OTc4OGY3MTFkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg",
        "https://www.youtube.com/watch?v=NZrX_ES93JA",
        2022,
        "Unkown",
        arrayListOf("Adventure, Sci-Fi"),
        "Jake Sully lives with his newfound family formed on the planet of Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na'vi race to protect their planet."
    ),
    Movie(
        8, "John Wick: Chapter 4",
        0F,
        "https://m.media-amazon.com/images/M/MV5BZDJiNTUyZWQtNGFkNy00ODhkLWJkNWMtOTI1ODhhYmNiYzU0XkEyXkFqcGdeQXVyMTMxNTMyNDQw._V1_.jpg",
        "https://www.youtube.com/watch?v=BMWtLXDkv6Y",
        2023,
        "Unkown",
        arrayListOf("Action, Crime"),
        "John Wick is declared excommunicado and a hefty bounty is set on him after he murders an international crime lord. He sets out to seek help to save himself from ruthless hitmen and bounty hunters."
    )
    //endregion
)
package com.example.misa.iwatch.entity
import com.google.android.gms.maps.model.LatLng


import com.example.misa.iwatch.R

/**
 * Created by NAWAL on 02/04/2018.
 */
var PARAMS_NAME = "PARAMS"
class data {


    companion object {
        var Filmsfav = ArrayList<Movie>()
        var Films = ArrayList<Movie>()
        var Series = ArrayList<Series>()
        var SeriesFav = ArrayList<Series>()
        var Acteurs = ArrayList<Personnes>()
        var Realisateurs = ArrayList<Personnes>()
        var Cinemas = ArrayList<Cinema>()
        var CinemasFav = ArrayList<Cinema>()



        fun getMoviesRecent(): ArrayList<Movie> {
            if (Films.size<1) {


                // Evaruation
                var evar = ArrayList<Float>();evar.add(3.0F); evar.add(2.0F); evar.add(5.0F); evar.add(5.0F);
                var evar1 = ArrayList<Float>();evar1.add(5.0F); evar1.add(4.0F); evar1.add(4.0F); evar1.add(5.0F);
                var evar2 = ArrayList<Float>();evar2.add(3.0F); evar2.add(1.0F); evar2.add(5.0F); evar2.add(4.0F);
                var evar3 = ArrayList<Float>();evar3.add(4.0F); evar3.add(5.0F); evar3.add(2.0F); evar3.add(3.0F);
                // Comments
                var comments = ArrayList<Comments>(); comments.add(Comments("Spawn Wesker ", "le deuxième meilleur film du mcu pour moi", R.drawable.a))
                var comments1 = ArrayList<Comments>(); comments1.add(Comments("Basil Court ", "Major Disappointment", R.drawable.d))
                var comments2 = ArrayList<Comments>(); comments2.add(Comments("Basil Court ", "Le genre de film que je regarde dans l'avion...tjrs aussi efficace avec un J.Depp tjrs bien funky dans le rôle de Jack Sparrow.", R.drawable.d))

                // Storylines

                var storyline = "Musicalement accompagné de la \"Awesome Mixtape n°2\" (la musique qu'écoute Star-Lord dans le film), Les Gardiens de la galaxie 2 poursuit les aventures de l'équipe alors qu'elle traverse les confins du cosmos. Les gardiens doivent combattre pour rester unis alors qu'ils découvrent les mystères de la filiation de Peter Quill. Les vieux ennemis vont devenir de nouveaux alliés et des personnages bien connus des fans de comics vont venir aider nos héros et continuer à étendre l'univers Marvel."
                var storyline1 = "C'était avant qu'elle ne devienne Wonder Woman, à l'époque où elle était encore Diana, princesse des Amazones et combattante invincible. Un jour, un pilote américain s'écrase sur l'île paradisiaque où elle vit, à l'abri des fracas du monde. Lorsqu'il lui raconte qu'une guerre terrible fait rage à l'autre bout de la planète, Diana quitte son havre de paix, convaincue qu'elle doit enrayer la menace. En s'alliant aux hommes dans un combat destiné à mettre fin à la guerre, Diana découvrira toute l'étendue de ses pouvoirs… et son véritable destin."
                var storyline2 = "Les temps sont durs pour le Capitaine Jack, et le destin semble même vouloir s’acharner lorsqu’un redoutable équipage fantôme mené par son vieil ennemi, le terrifiant Capitaine Salazar, s’échappe du Triangle du Diable pour anéantir tous les flibustiers écumant les flots… Sparrow compris ! Le seul espoir de survie du Capitaine Jack est de retrouver le légendaire Trident de Poséidon, qui donne à celui qui le détient tout pouvoir sur les mers et les océans."


                // filmographie
                var filmographie = ArrayList<Film>(); filmographie.add(Film("Guardien de la galaxy", R.drawable.guardians_galaxy, 0)); filmographie.add(Film("Wonder Women", R.drawable.wonder_women, 1)); filmographie.add(Film("Pirate des caraaibe", R.drawable.pirate, 2))

                // Actors
                var Actors = ArrayList<Personnes>()

                // Evaruation
                var evaract = ArrayList<Float>();evaract.add(4.0F); evaract.add(3.0F); evaract.add(4.0F); evaract.add(5.0F);
                var evaract1 = ArrayList<Float>();evaract1.add(5.0F); evaract1.add(4.0F); evaract1.add(3.0F); evaract1.add(3.0F);
                var evaract2 = ArrayList<Float>();evaract2.add(5.0F); evaract2.add(5.0F); evaract2.add(5.0F); evaract2.add(2.0F);
                var evaract3 = ArrayList<Float>();evaract3.add(2.0F); evaract3.add(1.0F); evaract3.add(2.0F); evaract3.add(1.0F);

                var storylineact = "est connu grâce aux rôles qu'il incarne à la télévision comme celui de Bright Abbott dans Everwood et d'Andy Dwyer dans Parks and Recreation. Il incarne aussi des rôles secondaires au cinéma comme dans Wanted : Choisis ton destin, Le Stratège, Ten Years, Cinq ans de réflexion, Zero Dark Thirty et Her. Il joue ensuite l'un des rôles principaux des films comme les comédies déjantées Hot Babes et Delivery Man."
                var storylineact1 = "une actrice, réalisatrice et productrice américano-dominicaine Elle accède à la notoriété grâce aux premiers rôles féminins de trois franchises majeures du cinéma de science-fiction : Avatar, Star Trek et Les Gardiens de la Galaxie."
                var storylineact2 = "plus connu sous le nom de Batista, est un acteur, ancien pratiquant de combat libre et catcheur américain Principalement connu pour son travail à la World Wrestling Entertainment (WWE), il y commence sa carrière en 2002 sous le nom de Deacon Batista. Après avoir été pendant un temps le manager de Reverend D-Von avant de devenir membre de l'Evolution avec Triple H, Ric Flair et Randy Orton."

                var commentsact = ArrayList<Comments>(); commentsact.add(Comments("Jules D. ", "J'ai hâte de le revoir dans le deuxième volet des Gardiens de la Galaxie !", R.drawable.e))
                var commentsact1 = ArrayList<Comments>(); commentsact1.add(Comments("Walter Mouse ", "La plus belle femme de ces dernières années. Ah et elle joue bien aussi ;)", R.drawable.i))
                var commentsact2 = ArrayList<Comments>(); commentsact2.add(Comments("MGM-ranger ", " Très bien dans les guardiens !)", R.drawable.c)); commentsact2.add(Comments("Licorne28  ", "Drax c'est le meilleur :) vive les gardiens", R.drawable.f))


                Actors.add(Personnes("CHRIS PRATT", " 21 juin 1979 ", "Virginia, Minnesota, USA", R.drawable.crispatt, storylineact, commentsact, filmographie, evaract,R.drawable.crispatt2))
                Actors.add(Personnes("ZOE SALDANA", "19 juin 1978  ", "New Jersey - Etats-Unis", R.drawable.zoe_saldana, storylineact1, commentsact1, filmographie, evaract1,R.drawable.zoe_saldana2))
                Actors.add(Personnes("DAVE BAUTISTA", "18 janvier 1969  ", "Washington D.C. - Etats-Unis", R.drawable.dave, storylineact2, commentsact2, filmographie, evaract2,R.drawable.dave2))
                var storylinereal = "James Gunn est un scénariste, acteur, producteur, réalisateur et directeur de la photographie américain, "
                var commentsreal = ArrayList<Comments>(); commentsreal.add(Comments("Cosmicm  ", " Quand j'ai vu sa filmographie, je me suis dit: attend c'est à lui qu'on a confié les gardiens de la galaxie ??? Comme quoi...on peut vraiment être surpris.", R.drawable.h))
                var evarreal = ArrayList<Float>();evarreal.add(4.0F); evarreal.add(3.0F); evarreal.add(5.0F); evarreal.add(4.0F);

                var realisateur = Personnes("JAMES GUNN", "5 août 1970 ", "Providence, Rhode Island, États-Unis", R.drawable.james, storylinereal, commentsreal, filmographie, evarreal,R.drawable.james2)


                //actors2
                // Actors
                var Actors2 = ArrayList<Personnes>()

                // Evaruation
                var evaract20 = ArrayList<Float>();evaract20.add(4.0F); evaract20.add(2.0F); evaract20.add(4.0F); evaract20.add(2.0F);
                var evaract21 = ArrayList<Float>();evaract21.add(4.0F); evaract21.add(2.0F); evaract21.add(3.0F); evaract21.add(5.0F);
                var evaract22 = ArrayList<Float>();evaract22.add(3.0F); evaract22.add(3.0F); evaract22.add(5.0F); evaract22.add(4.0F);

                var storylineact20 = "est connu grâce aux rôles qu'il incarne à la télévision comme celui de Bright Abbott dans Everwood et d'Andy Dwyer dans Parks and Recreation. Il incarne aussi des rôles secondaires au cinéma comme dans Wanted : Choisis ton destin, Le Stratège, Ten Years, Cinq ans de réflexion, Zero Dark Thirty et Her. Il joue ensuite l'un des rôles principaux des films comme les comédies déjantées Hot Babes et Delivery Man."
                var storylineact21 = "un acteur américain. Il est révélé pour son interprétation du rôle du capitaine Kirk dans la franchise reboot Star Trek, produite par J. J. Abrams depuis 2009, qui rencontre un important succès commercial et critique."
                var storylineact22 = "une actrice danoise. Révélée au monde entier en 2000 par son interprétation de Lucilla dans le péplum de Ridley Scott Gladiator, elle incarnera aussi un rôle de femme de pouvoir trouble et complexe en interprétant Meredith Kane dans la série politique Boss, entre 2011 et 2012."

                var commentsact20 = ArrayList<Comments>(); commentsact20.add(Comments("Jules D. ", "Très bon choix pour Wonder Woman", R.drawable.e))
                var commentsact21 = ArrayList<Comments>(); commentsact21.add(Comments("Walter Mouse ", "Il est convaincant dans Comancheria, il remonte dans mon estime ;)", R.drawable.d))
                var commentsact22 = ArrayList<Comments>(); commentsact22.add(Comments("MGM-ranger ", " une magnifique femme, et une très bonne actrice !", R.drawable.i))


                Actors2.add(Personnes("GAL GADOT", " 30 avril 1985 ", "(Rosh Ha'ayin", R.drawable.gal, storylineact20, commentsact20, filmographie, evaract20,R.drawable.gal2))
                Actors2.add(Personnes("CHRIS PINE", " 26 août 1980   ", "Los Angeles, Californie - Etats-Unis", R.drawable.chris_pin, storylineact21, commentsact21, filmographie, evaract21,R.drawable.chris_pin2))
                Actors2.add(Personnes("CONNIE NIELSEN", " 3 juillet 1965   ", "Elling, Jutland - Danemark", R.drawable.connies, storylineact22, commentsact22, filmographie, evaract22,R.drawable.connies2))

                var storylinereal2 = "Patty Jenkins est une réalisatrice et scénariste américaine, née le 24 juillet 1971 à Victorville en Californie.Elle s'est fait connaitre avec le film Monster, pour lequel Charlize Theron a obtenu l'Oscar de la meilleure actrice."
                var commentsreal2 = ArrayList<Comments>(); commentsreal2.add(Comments("Cosmicm  ", " Personne ici pour parler de son incroyable travail sur WW ", R.drawable.b))
                var evarrea12 = ArrayList<Float>();evarrea12.add(2.0F); evarrea12.add(3.0F); evarrea12.add(5.0F); evarrea12.add(5.0F);

                var realisateur2 = Personnes("PATTY JENKINS", "5 août 1970 ", "Providence, Rhode Island, États-Unis", R.drawable.patty, storylinereal2, commentsreal2, filmographie, evarreal,R.drawable.patty2)


                //actors2
                // Actors
                var Actors3 = ArrayList<Personnes>()


                var storylineact30 = "John Christopher Depp II, dit Johnny Depp, est un acteur, réalisateur, guitariste, scénariste et producteur de cinéma américain, né le 9 juin 1963 à Owensboro (Kentucky)."
                var storylineact31 = "Javier Bardem est un acteur espagnol né le 1er mars 1969 à Las Palmas de Gran Canaria (Îles Canaries) Parangon du latin viril grâce à ses rôles sulfureux chez Bigas Luna et Pedro Almodóvar, il est le premier acteur espagnol à avoir été nommé aux Oscars, en 2001, et à être récompensé en 2008 comme « Meilleur second rôle masculin » pour son interprétation de tueur froid et implacable dans No Country for Old Men des frères Coen. Ce rôle lui a aussi varu un Golden Globe dans la même catégorie."
                var storylineact32 = "Brenton Thwaites est un acteur australien né le 10 août 1989 à Cairns Il débute à la télévision australienne, dans la série télévisée SLiDE et dans le soap opera Summer Bay1. Il est finalement révélé, au cinéma, notamment dans le film d'horreur Oculus (2013), dans le film de science-fiction The Giver2 (2014) ainsi que le film fantastique Maléfique (2014)3."

                var commentsact30 = ArrayList<Comments>(); commentsact30.add(Comments("Jules D. ", "L'un des plus grand acteur de tous les temps", R.drawable.e))
                var commentsact31 = ArrayList<Comments>(); commentsact31.add(Comments("Walter Mouse ", "gage de qualité ", R.drawable.d))
                var commentsact32 = ArrayList<Comments>(); commentsact32.add(Comments("MGM-ranger ", " Il resemble un peu à l'acteur de spider Man je trouve lol", R.drawable.i))


                Actors3.add(Personnes("JOHNNY DEPP", " 9 juin 1963  ", "Owensboro, Kentucky - Etats-Unis", R.drawable.jonny, storylineact30, commentsact30, filmographie, evaract,R.drawable.jonny2))
                Actors3.add(Personnes("JAVIER BARDEM", "  1 mars 1969  ", "Las Palma, Gran Canaria - Espagne", R.drawable.javier, storylineact31, commentsact31, filmographie, evaract21,R.drawable.javier2))
                Actors3.add(Personnes("BRENTON THWAITES", " 10 août 1989  ", "Australie", R.drawable.brenton, storylineact32, commentsact32, filmographie, evaract22,R.drawable.brenton2))

                var storylinerea32 = "Espen Sandberg est un réalisateur norvégien né le 17 juin 1971 à Sandefjord en Norvège. Il forme un duo avec Joachim Rønning appelé les « Roenberg »1"
                var commentsrea32 = ArrayList<Comments>(); commentsrea32.add(Comments("Cosmicm  ", " Personne ici pour parler de son incroyable travail sur WW ", R.drawable.b)); commentsrea32.add(Comments("The Last Action Zero   ", "Idem, je veux un autre film, Monster était tellement bien ! :(", R.drawable.b)); commentsrea32.add(Comments("gaut zila   ", "Oué, on veut un autre chef d'oeuvre, Monster est un super film !!! :)", R.drawable.j));
                var evarrea13 = ArrayList<Float>();evarrea13.add(5.0F); evarrea13.add(4.0F); evarrea13.add(5.0F); evarrea13.add(3.0F);

                var realisateur3 = Personnes("ESPEN SANDBERG", "5 août 1970 ", "Providence, Rhode Island, États-Unis", R.drawable.espen, storylinerea32, commentsrea32, filmographie, evarrea13,R.drawable.espen2)


                var room = ArrayList<Room>()
                var ar= LatLng(40.7340685, -73.9933289)
                var ar1 = LatLng(48.8682668,2.366204)
                var ar2 = LatLng(40.7359436, -73.9960851)


                room.add(Room("Cinema Village ", "22 E 12th St, New York, NY 10003, États-Unis ", R.drawable.cinema_room))
                room.add(Room("Apollo Théâtre ", "18 Rue du Faubourg du Temple, 75011 Paris, France", R.drawable.apollo))
                room.add(Room("Quad Cinema ", "34 W 13th St, New York, NY 10011, États-Unis ", R.drawable.quad))


                var associateFilm = ArrayList<Film>()

                associateFilm.add(Film("Wonder Women", R.drawable.wonder_women, 1))
                associateFilm.add(Film("Gurdien Galaxy ", R.drawable.guardians_galaxy, 0))
                associateFilm.add(Film("Pirate des caraaibe", R.drawable.pirate, 2))


          //      Films.add(Movie("Guardian of the galaxy", "Action Aventure ", "James  Gunn", R.drawable.guardians_galaxy, evar, comments, storyline, Actors, realisateur, room, associateFilm, R.raw.video_harry.toString(),false))
                        //    Films.add(Movie("Wonder Women", "Action, Adventure, Fantasy  ", "Patty Jenkins", R.drawable.wonder_women, evar1, comments1, storyline1, Actors2, realisateur2, room, associateFilm, R.raw.video_harry.toString(),false))
       //         Films.add(Movie("PIRATES DES CARAÏBES", "Aventure, Fantastique, Action ", "Espen Sandberg", R.drawable.pirate, evar, comments2, storyline2, Actors3, realisateur3, room, associateFilm, R.raw.video_harry.toString(),false))

            }
            return this.Films
        }

        fun getSeriesRecent(): ArrayList<Series> {


            if (Series.size<1) {
                var evar = ArrayList<Float>()
                evar.add(3.0F); evar.add(4.0F); evar.add(5.0F); evar.add(3.0F);
                var comments = ArrayList<Comments>(); comments.add(Comments("Spawn Wesker ", "le deuxième meilleur film du mcu pour moi", R.drawable.a))

                var storyline = "Huit voleurs font une prise d'otages dans la Maison royale de la Monnaie d'Espagne, tandis qu'un génie du crime manipule la police pour mettre son plan à exécution."
                var storyline1 = "Il y a très longtemps, à une époque oubliée, une force a détruit l'équilibre des saisons. Dans un pays où l'été peut durer plusieurs années et l'hiver toute une vie, des forces sinistres et surnaturelles se pressent aux portes du Royaume des Sept Couronnes. La confrérie de la Garde de Nuit, protégeant le Royaume de toute créature pouvant provenir d'au-delà du Mur protecteur, n'a plus les ressources nécessaires pour assurer la sécurité de tous. Après un été de dix années, un hiver rigoureux s'abat sur le Royaume avec la promesse d'un avenir des plus sombres. Pendant ce temps, complots et rivarités se jouent sur le continent pour s'emparer du Trône de Fer, le symbole du pouvoir absolu."
                var storyline2 = "Scandinavie, à la fin du 8ème siècle. Ragnar Lodbrok, un jeune guerrier viking, est avide d'aventures et de nouvelles conquêtes. Lassé des pillages sur les terres de l'Est, il se met en tête d'explorer l'Ouest par la mer. Malgré la réprobation de son chef, Haraldson, il se fie aux signes et à la volonté des dieux, en construisant une nouvelle génération de vaisseaux, plus légers et plus rapides..."

                var seriesL = ArrayList<Int>();seriesL.add(1); seriesL.add(2);
                var seriesL1 = ArrayList<Int>();seriesL1.add(0); seriesL1.add(2);
                var seriesL2 = ArrayList<Int>();seriesL2.add(0); seriesL2.add(1);

                var diffusion_casa = ArrayList<Diffusion>(); diffusion_casa.add(Diffusion("NetFlix", R.drawable.netflix))
                var diffusion_game = ArrayList<Diffusion>(); diffusion_game.add(Diffusion("HBO", R.drawable.hbo))
                var diffusion_vikings = ArrayList<Diffusion>(); diffusion_vikings.add(Diffusion("Historia", R.drawable.historia))

                var episodes_casa_s1 = ArrayList<Episode>();episodes_casa_s1.add(Episode("le premier episode", "30 mn ", R.drawable.casadelpapel_saison1, storyline, diffusion_casa, comments, evar,R.raw.video_harry.toString())); episodes_casa_s1.add(Episode("le deuxieme episode", "50 mn ", R.drawable.casadelpapel_saison1, storyline, diffusion_casa, comments, evar,R.raw.video_harry.toString()));episodes_casa_s1.add(Episode("le troisieme episode", "40 mn ", R.drawable.casadelpapel_saison1, storyline, diffusion_casa, comments, evar,R.raw.video_harry.toString()))
                var episodes_casa_s2 = ArrayList<Episode>();episodes_casa_s2.add(Episode("le premier episode", "30 mn ", R.drawable.casadelpapel_saison2, storyline, diffusion_casa, comments, evar,R.raw.video_harry.toString())); episodes_casa_s2.add(Episode("le deuxieme episode", "50 mn ", R.drawable.casadelpapel_saison2, storyline, diffusion_casa, comments, evar,R.raw.video_harry.toString()));episodes_casa_s2.add(Episode("le troisieme episode", "40 mn ", R.drawable.casadelpapel_saison2, storyline, diffusion_casa, comments, evar,R.raw.video_harry.toString()))

                var episodes_game_s1 = ArrayList<Episode>();episodes_game_s1.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison1, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s1.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison1, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s1.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison1, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))
                var episodes_game_s2 = ArrayList<Episode>();episodes_game_s2.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison2, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s2.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison2, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s2.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison2, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))
                var episodes_game_s3 = ArrayList<Episode>();episodes_game_s3.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison3, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s3.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison3, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s3.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison3, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))
                var episodes_game_s4 = ArrayList<Episode>();episodes_game_s4.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison4, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s4.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison4, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s4.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison4, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))
                var episodes_game_s5 = ArrayList<Episode>();episodes_game_s5.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison5, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s5.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison5, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s5.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison5, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))
                var episodes_game_s6 = ArrayList<Episode>();episodes_game_s6.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison6, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s6.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison6, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s6.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison6, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))
                var episodes_game_s7 = ArrayList<Episode>();episodes_game_s7.add(Episode("le premier episode", "30 mn ", R.drawable.gameofthrones_saison7, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString())); episodes_game_s7.add(Episode("le deuxieme episode", "50 mn ", R.drawable.gameofthrones_saison7, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()));episodes_game_s7.add(Episode("le troisieme episode", "40 mn ", R.drawable.gameofthrones_saison7, storyline1, diffusion_game, comments, evar,R.raw.video_harry.toString()))

                var episodes_vikings_s1 = ArrayList<Episode>();episodes_vikings_s1.add(Episode("le premier episode", "30 mn ", R.drawable.vikings_saison1, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString())); episodes_vikings_s1.add(Episode("le deuxieme episode", "50 mn ", R.drawable.vikings_saison1, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString()));episodes_vikings_s1.add(Episode("le troisieme episode", "40 mn ", R.drawable.vikings_saison1, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString()))
                var episodes_vikings_s2 = ArrayList<Episode>();episodes_vikings_s2.add(Episode("le premier episode", "30 mn ", R.drawable.vikings_saison2, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString())); episodes_vikings_s2.add(Episode("le deuxieme episode", "50 mn ", R.drawable.vikings_saison2, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString()));episodes_vikings_s2.add(Episode("le troisieme episode", "40 mn ", R.drawable.vikings_saison2, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString()))
                var episodes_vikings_s3 = ArrayList<Episode>();episodes_vikings_s3.add(Episode("le premier episode", "30 mn ", R.drawable.vikings_saison3, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString())); episodes_vikings_s3.add(Episode("le deuxieme episode", "50 mn ", R.drawable.vikings_saison3, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString()));episodes_vikings_s3.add(Episode("le troisieme episode", "40 mn ", R.drawable.vikings_saison3, storyline2, diffusion_vikings, comments, evar,R.raw.video_harry.toString()))


                // Actors
                var Actors = ArrayList<Personnes>()

                // Evaruation
                var evaract = ArrayList<Float>();evaract.add(3.0F); evaract.add(2.0F); evaract.add(5.0F); evaract.add(4.0F);
                var evaract1 = ArrayList<Float>();evaract1.add(3.0F); evaract1.add(5.0F); evaract1.add(3.0F); evaract1.add(5.0F);
                var evaract2 = ArrayList<Float>();evaract2.add(5.0F); evaract2.add(1.0F); evaract2.add(5.0F); evaract2.add(4.0F);
                var evaract3 = ArrayList<Float>();evaract3.add(4.0F); evaract3.add(2.0F); evaract3.add(4.0F); evaract3.add(5.0F);

                var storylineact = "est connu grâce aux rôles qu'il incarne à la télévision comme celui de Bright Abbott dans Everwood et d'Andy Dwyer dans Parks and Recreation. Il incarne aussi des rôles secondaires au cinéma comme dans Wanted : Choisis ton destin, Le Stratège, Ten Years, Cinq ans de réflexion, Zero Dark Thirty et Her. Il joue ensuite l'un des rôles principaux des films comme les comédies déjantées Hot Babes et Delivery Man."
                var storylineact1 = "une actrice, réalisatrice et productrice américano-dominicaine Elle accède à la notoriété grâce aux premiers rôles féminins de trois franchises majeures du cinéma de science-fiction : Avatar, Star Trek et Les Gardiens de la Galaxie."
                var storylineact2 = "plus connu sous le nom de Batista, est un acteur, ancien pratiquant de combat libre et catcheur américain Principalement connu pour son travail à la World Wrestling Entertainment (WWE), il y commence sa carrière en 2002 sous le nom de Deacon Batista. Après avoir été pendant un temps le manager de Reverend D-Von avant de devenir membre de l'Evolution avec Triple H, Ric Flair et Randy Orton."

                var commentsact = ArrayList<Comments>(); commentsact.add(Comments("Jules D. ", "J'ai hâte de le revoir dans le deuxième volet des Gardiens de la Galaxie !", R.drawable.e))
                var commentsact1 = ArrayList<Comments>(); commentsact1.add(Comments("Walter Mouse ", "La plus belle femme de ces dernières années. Ah et elle joue bien aussi ;)", R.drawable.i))
                var commentsact2 = ArrayList<Comments>(); commentsact2.add(Comments("MGM-ranger ", " Très bien dans les guardiens !)", R.drawable.c)); commentsact2.add(Comments("Licorne28  ", "Drax c'est le meilleur :) vive les gardiens", R.drawable.f))
                // filmographie
                var filmographie = ArrayList<Film>(); filmographie.add(Film("Guardien de la galaxy", R.drawable.guardians_galaxy, 0)); filmographie.add(Film("Wonder Women", R.drawable.wonder_women, 1)); filmographie.add(Film("Pirate des caraaibe", R.drawable.pirate, 2))


                Actors.add(Personnes("CHRIS PRATT", " 21 juin 1979 ", "Virginia, Minnesota, USA", R.drawable.crispatt, storylineact, commentsact, filmographie, evaract,R.drawable.crispatt2))
                Actors.add(Personnes("ZOE SALDANA", "19 juin 1978  ", "New Jersey - Etats-Unis", R.drawable.zoe_saldana, storylineact1, commentsact1, filmographie, evaract1,R.drawable.zoe_saldana2))
                Actors.add(Personnes("DAVE BAUTISTA", "18 janvier 1969  ", "Washington D.C. - Etats-Unis", R.drawable.dave, storylineact2, commentsact2, filmographie, evaract2,R.drawable.dave2))

                var saisons_casa = ArrayList<Saisons>()
                var saisons_game = ArrayList<Saisons>()
                var saisons_vikings = ArrayList<Saisons>()

                saisons_casa.add(Saisons("saison 1", "le debut de la saison sortie en 2016 ", R.drawable.casadelpapel_saison1, evar, comments, storyline, episodes_casa_s1, Actors,R.raw.casa.toString()))
                saisons_casa.add(Saisons("saison 2", "le debut de la saison sortie en 2017 ", R.drawable.casadelpapel_saison2, evar, comments, storyline, episodes_casa_s2, Actors,R.raw.casa.toString()))
                saisons_game.add(Saisons("saison 1", "le debut de la saison sortie en 2010 ", R.drawable.gameofthrones_saison1, evar, comments, storyline1, episodes_game_s1, Actors,R.raw.game.toString()))
                saisons_game.add(Saisons("saison 2", "le debut de la saison sortie en 2011 ", R.drawable.gameofthrones_saison2, evar, comments, storyline1, episodes_game_s2, Actors,R.raw.game.toString()))
                saisons_game.add(Saisons("saison 3", "le debut de la saison sortie en 2012 ", R.drawable.gameofthrones_saison3, evar, comments, storyline1, episodes_game_s3, Actors,R.raw.game.toString()))
                saisons_game.add(Saisons("saison 4", "le debut de la saison sortie en 2013 ", R.drawable.gameofthrones_saison4, evar, comments, storyline1, episodes_game_s4, Actors,R.raw.game.toString()))
                saisons_game.add(Saisons("saison 5", "le debut de la saison sortie en 2014 ", R.drawable.gameofthrones_saison5, evar, comments, storyline1, episodes_game_s5, Actors,R.raw.game.toString()))
                saisons_game.add(Saisons("saison 6", "le debut de la saison sortie en 2015 ", R.drawable.gameofthrones_saison6, evar, comments, storyline1, episodes_game_s6, Actors,R.raw.game.toString()))
                saisons_game.add(Saisons("saison 7", "le debut de la saison sortie en 2016 ", R.drawable.gameofthrones_saison7, evar, comments, storyline1, episodes_game_s7, Actors,R.raw.game.toString()))

                saisons_vikings.add(Saisons("saison 1", "le debut de la saison sortie en 2015 ", R.drawable.vikings_saison1, evar, comments, storyline2, episodes_vikings_s1, Actors,R.raw.vikings.toString()))
                saisons_vikings.add(Saisons("saison 2", "le debut de la saison sortie en 2016 ", R.drawable.vikings_saison2, evar, comments, storyline2, episodes_vikings_s2, Actors,R.raw.vikings.toString()))
                saisons_vikings.add(Saisons("saison 3", "le debut de la saison sortie en 2017 ", R.drawable.vikings_saison3, evar, comments, storyline2, episodes_vikings_s3, Actors,R.raw.vikings.toString()))

              //  Series.add(Series("La casa del papel", "Action Aventure ", "Álvaro Morte", R.drawable.casadelpapel, evar, comments, storyline, seriesL, saisons_casa, R.raw.casa.toString(),false))
                // Series.add(Series("Game Of Thrones", "DRAME FANTASTIQUE  ", " David Benioff", R.drawable.gameofthrones, evar, comments, storyline1, seriesL1, saisons_game, R.raw.game.toString(),false))
                //Series.add(Series("Vikings ", "AVENTURE DRAME HISTORIQUE ", "Michael Hirst ", R.drawable.vikings, evar, comments, storyline2, seriesL2, saisons_vikings, R.raw.vikings.toString(),false))


            }
            return Series
        }

        fun getActors(): ArrayList<Personnes> {

            if (Acteurs.size<1) {
                var evar = ArrayList<Float>()
                evar.add(5.0F); evar.add(5.0F); evar.add(4.0F); evar.add(3.0F);
                var evaract = ArrayList<Float>();evaract.add(2.0F); evaract.add(2.0F); evaract.add(5.0F); evaract.add(5.0F);
                var evaract1 = ArrayList<Float>();evaract1.add(2.0F); evaract1.add(4.0F); evaract1.add(4.0F); evaract1.add(5.0F);
                var evaract2 = ArrayList<Float>();evaract2.add(5.0F); evaract2.add(3.0F); evaract2.add(1.0F); evaract2.add(2.0F);
                var evaract3 = ArrayList<Float>();evaract3.add(2.0F); evaract3.add(5.0F); evaract3.add(2.0F); evaract3.add(3.0F);
                var evaract21 = ArrayList<Float>();evaract21.add(4.0F); evaract21.add(2.0F); evaract21.add(3.0F); evaract21.add(5.0F);
                var evaract22 = ArrayList<Float>();evaract22.add(3.0F); evaract22.add(3.0F); evaract22.add(5.0F); evaract22.add(4.0F);


                var storylineact = "est connu grâce aux rôles qu'il incarne à la télévision comme celui de Bright Abbott dans Everwood et d'Andy Dwyer dans Parks and Recreation. Il incarne aussi des rôles secondaires au cinéma comme dans Wanted : Choisis ton destin, Le Stratège, Ten Years, Cinq ans de réflexion, Zero Dark Thirty et Her. Il joue ensuite l'un des rôles principaux des films comme les comédies déjantées Hot Babes et Delivery Man."
                var storylineact1 = "une actrice, réalisatrice et productrice américano-dominicaine Elle accède à la notoriété grâce aux premiers rôles féminins de trois franchises majeures du cinéma de science-fiction : Avatar, Star Trek et Les Gardiens de la Galaxie."
                var storylineact2 = "plus connu sous le nom de Batista, est un acteur, ancien pratiquant de combat libre et catcheur américain Principalement connu pour son travail à la World Wrestling Entertainment (WWE), il y commence sa carrière en 2002 sous le nom de Deacon Batista. Après avoir été pendant un temps le manager de Reverend D-Von avant de devenir membre de l'Evolution avec Triple H, Ric Flair et Randy Orton."
                var storylineact30 = "John Christopher Depp II, dit Johnny Depp, est un acteur, réalisateur, guitariste, scénariste et producteur de cinéma américain, né le 9 juin 1963 à Owensboro (Kentucky)."
                var storylineact31 = "Javier Bardem est un acteur espagnol né le 1er mars 1969 à Las Palmas de Gran Canaria (Îles Canaries) Parangon du latin viril grâce à ses rôles sulfureux chez Bigas Luna et Pedro Almodóvar, il est le premier acteur espagnol à avoir été nommé aux Oscars, en 2001, et à être récompensé en 2008 comme « Meilleur second rôle masculin » pour son interprétation de tueur froid et implacable dans No Country for Old Men des frères Coen. Ce rôle lui a aussi varu un Golden Globe dans la même catégorie."
                var storylineact32 = "Brenton Thwaites est un acteur australien né le 10 août 1989 à Cairns Il débute à la télévision australienne, dans la série télévisée SLiDE et dans le soap opera Summer Bay1. Il est finalement révélé, au cinéma, notamment dans le film d'horreur Oculus (2013), dans le film de science-fiction The Giver2 (2014) ainsi que le film fantastique Maléfique (2014)3."

                var storylineact20 = "est connu grâce aux rôles qu'il incarne à la télévision comme celui de Bright Abbott dans Everwood et d'Andy Dwyer dans Parks and Recreation. Il incarne aussi des rôles secondaires au cinéma comme dans Wanted : Choisis ton destin, Le Stratège, Ten Years, Cinq ans de réflexion, Zero Dark Thirty et Her. Il joue ensuite l'un des rôles principaux des films comme les comédies déjantées Hot Babes et Delivery Man."
                var storylineact21 = "un acteur américain. Il est révélé pour son interprétation du rôle du capitaine Kirk dans la franchise reboot Star Trek, produite par J. J. Abrams depuis 2009, qui rencontre un important succès commercial et critique."
                var storylineact22 = "une actrice danoise. Révélée au monde entier en 2000 par son interprétation de Lucilla dans le péplum de Ridley Scott Gladiator, elle incarnera aussi un rôle de femme de pouvoir trouble et complexe en interprétant Meredith Kane dans la série politique Boss, entre 2011 et 2012."

                var commentsact20 = ArrayList<Comments>(); commentsact20.add(Comments("Jules D. ", "Très bon choix pour Wonder Woman", R.drawable.e))
                var commentsact21 = ArrayList<Comments>(); commentsact21.add(Comments("Walter Mouse ", "Il est convaincant dans Comancheria, il remonte dans mon estime ;)", R.drawable.d))
                var commentsact22 = ArrayList<Comments>(); commentsact22.add(Comments("MGM-ranger ", " une magnifique femme, et une très bonne actrice !", R.drawable.i))

                var commentsact30 = ArrayList<Comments>(); commentsact30.add(Comments("Jules D. ", "L'un des plus grand acteur de tous les temps", R.drawable.e))
                var commentsact31 = ArrayList<Comments>(); commentsact31.add(Comments("Walter Mouse ", "gage de qualité ", R.drawable.d))
                var commentsact32 = ArrayList<Comments>(); commentsact32.add(Comments("MGM-ranger ", " Il resemble un peu à l'acteur de spider Man je trouve lol", R.drawable.i))
                var commentsact = ArrayList<Comments>(); commentsact.add(Comments("Jules D. ", "J'ai hâte de le revoir dans le deuxième volet des Gardiens de la Galaxie !", R.drawable.e))
                var commentsact1 = ArrayList<Comments>(); commentsact1.add(Comments("Walter Mouse ", "La plus belle femme de ces dernières années. Ah et elle joue bien aussi ;)", R.drawable.i))
                var commentsact2 = ArrayList<Comments>(); commentsact2.add(Comments("MGM-ranger ", " Très bien dans les guardiens !)", R.drawable.c))


                // filmographie
                var filmographie = ArrayList<Film>(); filmographie.add(Film("Guardien de la galaxy", R.drawable.guardians_galaxy, 0)); filmographie.add(Film("Wonder Women", R.drawable.wonder_women, 1)); filmographie.add(Film("Pirate des caraaibe", R.drawable.pirate, 2))

                Acteurs.add(Personnes("CHRIS PRATT", " 21 juin 1979 ", "Virginia, Minnesota, USA", R.drawable.crispatt, storylineact, commentsact, filmographie, evaract,R.drawable.crispatt2))
                Acteurs.add(Personnes("ZOE SALDANA", "19 juin 1978  ", "New Jersey - Etats-Unis", R.drawable.zoe_saldana, storylineact1, commentsact1, filmographie, evaract1,R.drawable.zoe_saldana2))
                Acteurs.add(Personnes("DAVE BAUTISTA", "18 janvier 1969  ", "Washington D.C. - Etats-Unis", R.drawable.dave, storylineact2, commentsact2, filmographie, evaract2,R.drawable.dave2))
                Acteurs.add(Personnes("GAL GADOT", " 30 avril 1985 ", "(Rosh Ha'ayin", R.drawable.gal, storylineact20, commentsact20, filmographie, evaract3,R.drawable.gal2))
                Acteurs.add(Personnes("CHRIS PINE", " 26 août 1980   ", "Los Angeles, Californie - Etats-Unis", R.drawable.chris_pin, storylineact21, commentsact21, filmographie, evaract21,R.drawable.chris_pin2))
                Acteurs.add(Personnes("CONNIE NIELSEN", " 3 juillet 1965   ", "Elling, Jutland - Danemark", R.drawable.connies, storylineact22, commentsact22, filmographie, evaract22,R.drawable.connies2))
                Acteurs.add(Personnes("JOHNNY DEPP", " 9 juin 1963  ", "Owensboro, Kentucky - Etats-Unis", R.drawable.jonny, storylineact30, commentsact30, filmographie, evaract,R.drawable.jonny2))
                Acteurs.add(Personnes("JAVIER BARDEM", "  1 mars 1969  ", "Las Palma, Gran Canaria - Espagne", R.drawable.javier, storylineact31, commentsact31, filmographie, evaract21,R.drawable.javier2))
                Acteurs.add(Personnes("BRENTON THWAITES", " 10 août 1989  ", "Australie", R.drawable.brenton, storylineact32, commentsact32, filmographie, evaract22,R.drawable.brenton2))

            }
            return Acteurs
        }

        fun getRealisators(): ArrayList<Personnes> {


            if (Realisateurs.size<1) {
                // filmographie
                var filmographie = ArrayList<Film>(); filmographie.add(Film("Guardien de la galaxy", R.drawable.guardians_galaxy, 0)); filmographie.add(Film("Wonder Women", R.drawable.wonder_women, 1)); filmographie.add(Film("Pirate des caraaibe", R.drawable.pirate, 2))


                var storylinereal = "James Gunn est un scénariste, acteur, producteur, réalisateur et directeur de la photographie américain, "
                var commentsreal = ArrayList<Comments>(); commentsreal.add(Comments("Cosmicm  ", " Quand j'ai vu sa filmographie, je me suis dit: attend c'est à lui qu'on a confié les gardiens de la galaxie ??? Comme quoi...on peut vraiment être surpris.", R.drawable.h))
                var evarreal = ArrayList<Float>();evarreal.add(4.0F); evarreal.add(3.0F); evarreal.add(5.0F); evarreal.add(5.0F);
                var commentsreal2 = ArrayList<Comments>(); commentsreal2.add(Comments("Cosmicm  ", " Personne ici pour parler de son incroyable travail sur WW ", R.drawable.b))
                var evarrea12 = ArrayList<Float>();evarrea12.add(2.0F); evarrea12.add(2.0F); evarrea12.add(5.0F); evarrea12.add(4.0F);
                var storylinereal2 = "Patty Jenkins est une réalisatrice et scénariste américaine, née le 24 juillet 1971 à Victorville en Californie.Elle s'est fait connaitre avec le film Monster, pour lequel Charlize Theron a obtenu l'Oscar de la meilleure actrice."
                var storylinerea32 = "Espen Sandberg est un réalisateur norvégien né le 17 juin 1971 à Sandefjord en Norvège. Il forme un duo avec Joachim Rønning appelé les « Roenberg »1"
                var commentsrea32 = ArrayList<Comments>(); commentsrea32.add(Comments("Cosmicm  ", " Personne ici pour parler de son incroyable travail sur WW ", R.drawable.b))
                Realisateurs.add(Personnes("ESPEN SANDBERG", "5 août 1970 ", "Providence, Rhode Island, États-Unis", R.drawable.espen, storylinerea32, commentsrea32, filmographie, evarreal,R.drawable.espen2))

                Realisateurs.add(Personnes("PATTY JENKINS", "5 août 1970 ", "Providence, Rhode Island, États-Unis", R.drawable.patty, storylinereal2, commentsreal2, filmographie, evarreal,R.drawable.patty2))

                Realisateurs.add(Personnes("JAMES GUNN", "5 août 1970 ", "Providence, Rhode Island, États-Unis", R.drawable.james, storylinereal, commentsreal, filmographie, evarreal,R.drawable.james2))

            }

            return Realisateurs
        }

        fun getCinema(): ArrayList<Cinema> {


            if (Cinemas.size<1) {
                val arg = LatLng(40.7340685, -73.9933289)
                val arg1 = LatLng(48.8682668,2.366204)
                val arg2 = LatLng(40.7359436, -73.9960851)

                Cinemas.add(Cinema("Cinema Village ", "22 E 12th St, New York, NY 10003, États-Unis ", R.drawable.cinema_room,arg,false))
                Cinemas.add(Cinema("Apollo Théâtre ", "18 Rue du Faubourg du Temple, 75011 Paris, France", R.drawable.apollo,arg1,false))
                Cinemas.add(Cinema("Quad Cinema ", "34 W 13th St, New York, NY 10011, États-Unis ", R.drawable.quad,arg2,false))
            }
            return Cinemas
        }
        
        fun getMoviesFav():ArrayList<Movie> {

            return Filmsfav;
        }
        fun getSerieFav():ArrayList<Series> {

            return SeriesFav;
        }
        fun getCinemaFav():ArrayList<Cinema> {

            return CinemasFav;
        }
    }





}